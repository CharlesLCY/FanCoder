package com.lcy.fancoder.beans.http;

/**
 * 通用请求类.
 */
public class BaseRequest<T> {

    public String os;                   // 操作系统
    public String clientVersion;        // 客户端版本号
    public String mobileType;           // 手机型号
    public String guid;                 // 手机唯一标识
    public String channel;              // 渠道号
    public String token;                // 口令, 登陆后传递
    public long timestamp;              // 时间戳（精确到毫秒）
    public String sign;                 // 签名字符串
    public String salt;                 // 盐（6位随机数）
    public T data;                      // 业务参数

}