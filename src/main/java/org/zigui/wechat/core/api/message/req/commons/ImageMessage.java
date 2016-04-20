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
 * Class Name: ImageMessage
 * Create Date: 2016/4/20 23:13
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:图片消息
 */
public class ImageMessage extends BaseMsg {
    /**
     * 图片的链接
     */
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}