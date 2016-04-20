/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.req.commons
 * Author: Xuejia
 * Date Time: 2016/4/20 23:14
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.req.commons;

import org.zigui.wechat.core.api.message.req.BaseMsg;

/**
 * Class Name: LocationMessage
 * Create Date: 2016/4/20 23:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:地理位置消息
 */
public class LocationMessage extends BaseMsg {
    /**
     * 地理位置纬度
     */
    private String Location_X;
    /**
     * 地理位置经度
     */
    private String Location_Y;
    /**
     * 地图的缩放大小
     */
    private String Scale;
    /**
     * 地理位置信息
     */
    private String Label;

    public String getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(String location_X) {
        Location_X = location_X;
    }

    public String getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(String location_Y) {
        Location_Y = location_Y;
    }

    public String getScale() {
        return Scale;
    }

    public void setScale(String scale) {
        Scale = scale;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}
