/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp.entity;

/**
 * Class Name: Video
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class Video {
    /**
     * 通过素材管理接口上传多媒体文件，得到的id    NOT NULL
     */
    private String MediaId = null;
    /**
     * 视频消息的标题
     */
    private String Title = null;
    /**
     * 视频消息的描述
     */
    private String Description = null;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

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
}
