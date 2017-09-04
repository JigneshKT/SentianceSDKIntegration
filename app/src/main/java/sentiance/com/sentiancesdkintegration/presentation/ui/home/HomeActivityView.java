package sentiance.com.sentiancesdkintegration.presentation.ui.home;

import sentiance.com.sentiancesdkintegration.base.BaseActivityView;


public interface HomeActivityView extends BaseActivityView {
    void onSentianceSDKStarted();
    void onSentianceSDKFailure();

    void onUserIdSuccess(String userId);
    void onUserIdFailure();

    void onVersionSuccess(String version);
    void onVersionFailure();
}
