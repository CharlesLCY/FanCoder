package com.lcy.fancoder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lcy.fancoder.configs.ApplicationConfig;
import com.lcy.fancoder.configs.LoggerAS3IssuesFix;
import com.lcy.fancoder.lifecycles.ApplicationListener;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.List;

/**
 * @author FanCoder.LCY
 * @date 2018/8/30 19:53
 * @email 15708478830@163.com
 * @desc
 **/
public abstract class App extends Application {
    /**
     * 假单例,用于保存 Application 实例对象,在其他地方获取.
     */
    private static App context;

    /**
     * 应用程序配置信息类,用于 APP 配置基础库中的基本信息.
     */
    public ApplicationConfig applicationConfig;

    /**
     * 应用程序生命周期监听器.用于第三方根据生命周期处理相关逻辑.
     */
    private List<ApplicationListener> applicationListeners;

    /**
     * 应用程序是否允许在后台.
     */
    private boolean isBackground = true;

    /**
     * Called when the application is starting, before any activity, service,
     * or receiver objects (excluding content providers) have been created.
     * Implementations should be as quick as possible (for example using
     * lazy initialization of state) since the time spent in this function
     * directly impacts the performance of starting the first activity,
     * service, or receiver in a process.
     * If you override this method, be sure to call super.onCreate().
     */
    @Override
    public void onCreate() {
        initConfig();                                                                               // 初始化应用程序配置信息
        super.onCreate();                                                                           // 初始化 Application
        initApplication();                                                                          // 初始化单例
        initCallbacks();                                                                            // 初始化生命周期回调
        initLogger();                                                                               // 初始化 Logger 日志
        initARouter();                                                                              // 初始化 ARouter 路由
        registerForegroundListener();                                                               // 注册前台监听
        triggerApplicationOnCreateCallbackEvent(0);                                       // 触发生命周期回调监听

    }

    /**
     * 内存低.
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 内存等级发生变化.
     *
     * @param level 内存/页面状态等级.
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        registerBackgroundListener(level);
    }

    /**
     * 初始化应用程序生命周期监听器.
     */
    private void initCallbacks() {
        if (null == applicationListeners) {
            applicationListeners = getApplicationCallbacks();
        }
    }

    /**
     * 初始化配置信息.
     */
    private void initConfig() {
        if (null == applicationConfig) {
            applicationConfig = getApplicationConfig();
        }
    }

    /**
     * 初始化应用程序.
     */
    private void initApplication() {
        if (null == context) {
            context = this;
        }
    }

    /**
     * 初始化日志工具类.
     */
    private void initLogger() {
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .logStrategy(new LoggerAS3IssuesFix())
                .methodCount(0)
                .showThreadInfo(false)
                .tag("SM")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return applicationConfig.isDebugModel;
            }
        });
    }

    /**
     * 初始化 ARouter .
     */
    private void initARouter() {
        if (applicationConfig.isDebugModel) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
    }

    /**
     * 触发 com.lcy.fancoder.App 生命周期事件.
     *
     * @param eventCode 生命周期事件编码. 0=创建应用; 1=进入后台; 2=进入前台.
     */
    private void triggerApplicationOnCreateCallbackEvent(int eventCode) {
        if (null != applicationListeners && applicationListeners.size() > 0) {
            for (ApplicationListener callback : applicationListeners) {
                switch (eventCode) {
                    case 0:
                        callback.onAppCreate();
                        break;
                    case 1:
                        callback.onAppToBackground();
                        break;
                    case 2:
                        callback.onAppToForeground();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 注册应用程序转入前台的监听.
     */
    private void registerForegroundListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                if (isBackground) {
                    isBackground = false;
                    triggerApplicationOnCreateCallbackEvent(2);
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    /**
     * 私有方法-注册应用程序转入后台的监听.
     *
     * @param level 当前内存/页面状态等级.
     */
    private void registerBackgroundListener(int level) {
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            isBackground = true;
            triggerApplicationOnCreateCallbackEvent(1);
        }
    }

    /**
     * 获取应用程序 Application 实例.
     *
     * @return 返回应用程序 Application 实例.
     */
    public static App getInstance() {
        return context;
    }

    /**
     * 获取额外的生命周期处理接口对象.
     * 可用于第三方及程序内部监听应用的生命周期,以便处理相关业务问题.
     */
    public abstract List<ApplicationListener> getApplicationCallbacks();

    /**
     * 获取应用程序配置信息.
     * 用于实现初始化相关库及参数.
     */
    public abstract ApplicationConfig getApplicationConfig();
}
