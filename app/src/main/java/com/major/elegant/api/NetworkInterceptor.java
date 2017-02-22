package com.major.elegant.api;

import com.major.elegant.base.BaseApp;
import com.major.elegant.util.LogUtil;
import com.major.elegant.util.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @desc: TODO
 * @author: Major
 * @since: 2017/2/22 0:55
 */
public class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //无网络时强制使用缓存
        if (!NetUtil.isConnected(BaseApp.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            LogUtil.e("no network");
        }

        Response response = chain.proceed(request);

        if (NetUtil.isConnected(BaseApp.getInstance())) {
            String cacheControl = request.cacheControl().toString();
            // 有网络时，设置缓存为0
            int maxStale = 0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxStale)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
            LogUtil.e("有网络");
        } else {
            // 无网络时，设置缓存为3周
            int maxStale = 60 * 60 * 24 * 21;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
            LogUtil.e("无网络");
        }

        return response;
    }
}
