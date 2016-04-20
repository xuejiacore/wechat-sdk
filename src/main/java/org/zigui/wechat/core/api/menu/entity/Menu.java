/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.menu.entity
 * Author: Xuejia
 * Date Time: 2016/4/20 22:22
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.menu.entity;

/**
 * Class Name: Menu
 * Create Date: 2016/4/20 22:22
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:菜单，每个菜单对象中之多只有三个一级菜单
 */
public class Menu {
    private Button[] button = null;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
