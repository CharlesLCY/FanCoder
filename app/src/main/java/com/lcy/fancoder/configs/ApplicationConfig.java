package com.lcy.fancoder.configs;

/**
 * 应用程序配置类, 用于配置框架运行的基本信息.
 * <p>
 * 作者 : 吴荣
 * 日期 : 2018-08-27 20:45
 */
@SuppressWarnings({"JavaDoc", "WeakerAccess", "UnusedReturnValue"})
public class ApplicationConfig {

    /**
     * Debug 开关.
     */
    public boolean isDebugModel;

    /**
     * API 客户端配置信息.
     */
    public NetworkConfig httpClientConfig;

    /**
     * File 文件客户端配置信息.
     */
    public NetworkConfig fileClientConfig;

    /**
     * 设置开发调试开关.
     *
     * @param isDebugModel 是否为开发调试模式.
     * @return 应用程序配置类.
     */
    public ApplicationConfig setDebugModel(boolean isDebugModel) {
        this.isDebugModel = isDebugModel;
        return this;
    }

    /**
     * 设置 API 客户端配置信息.
     *
     * @param httpClientConfig API 网络客户端配置信息.
     * @return 应用程序配置类.
     */
    public ApplicationConfig setHttpClientConfig(NetworkConfig httpClientConfig) {
        this.httpClientConfig = httpClientConfig;
        return this;
    }

    /**
     * 设置 File 客户端配置信息.
     *
     * @param fileClientConfig File 文件客户端网络配置信息.
     * @return 应用程序配置类.
     */
    public ApplicationConfig setFileClientConfig(NetworkConfig fileClientConfig) {
        this.fileClientConfig = fileClientConfig;
        return this;
    }
}
