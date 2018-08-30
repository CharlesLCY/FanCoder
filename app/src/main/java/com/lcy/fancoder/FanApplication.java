package com.lcy.fancoder;

import com.lcy.fancoder.configs.ApplicationConfig;
import com.lcy.fancoder.configs.NetworkConfig;
import com.lcy.fancoder.lifecycles.ApplicationListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Application 生命周期.
 * 程序入口.
 */
public class FanApplication extends App {

    /**
     * 应用程序创建生命周期.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        initConfigs();
    }

    private void initConfigs() {
    }

    /**
     * 获取额外的生命周期处理接口对象.
     * 可用于第三方及程序内部监听应用的生命周期,以便处理相关业务问题.
     */
    @Override
    public List<ApplicationListener> getApplicationCallbacks() {
        List<ApplicationListener> callbacks = new ArrayList<>();
        callbacks.add(new ApplicationListener() {
            @Override
            public void onAppCreate() {
                Logger.t("LifeCycle").d("App Create");
            }

            @Override
            public void onAppToBackground() {
                Logger.t("LifeCycle").d("App Background");
            }

            @Override
            public void onAppToForeground() {
                Logger.t("LifeCycle").d("App Foreground");
            }
        });
        return callbacks;
    }

    /**
     * 获取应用程序配置信息.
     * 用于实现初始化相关库及参数.
     */
    @Override
    public ApplicationConfig getApplicationConfig() {
        ApplicationConfig config = new ApplicationConfig();
        // 开启调试模式,若未开启,则不会打印日志信息.
        config.setDebugModel(true);
        // 设置 HttpClient 配置信息,用于发起网络请求,主要是接口请求.
        config.setHttpClientConfig(new NetworkConfig() {
            @Override
            public OkHttpClient.Builder getOkBuilder() {
                // 添加网络拦截器,用于打印网络请求日志
                return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor());
            }

            @Override
            public Retrofit.Builder getReBuilder() {
                return new Retrofit.Builder().baseUrl("");
            }
        });
        // 设置 FileClient 配置信息,用于发起文件/图片上传和下载请求,主要是非接口请求.
        config.setFileClientConfig(new NetworkConfig() {
            @Override
            public OkHttpClient.Builder getOkBuilder() {
                // 添加网络拦截器,用于打印网络请求日志
                return new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor());
            }

            @Override
            public Retrofit.Builder getReBuilder() {
                return new Retrofit.Builder()
                        // 添加 BaseUrl
                        .baseUrl("")
                        // Gson 转换器
                        .addConverterFactory(GsonConverterFactory.create())
                        // RxJava2 转换器
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }
        });
        return config;
    }

}
