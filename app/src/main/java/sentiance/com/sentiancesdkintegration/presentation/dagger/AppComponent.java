package sentiance.com.sentiancesdkintegration.presentation.dagger;


import javax.inject.Singleton;

import dagger.Component;
import sentiance.com.sentiancesdkintegration.SentianceSDKIntegration;
import sentiance.com.sentiancesdkintegration.presentation.dagger.module.SentianceSDKConfig;
import sentiance.com.sentiancesdkintegration.presentation.ui.home.HomeActivity;

@Singleton
@Component(modules = {SentianceSDKConfig.class})
public interface AppComponent {
    void inject(SentianceSDKIntegration sentianceSDKIntegration);
    void inject(HomeActivity homeActivity);
}
