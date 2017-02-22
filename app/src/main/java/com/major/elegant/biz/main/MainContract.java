package com.major.elegant.biz.main;

import com.major.elegant.base.BaseModel;
import com.major.elegant.base.BasePresenter;
import com.major.elegant.base.BaseView;
import com.major.elegant.bean.Gank;

import rx.Observable;

/**
 * @desc: 契约类
 * @author: Major
 * @since: 2017/2/22 0:22
 */
public interface MainContract {

    interface View extends BaseView {

        void onSuccess(Gank data);

        void onFailure(String err);
    }

    interface Model extends BaseModel {

        Observable<Gank> getGank(int page);
    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getGank(int page);
    }
}
