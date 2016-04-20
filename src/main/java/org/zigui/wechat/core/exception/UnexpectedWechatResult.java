/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.exception
 * Author: Xuejia
 * Date Time: 2016/4/20 22:33
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.exception;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Class Name: UnexpectedWechatResult2
 * Create Date: 2016/4/20 22:33
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class UnexpectedWechatResult extends WeChatException {
    public UnexpectedWechatResult(String jsonMsg) {
        super(jsonMsg);
    }

    public UnexpectedWechatResult(JsonNode jsonNode) {
        super(jsonNode);
    }

    public UnexpectedWechatResult(String title, String jsonMsg) {
        super(title, jsonMsg);
    }
}