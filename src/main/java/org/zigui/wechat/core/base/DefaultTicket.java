/**
 * Project: WeChat
 * Package Name: org.zigui.wechat
 * Author: Xuejia
 * Date Time: 2016/4/21 14:56
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.base.JsonRequester;
import org.zigui.wechat.core.net.NetworkKit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: DefaultTicket
 * Create Date: 2016/4/21 14:56
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class DefaultTicket extends AbstractTicketAdapter {

    public Map<String, Object> refreshTicket() {
        Map<String, Object> data = new HashMap<String, Object>();

        // 使用微信提供的接口获取access_token
        JsonNode jsonNode = JsonRequester.reqsWith(String.format(CGI_GET_ACCESS_TOKEN, Ticket.getAppId(),
                Ticket.getAppSecret()), null, JsonRequester.POST);
        if (jsonNode != null) {
            data.put(K_ACCESS_TOKEN, jsonNode.get("access_token").asText());
            data.put(K_TOKEN_EXPIRES_IN, jsonNode.get("expires_in").asInt());
        } else {
            logger.warn("* 请求access_token失败");
        }

        String json = NetworkKit.sshPost(String.format(CGI_GET_JS_TICKET, data.get(K_ACCESS_TOKEN)), null);
        ObjectMapper om = new ObjectMapper();
        try {
            data.put(K_JS_TICKET, om.readTree(json).get("ticket").asText());
            data.put(K_TICKET_EXPIRES_IN, om.readTree(json).get("expires_in").asInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
