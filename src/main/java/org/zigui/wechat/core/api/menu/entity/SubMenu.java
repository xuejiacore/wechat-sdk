/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.menu.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 22:22
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.menu.entity;

/**
 * Class Name: SubMenu
 * Create Date: 2016/4/20 22:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:二级子菜单
 */
public class SubMenu extends Button {
    /**
     * 菜单的响应动作类型
     */
    private String type = null;
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     * click等点击类型必须
     */
    private String key = null;
    /**
     * 网页链接，用户点击菜单可打开链接，不超过256字节
     * view类型必须
     */
    private String url = null;
    /**
     * 调用新增永久素材接口返回的合法media_id
     * media_id类型和view_limited类型必须
     */
    private String media_id = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }
}
