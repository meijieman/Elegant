package com.major.elegant.biz.main;

import com.major.elegant.base.BaseModel;
import com.major.elegant.base.BasePresenter;
import com.major.elegant.base.BaseView;
import com.major.elegant.bean.Gank;

import rx.Observable;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:22
 */
public interface MainContract {

    interface MainView extends BaseView {

        void showDialog();

        void hideDialog();

        void onSuccess(Gank data);

        void onFailure(String err);
    }

    interface MainModel extends BaseModel {

        Observable<Gank> getGank(int page);
    }

    abstract class MainPresenter extends BasePresenter<MainView, MainModel> {

        public abstract void getGank(int page);
    }

}
