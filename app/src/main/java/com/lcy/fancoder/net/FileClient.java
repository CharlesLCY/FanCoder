package com.lcy.fancoder.net;

import com.lcy.fancoder.App;
import com.lcy.fancoder.errors.LocalException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * 文件传输客户端, 用于文件/图片/视频等耗时较长的上传和下载.
 * 在使用该客户端时,不应该设置超时时间,避免弱网情况下,传输速度慢,无法正常上传和下载.
 * <p>
 * 作者 : 吴荣
 * 日期 : 2018-07-24 16:16
 */
@SuppressWarnings({"JavaDoc", "unused"})
public class FileClient {

    /**
     * 单例<文件>客户端.
     */
    private static volatile FileClient instance;

    /**
     * 内置 OkHttpClient 作为网络传输底层;
     */
    private OkHttpClient okHttpClient;

    /**
     * 内置 Retrofit 作为 API 层.
     */
    private Retrofit retrofit;

    /**
     * 私有构造器方法.
     */
    private FileClient() {
        try {
            okHttpClient = App.getInstance().applicationConfig.fileClientConfig.getOkBuilder().build();
            retrofit = App.getInstance().applicationConfig.fileClientConfig.getReBuilder().client(okHttpClient).build();
        } catch (Exception e) {
            throw new LocalException("SMFileClient 初始化失败!!!");
        }
    }

    /**
     * 单例模式.
     * Volatile + 双重锁 + 懒汉单例 + 线程安全.
     *
     * @return 返回<文件>客户端.
     */
    public static FileClient getInstance() {
        if (instance == null) {
            synchronized (FileClient.class) {
                if (instance == null) {
                    instance = new FileClient();
                }
            }
        }
        return instance;
    }

    /**
     * 获取网络传输层.
     *
     * @return 客户端内置的网络传输层.
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 获取 API 层.
     *
     * @return 客户端内置的 API 层.
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * API 辅助方法, 获取 API 层 Retrofit 的 Service 实例对象,用于发起请求.
     *
     * @param service 网络接口类.
     * @param <T>     Service 网络接口实现类.
     * @return Service 网络接口实现类.
     */
    public <T> T get(final Class<T> service) {
        return retrofit.create(service);
    }

    /**
     * 图片上传参数拼装.
     * 为单图和多图提供快速上传途径.
     * 例如:
     *
     * @Multipart
     * @POST("img/upload") Call<T> upload(@PartMap Map<String, RequestBody> params);
     * <p>
     * FileClient.getInstance.get(XXXX.class).upload(FileClient.getInstance.getImageParams(XXX))
     */
    public Map<String, RequestBody> getImageParams(List<String> imgStrs) {
        Map<String, RequestBody> params = new HashMap<>();
        for (String imgStr : imgStrs) {
            File file = new File(imgStr);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            params.put("AttachmentKey\"; filename=\"" + file.getName(), requestFile);
        }
        return params;
    }
}
