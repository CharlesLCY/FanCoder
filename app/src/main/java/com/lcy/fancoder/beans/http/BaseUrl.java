package com.lcy.fancoder.beans.http;

import com.lcy.fancoder.net.interceptors.BaseUrlInterceptor;

import okhttp3.Interceptor;

/**
 * URL 实体类, 用于网络请求配置不同的请求域名时使用.
 * 配置信息后,需要在请求头中配置相匹配的信息,在请求接口中发现相关请求头后,由拦截器自行切换,该拦截器默认不注册.
 * 详情可参见 {@link BaseUrlInterceptor#intercept(Interceptor.Chain)}
 * <p>
 * 作者 : 吴荣
 * 日期 : 2018-07-24 16:16
 */
@SuppressWarnings("JavaDoc")
public class BaseUrl {

    /**
     * Scheme 协议头.
     * 目前只支持 https 和 http
     */
    public String scheme;

    /**
     * Hoast 域名 IP 地址.
     */
    public String host;

    /**
     * Port 端口.
     * 默认为80,取值范围为: (0,65535]
     */
    public int port = 80;

    /**
     * 一个最基本的构造器.
     *
     * @param scheme 协议头.
     * @param host   域名或 IP 地址.
     */
    public BaseUrl(String scheme, String host) {
        this.scheme = scheme;
        this.host = host;
    }

    /**
     * 一个完整的构造器.
     *
     * @param scheme 协议头.
     * @param host   域名或 IP 地址.
     * @param port   端口号.
     */
    public BaseUrl(String scheme, String host, int port) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

}
