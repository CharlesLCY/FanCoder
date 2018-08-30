package com.lcy.fancoder.net;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.lcy.fancoder.App;

import java.io.InputStream;

/**
 * @Project 四川水木科技移动应用基础库.
 * @Describe Glide Module 注入. 用于配置图片相关信息, 控制缓存大小.
 * @Author 吴荣
 * @Date 2018-07-24
 * @Time 16:16
 * @Copyright 2018 www.scshuimukeji.cn Inc. All rights reserved.
 * 注意: 本内容仅限于四川水木科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
@GlideModule
@SuppressWarnings({"JavaDoc", "unused"})
public class GlideClient extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(FileClient.getInstance().getOkHttpClient()));
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        // 设置 Glide 内存缓存为 10 MB.
        int memoryCacheSizeBytes = 1024 * 1024 * 10; // 10mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        // 设置 Glide Bitmap 对象复用池为 20 MB.
        int bitmapPoolSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setBitmapPool(new LruBitmapPool(bitmapPoolSizeBytes));
        // 设置 Glide 本地磁盘缓存 Caches 为 100 MB.
        int diskCacheSizeBytes = 1024 * 1024 * 100;  // 100 MB
        builder.setDiskCache(new InternalCacheDiskCacheFactory(App.getInstance().getApplicationContext(), diskCacheSizeBytes));
        // 设置 Glide 日志级别
        builder.setLogLevel(App.getInstance().applicationConfig.isDebugModel ? Log.VERBOSE : Log.ERROR);
    }
}
