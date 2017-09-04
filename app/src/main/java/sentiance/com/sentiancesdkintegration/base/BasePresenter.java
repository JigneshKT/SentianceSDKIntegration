package sentiance.com.sentiancesdkintegration.base;


import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends BaseActivityView> {

    protected CompositeSubscription subscriptions = new CompositeSubscription();

    protected T view;

    public void onResume(T v) {
        setView(v);
        updateViewState();
    }

    protected void setView(@NonNull T view) {
        this.view = view;
    }

    protected void updateViewState(){};

}
