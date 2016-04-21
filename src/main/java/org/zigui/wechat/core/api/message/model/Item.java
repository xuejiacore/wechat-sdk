/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.model
 * Author: Xuejia
 * Date Time: 2016/4/20 23:11
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.model;

/**
 * Class Name: Item
 * Create Date: 2016/4/20 23:11
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class Item {
    /**
     * 模板的数据
     */
    private String value = null;
    /**
     * 模板消息的颜色：十六进制如：#0099cc
     */
    private String color = null;

    public Item(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public Item(String value) {
        this(value, "#000000");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}