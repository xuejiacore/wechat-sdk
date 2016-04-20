/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.menu.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 22:22
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.menu.entity;

/**
 * Class Name: Button
 * Create Date: 2016/4/20 22:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:菜单按钮的基类
 */
public class Button {
    /**
     * 菜单标题，不超过16个字节，子菜单不超过40个字节
     */
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}