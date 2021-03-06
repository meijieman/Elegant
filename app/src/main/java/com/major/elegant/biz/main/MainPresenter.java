package com.major.elegant.biz.main;

import com.major.elegant.bean.Gank;

import rx.Subscriber;
import rx.Subscription;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:14
 */
public class MainPresenter extends MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void getGank(int page) {
        Subscription subscribe =
                mModel.getGank(page)
                        .subscribe(new Subscriber<Gank>() {
                            @Override
                            public void onStart() {
                                mView.showDialog();
                            }

                            @Override
                            public void onCompleted() {
                                mView.hideDialog();
                            }

                            @Override
                            public void onError(Throwable e) {
                                mView.onFailure(e.getMessage());
                                onCompleted();
                            }

                            @Override
                            public void onNext(Gank gank) {
                                mView.onSuccess(gank);
                            }
                        });
        addSubscibne(subscribe);
    }
}
