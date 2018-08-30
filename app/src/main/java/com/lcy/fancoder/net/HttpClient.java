package com.lcy.fancoder.net;

import com.lcy.fancoder.App;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @Project 四川水木科技移动应用基础库.
 * @Describe API 服务客户端, 默认关闭了失败重试, 连接超时时间5秒, 读写超时时间5秒, 本地 Get 缓存 20 MB, 当前客户端主要用于应用内的高速请求, 不受图片,大文件传输的影响.
 * @Author 吴荣
 * @Date 2018-07-24
 * @Time 16:16
 * @Copyright 2018 www.scshuimukeji.cn Inc. All rights reserved.
 * 注意: 本内容仅限于四川水木科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@SuppressWarnings({"JavaDoc", "unused"})
public class HttpClient {

    ////////////////////////////////////////////////////////////
    //////////////////// 成员变量
    ////////////////////////////////////////////////////////////

    private static volatile HttpClient instance;
    private OkHttpClient okHttpClient;

    ////////////////////////////////////////////////////////////
    ////////////////////
    ////////////////////////////////////////////////////////////
    private Retrofit retrofit;

    private HttpClient() {
        okHttpClient = App.getInstance().applicationConfig.httpClientConfig.getOkBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .cache(new Cache(App.getInstance().getCacheDir(), 1024 * 1024 * 20))
                .build();
        retrofit = App.getInstance().applicationConfig.httpClientConfig.getReBuilder().client(okHttpClient).build();
    }

    public static HttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    ////////////////////////////////////////////////////////////
    ////////////////////  私有方法 & 对内 API 方法
    ////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    ////////////////////  公有方法 & 对外 API 方法
    ////////////////////////////////////////////////////////////

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T get(final Class<T> service) {
        return retrofit.create(service);
    }
}
