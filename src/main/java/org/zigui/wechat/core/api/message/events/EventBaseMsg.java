/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.events
 * Author: Xuejia
 * Date Time: 2016/4/20 22:26
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.events;

import org.zigui.wechat.core.api.message.req.BaseMsg;

/**
 * Class Name: EventBaseMsg
 * Create Date: 2016/4/20 22:26
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * 目前已有的事件消息类型有：
 * <p>                                          Event
 * 1、关注/取消关注事件                         subscribe | unsubscribe
 * 2、扫描带参数二维码事件                      subscribe | SCAN
 * 3、上报地理位置事件                          LOCATION
 * 4、点击菜单拉取消息时的事件推送              CLICK
 * 5、点击菜单跳转链接时的事件推送              VIEW
 * <p>
 * <p>
 * 事件推送消息
 */
public class EventBaseMsg extends BaseMsg {
    /**
     * 事件的类型
     */
    private String Event = null;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
