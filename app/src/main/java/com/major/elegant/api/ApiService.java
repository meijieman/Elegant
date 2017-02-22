package com.major.elegant.api;

import com.major.elegant.bean.Gank;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:49
 */
public interface ApiService {

    String BASE_URL = "http://gank.io";

    @GET("api/data/Android/10/{page}")
    Observable<Gank> getGank(@Path("page") int page);
}
