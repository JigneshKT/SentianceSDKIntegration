package sentiance.com.sentiancesdkintegration;

import android.app.Application;

import sentiance.com.sentiancesdkintegration.presentation.dagger.AppComponent;
import sentiance.com.sentiancesdkintegration.presentation.dagger.DaggerAppComponent;
import sentiance.com.sentiancesdkintegration.presentation.dagger.module.SentianceSDKConfig;

public class SentianceSDKIntegration  extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        getAppComponent().inject(this);

    }

    private void initAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .sentianceSDKConfig(new SentianceSDKConfig(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

