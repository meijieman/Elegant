package com.major.elegant.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:16
 */

public class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V mView;
    protected M mModel;
    private CompositeSubscription mCompositeSubscription;

    protected void addSubscibne(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe(){
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }
}
