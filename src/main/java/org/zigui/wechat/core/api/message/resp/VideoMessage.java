/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:20
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

import org.zigui.wechat.core.api.message.resp.entity.Video;

/**
 * Class Name: VideoMessage
 * Create Date: 2016/4/20 23:20
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:视频消息
 */
public class VideoMessage extends BaseMessage {
    private org.zigui.wechat.core.api.message.resp.entity.Video Video = null;

    public org.zigui.wechat.core.api.message.resp.entity.Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
