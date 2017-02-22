package com.major.elegant.api;

import com.major.elegant.base.App;
import com.major.elegant.util.LogUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:39
 */
public class ApiFactory {

    private volatile static ApiFactory sInstance;

    private Retrofit mRetrofit;

    private ApiFactory() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        int size = 1024 * 1024 * 10;
        File cacheFile = new File(App.getInstance().getExternalCacheDir(), "okHttp");
        LogUtil.w("cacheFile " + cacheFile.getAbsolutePath());
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()) // 解析数据
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ApiFactory getInstance() {
        if (sInstance == null) {
            synchronized (ApiFactory.class) {
                sInstance = new ApiFactory();
            }
        }
        return sInstance;
    }

    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }
}
