package sentiance.com.sentiancesdkintegration.data.SentianceSDK;

import android.content.Context;

import com.sentiance.sdk.OnInitCallback;
import com.sentiance.sdk.OnStartFinishedHandler;
import com.sentiance.sdk.SdkConfig;
import com.sentiance.sdk.SdkStatus;
import com.sentiance.sdk.Sentiance;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

public class SentianceSDKRepository implements OnInitCallback, OnStartFinishedHandler {

    private SdkConfig sdkConfig;
    private Context context;
    private final static String TAG = SentianceSDKRepository.class.getSimpleName();
    private PublishSubject<Boolean> sdkConfigSubject = PublishSubject.create();
    private Boolean isSDKInitialised;


    @Inject
    public SentianceSDKRepository(SdkConfig sdkConfig,Context context){
        this.sdkConfig = sdkConfig;
        this.context =context;
        isSDKInitialised=false;
    }

    public Observable<Boolean> startSentianceSDK(){
        sentianceSDKInit();
        return sdkConfigSubject;
    }

    private void sentianceSDKInit(){
        Sentiance.getInstance(context).init(sdkConfig, this);
    }


    @Override
    public void onInitSuccess() {
        Sentiance.getInstance(context).start(this);
    }

    @Override
    public void onInitFailure(InitIssue initIssue) {
        isSDKInitialised=false;
        sdkConfigSubject.onError(new Throwable());
    }

    @Override
    public void onStartFinished(SdkStatus sdkStatus) {
        isSDKInitialised=true;
        sdkConfigSubject.onNext(true);
    }


    public Observable<String> getUserId(){
        return Observable.just(Sentiance.getInstance(context).getUserId());
    }


    public Observable<Boolean> getSDKStatus(){
        return Observable.just(isSDKInitialised);
    }

    public Observable<String> getSentianceSDKVersion(){
        return Observable.just(Sentiance.getInstance(context).getVersion());
    }

}
