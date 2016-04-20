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
 * Class Name: LinkMessage
 * Create Date: 2016/4/20 23:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:链接消息
 */
public class LinkMessage extends BaseMsg {
    /**
     * 消息的标题
     */
    private String Title;
    /**
     * 消息的描述
     */
    private String Description;
    /**
     * 消息的链接
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

