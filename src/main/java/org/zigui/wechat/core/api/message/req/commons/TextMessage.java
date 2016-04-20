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
 * Class Name: TextMessage
 * Create Date: 2016/4/20 23:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:文本消息
 */
public class TextMessage extends BaseMsg {
    /**
     * 消息的内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}