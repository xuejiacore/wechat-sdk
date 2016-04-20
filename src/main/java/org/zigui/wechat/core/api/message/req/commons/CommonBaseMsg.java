/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.req.commons
 * Author: Xuejia
 * Date Time: 2016/4/20 23:13
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.req.commons;

import org.zigui.wechat.core.api.message.req.BaseMsg;

/**
 * Class Name: CommonBaseMsg
 * Create Date: 2016/4/20 23:13
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description: 接收的普通消息
 * <p>
 * 1、文本消息           text
 * 2、图片消息           image
 * 3、语音消息           voice
 * 4、视频消息           video
 * 5、小视频消息         shortvideo
 * 6、地理位置消息       location
 * 7、链接消息           link
 * <p>
 * <p>
 */
public class CommonBaseMsg extends BaseMsg {
    /**
     * 消息的id（64位整型），如果是事件没有消息ID
     */
    private long MsgId;

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}