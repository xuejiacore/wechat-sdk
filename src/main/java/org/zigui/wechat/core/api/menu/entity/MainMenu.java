/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.menu.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 22:22
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.menu.entity;

/**
 * Class Name: MainMenu
 * Create Date: 2016/4/20 22:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:一级菜单，能够包含有二级子菜单
 */
public class MainMenu extends Button {
    /**
     * 二级子菜单
     */
    private Button[] sub_button = null;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}

