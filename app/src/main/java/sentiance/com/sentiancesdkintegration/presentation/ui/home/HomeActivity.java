package sentiance.com.sentiancesdkintegration.presentation.ui.home;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sentiance.com.sentiancesdkintegration.R;
import sentiance.com.sentiancesdkintegration.base.BaseActivity;


public class HomeActivity extends BaseActivity<HomeActivityPresenter> implements HomeActivityView{


    @BindView(R.id.tv_user_id)
    TextView tv_user_id;

    @BindView(R.id.tv_sentiance_version)
    TextView tv_sentiance_version;

    @OnClick(R.id.tv_user_id)
    public void getSentianceVersion(){
        getPresenter().getSentianceVersion();
    }

    @Inject
    HomeActivityPresenter homeActivityPresenter;

    @Override
    protected void inject() {
        getAppComponent().inject(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_home;
    }

    @NonNull
    @Override
    protected HomeActivityPresenter getPresenter() {
        return homeActivityPresenter;
    }

    @Override
    public void onSentianceSDKStarted() {
        getPresenter().getSentianceUserId();
    }

    @Override
    public void onSentianceSDKFailure() {
        showUserIdWithMessage(getString(R.string.user_id_failure_label));
    }

    @Override
    public void onUserIdSuccess(final String userId) {
        showUserIdWithMessage(getString(R.string.user_id_success_label)+userId);
    }

    @Override
    public void onUserIdFailure() {
        showUserIdWithMessage(getString(R.string.user_id_failure_label));
    }


    @Override
    public void onVersionSuccess(String version) {
        showVersionWithMessage(getString(R.string.sentiance_version_success_label)+version);
    }

    @Override
    public void onVersionFailure() {
        showUserIdWithMessage(getString(R.string.sentiance_version_failure_label));
    }

    private void showUserIdWithMessage(final String message){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_user_id.setVisibility(View.VISIBLE);
                tv_user_id.setText(message);
            }
        });
    }

    private void showVersionWithMessage(final String message){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_sentiance_version.setVisibility(View.VISIBLE);
                tv_sentiance_version.setText(message);
            }
        });
    }
}
