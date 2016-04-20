/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp.entity;

/**
 * Class Name: Music
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:音乐model
 */
public class Music {
    /**
     * 音乐的名称
     */
    private String Title;
    /**
     * 音乐的描述
     */
    private String Description;
    /**
     * 音乐的链接
     */
    private String MusicUrl;
    /**
     * 高质量音乐的连接，WIFI 环境下有限使用该连接播放音乐
     */
    private String HQMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
     */
    private String ThumbMediaId = null;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String musicUrl) {
        HQMusicUrl = musicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
