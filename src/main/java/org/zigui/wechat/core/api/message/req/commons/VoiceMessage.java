/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.req.commons
 * Author: Xuejia
 * Date Time: 2016/4/20 23:14
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.req.commons;

import org.zigui.wechat.core.api.message.req.BaseMsg;

/**
 * Class Name: VoiceMessage
 * Create Date: 2016/4/20 23:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:音频消息
 */
public class VoiceMessage extends BaseMsg {
    /**
     * 媒体的ID
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}