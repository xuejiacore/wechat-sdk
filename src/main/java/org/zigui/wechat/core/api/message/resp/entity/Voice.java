/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp.entity;

/**
 * Class Name: Voice
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class Voice {
    /**
     * 通过素材管理接口上传多媒体文件，得到的id。
     */
    private String MediaId = null;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
