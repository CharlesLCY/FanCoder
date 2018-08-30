package com.lcy.fancoder.beans.http;

/**
 * @Project 四川水木科技移动应用基础库.
 * @Describe 移动前端通用返回实体类对象.
 * @Author 吴荣
 * @Date 2018-07-10
 * @Time 11:49
 * @Copyright 2018 www.scshuimukeji.cn Inc. All rights reserved.
 * 注意: 本内容仅限于四川水木科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@SuppressWarnings("JavaDoc")
public class BaseResult<T> {

    public long code;       // 业务状态码.
    public String message;  // 业务消息.
    public String key;      // 解密用到的盐.
    public T body;          // 返回的业务数据.
}
