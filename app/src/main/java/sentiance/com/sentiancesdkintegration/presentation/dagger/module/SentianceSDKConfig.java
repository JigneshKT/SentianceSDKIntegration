package sentiance.com.sentiancesdkintegration.presentation.dagger.module;

import android.content.Context;

import com.sentiance.sdk.SdkConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sentiance.com.sentiancesdkintegration.data.SentianceSDK.SentianceSDKRepository;

@Module
public class SentianceSDKConfig {

    Context context;
    public SentianceSDKConfig(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    SdkConfig provideSdkConfig(){
        return new SdkConfig.Builder("59aa842a04210d07000000d8", "569fdabb454c7ca0933d2d7c15c81604ed4ebe1909f9e92537ce269f16988e69b72c99850a6944ff64215df9d61b9845a61a9945a3b3e6ebe02826de7813cad5")
                .build();
    }

    @Provides
    @Singleton
    SentianceSDKRepository provideSentianceSDKRepository(SdkConfig sdkConfig, Context context){
        return  new SentianceSDKRepository(sdkConfig,context);
    }


}
