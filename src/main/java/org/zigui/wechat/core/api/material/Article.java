/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.material
 * Author: Xuejia
 * Date Time: 2016/4/20 22:20
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.material;

/**
 * Class Name: Article
 * Create Date: 2016/4/20 22:20
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:素材的图文实体
 */
public class Article {
    // 图文消息标题
    private String title = null;
    // 图文消息的封面图片素材id（必须是永久mediaID）
    private String thumb_media_id = null;
    // 图文消息的作者
    private String author = null;
    // 但图文消息的摘要
    private String digest = null;
    // 是否作为封面（0 false 1 true）
    private int show_cover_pic = 0;
    // 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
    private String content = null;
    // 图文消息的原文地址
    private String content_source_url = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(int show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }
}
