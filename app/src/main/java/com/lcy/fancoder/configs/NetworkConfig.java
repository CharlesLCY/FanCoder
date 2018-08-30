package com.lcy.fancoder.configs;

import com.lcy.fancoder.net.FileClient;
import com.lcy.fancoder.net.HttpClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 网络配置接口, 用于配置网络请求.
 * <p>
 * 具体使用情况 参见: {@link FileClient} 和 {@link HttpClient}
 * <p>
 * 作者 : 吴荣
 * 日期 : 2018-07-23 16:10
 */
@SuppressWarnings("JavaDoc")
public interface NetworkConfig {

    /**
     * 获取网络底层客户端构造器.
     *
     * @return 网络底层客户端构造器.
     */
    OkHttpClient.Builder getOkBuilder();

    /**
     * 获取网络 API 层客户端构造器.
     *
     * @return 网络 API 层客户端构造器.
     */
    Retrofit.Builder getReBuilder();

}