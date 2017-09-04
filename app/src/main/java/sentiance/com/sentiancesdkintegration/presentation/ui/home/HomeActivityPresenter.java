package sentiance.com.sentiancesdkintegration.presentation.ui.home;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import sentiance.com.sentiancesdkintegration.base.BaseActivityPresenter;
import sentiance.com.sentiancesdkintegration.data.SentianceSDK.SentianceSDKRepository;



public class HomeActivityPresenter extends BaseActivityPresenter<HomeActivityView> {

    private final SentianceSDKRepository sentianceSDKRepository;


    @Inject
    HomeActivityPresenter(SentianceSDKRepository sentianceSDKRepository) {
        this.sentianceSDKRepository = sentianceSDKRepository;
    }

    @Override
    protected void updateViewState() {
        super.updateViewState();
        initializeSentianceSDK();
    }

    public void startSentianceSDK() {

        sentianceSDKRepository.startSentianceSDK()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onSentianceSDKFailure();
                    }

                    @Override
                    public void onNext(Boolean status) {

                        if (status)
                            view.onSentianceSDKStarted();
                        else
                            view.onSentianceSDKFailure();
                    }
                });
    }

    public void initializeSentianceSDK(){
        sentianceSDKRepository.getSDKStatus()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onSentianceSDKFailure();
                    }

                    @Override
                    public void onNext(Boolean status) {
                        if(!status){
                            startSentianceSDK();
                        }else{
                            view.onSentianceSDKStarted();
                        }
                    }
                });
    }


    public void getSentianceUserId(){

        sentianceSDKRepository.getUserId()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onSentianceSDKFailure();
                    }

                    @Override
                    public void onNext(String userId) {

                        if (userId!=null && !userId.equals(""))
                            view.onUserIdSuccess(userId);
                        else
                            view.onUserIdFailure();
                    }
                });

    }

    public void getSentianceVersion(){

        sentianceSDKRepository.getSentianceSDKVersion()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onVersionFailure();
                    }

                    @Override
                    public void onNext(String version) {

                        if (version!=null && !version.equals(""))
                            view.onVersionSuccess(version);
                        else
                            view.onVersionFailure();
                    }
                });

    }


}
