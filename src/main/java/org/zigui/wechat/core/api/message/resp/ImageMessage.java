/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

import org.zigui.wechat.core.api.message.resp.entity.Image;

/**
 * Class Name: ImageMessage
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:回复图片消息
 */
public class ImageMessage extends BaseMessage {
    /**
     * 图片
     */
    private org.zigui.wechat.core.api.message.resp.entity.Image Image = null;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
