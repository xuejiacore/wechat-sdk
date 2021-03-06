/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core
 * Author: Xuejia
 * Date Time: 2016/4/21 14:46
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.base;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Class Name: AbstractTicketAdapter
 * Create Date: 2016/4/21 14:46
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public abstract class AbstractTicketAdapter implements ITicket {
    Logger logger = Logger.getLogger(ITicket.class);

    /**
     * Access Token
     */
    protected String accessToken = null;
    /**
     * Js Ticket
     */
    protected String jsTicket = null;
    /**
     * Token Expires In
     */
    protected int tokenExpiresIn = 0;
    /**
     * Ticket Expires In
     */
    protected int ticketExpiresIn = 0;

    /**
     * 获得Ticket
     */
    public void obtainTicket() {
        Map<String, Object> ticketData = refreshTicket();
        if (ticketData != null) {
            this.accessToken = (String) ticketData.get(K_ACCESS_TOKEN);
            this.jsTicket = (String) ticketData.get(K_JS_TICKET);
            this.tokenExpiresIn = (Integer) ticketData.get(K_TOKEN_EXPIRES_IN);
            this.ticketExpiresIn = (Integer) ticketData.get(K_TICKET_EXPIRES_IN);
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getJsTicket() {
        return jsTicket;
    }

    public int getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    public int getTicketExpiresIn() {
        return ticketExpiresIn;
    }
}
