package com.major.elegant.biz.main;

import com.major.elegant.api.ApiFactory;
import com.major.elegant.bean.Gank;
import com.major.elegant.rx.RxSchedulers;

import rx.Observable;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:16
 */
public class MainModel implements MainContract.Model {

    @Override
    public Observable<Gank> getGank(int page) {
        return ApiFactory.getInstance()
                         .getApiService()
                         .getGank(page)
                         .compose(RxSchedulers.<Gank>switchThrid());
    }
}
