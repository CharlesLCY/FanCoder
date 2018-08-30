package com.lcy.fancoder.beans.banner;

/**
 * 轮播实体类.
 * 用于 BannerView 中存放数据.
 * <p>
 * 作者 : 吴荣
 * 日期 : 2018-08-27 20:26
 */
@SuppressWarnings("WeakerAccess")
public class Banner {

    /**
     * 轮播 ID.
     */
    public long bannerId;

    /**
     * 轮播图片地址.
     */
    public String bannerImageUrl;

    /**
     * 轮播描述.
     */
    public String bannerDesc;

    /**
     * 基本构造器.
     *
     * @param bannerImageUrl Banner 的图片链接.
     */
    public Banner(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    /**
     * 完整构造器.
     *
     * @param bannerId       Banner 的唯一 Id ,用于区分点击事件/分发处理等.
     * @param bannerImageUrl Banner 的图片链接.
     * @param bannerDesc     Banner 的图片描述,可用于显示底部半透明文案等.
     */
    public Banner(long bannerId, String bannerImageUrl, String bannerDesc) {
        this.bannerId = bannerId;
        this.bannerImageUrl = bannerImageUrl;
        this.bannerDesc = bannerDesc;
    }
}
