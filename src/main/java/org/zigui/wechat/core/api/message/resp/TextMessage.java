/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:20
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

/**
 * Class Name: TextMessage
 * Create Date: 2016/4/20 23:20
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:文本消息
 */
public class TextMessage extends BaseMessage {
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}