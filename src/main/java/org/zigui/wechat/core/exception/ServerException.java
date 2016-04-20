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
 * Class Name: ServerException2
 * Create Date: 2016/4/20 22:33
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:服务器反馈的异常
 */
public class ServerException extends WeChatException {
    public ServerException(String jsonMsg) {
        super(jsonMsg);
    }

    public ServerException(JsonNode jsonNode) {
        super(jsonNode);
    }

    public ServerException(String title, String jsonMsg) {
        super(title, jsonMsg);
    }
}