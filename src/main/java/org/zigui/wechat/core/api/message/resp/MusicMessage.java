/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

import org.zigui.wechat.core.api.message.resp.entity.Music;

/**
 * Class Name: MusicMessage
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:音乐消息
 */
public class MusicMessage extends BaseMessage {
    /**
     * 音乐对象
     */
    private org.zigui.wechat.core.api.message.resp.entity.Music Music;

    public org.zigui.wechat.core.api.message.resp.entity.Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}