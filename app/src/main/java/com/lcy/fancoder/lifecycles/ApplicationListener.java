package com.lcy.fancoder.lifecycles;


import com.lcy.fancoder.App;

/**
 * 应用程序监听器.
 * 实现该接口后,通过{@link App#getApplicationCallbacks()}的方式注册至应用程序中,
 * 相关生命周期时,自动触发监听回调,相关需求方则根据回调完成基本操作,例如: 分享 SDK 初始化等.
 * <p>
 * 作者 : 吴荣
 * 日期 : 略
 */
public interface ApplicationListener {

    /**
     * 应用程序被创建
     */
    void onAppCreate();                                            // 创建 Application 实例

    /**
     * 应用程序转入后台运行.
     */
    void onAppToBackground();                                      // com.lcy.fancoder.App 转入后台

    /**
     * 应用程序回到前台运行.
     */
    void onAppToForeground();                                      // com.lcy.fancoder.App 转入前台

}
