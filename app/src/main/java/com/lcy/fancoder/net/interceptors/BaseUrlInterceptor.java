package com.lcy.fancoder.net.interceptors;

import android.text.TextUtils;

import com.lcy.fancoder.beans.http.BaseUrl;

import java.io.IOException;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Project 四川水木科技移动应用基础库.
 * @Describe URL 拦截器, 可以实现动态切换 BaseUrl.
 * @Author 吴荣
 * @Date 2018-07-24
 * @Time 16:16
 * @Copyright 2018 www.scshuimukeji.cn Inc. All rights reserved.
 * 注意: 本内容仅限于四川水木科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@SuppressWarnings({"JavaDoc", "unused"})
public class BaseUrlInterceptor implements Interceptor {

    /**
     * BaseUrl 配置信息.
     */
    private Map<String, BaseUrl> urlsConfigs;

    /**
     * 构造器.
     */
    public BaseUrlInterceptor(Map<String, BaseUrl> urlsConfigs) {
        this.urlsConfigs = urlsConfigs;
    }

    /**
     * 拦截方法.
     *
     * @param chain 请求修改器.
     * @return 请求结果.
     * @throws IOException IO 读写异常.
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String headValue = request.header("Base-Url");
        if (urlsConfigs != null && !TextUtils.isEmpty(headValue)) {
            BaseUrl urlValue = urlsConfigs.get(headValue);
            if (null != urlValue) {
                HttpUrl oldHttpUrl = request.url();
                Request.Builder builder = request.newBuilder();
                builder.removeHeader("Base-Url");
                HttpUrl.Builder httpUrlBuilder = oldHttpUrl.newBuilder();
                httpUrlBuilder.scheme(urlValue.scheme);
                httpUrlBuilder.host(urlValue.host);
                httpUrlBuilder.port(urlValue.port);
                HttpUrl newHttpUrl = httpUrlBuilder.build();
                return chain.proceed(builder.url(newHttpUrl).build());
            }
        }
        return chain.proceed(request);
    }
}