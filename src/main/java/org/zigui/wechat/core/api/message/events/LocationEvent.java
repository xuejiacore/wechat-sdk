/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.events
 * Author: Xuejia
 * Date Time: 2016/4/20 22:27
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.events;

/**
 * Class Name: LocationEvent
 * Create Date: 2016/4/20 22:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * Event: LOCATION
 */
public class LocationEvent extends EventBaseMsg {
    /**
     * 地理位置纬度
     */
    private String Latitude = null;
    /**
     * 地理位置经度
     */
    private String Longitude = null;
    /**
     * 地理位置精度
     */
    private String Precision = null;

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }
}
