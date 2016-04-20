/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 23:18
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp.entity;

/**
 * Class Name: Article
 * Create Date: 2016/4/20 23:18
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:图文消息实体
 */
public class Article {
    /**
     * 图文消息的名称
     */
    private String Title;
    /**
     * 图文消息的描述
     */
    private String Description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String PicUrl;
    /**
     * 点击图文消息跳转的链接
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return null == PicUrl ? "" : PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return null == Url ? "" : Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

}
