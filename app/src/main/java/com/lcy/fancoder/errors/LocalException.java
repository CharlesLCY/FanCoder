package com.lcy.fancoder.errors;

/**
 * @Project 四川水木科技移动应用基础库.
 * @Describe 本地异常类, 用于捕获/显示 APP 本地错误.
 * @Author 吴荣
 * @Date 2018-07-24
 * @Time 16:16
 * @Copyright 2018 www.scshuimukeji.cn Inc. All rights reserved.
 * 注意: 本内容仅限于四川水木科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@SuppressWarnings("JavaDoc")
public class LocalException extends RuntimeException {

    public LocalException() {
        super();
    }

    public LocalException(String message) {
        super(message);
    }

    public LocalException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalException(Throwable cause) {
        super(cause);
    }
}
