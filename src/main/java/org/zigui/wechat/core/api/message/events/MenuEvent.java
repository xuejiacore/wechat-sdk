/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.events
 * Author: Xuejia
 * Date Time: 2016/4/20 22:27
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.events;

/**
 * Class Name: MenuEvent
 * Create Date: 2016/4/20 22:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * Event: CLICK | VIEW
 * <p>
 * 1、点击菜单拉取消息时的事件推送 CLICK
 * 2、点击菜单跳转链接时的事件推送 VIEW
 */
public class MenuEvent {

    /**
     * 1、事件KEY值，与自定义菜单接口中KEY值对应
     * 2、事件KEY值，设置的跳转URL
     */
    private String EventKey = null;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
