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

    protected String accessToken = null;
    protected String jsTicket = null;
    protected int tokenExpiresIn = 0;
    protected int ticketExpiresIn = 0;

    public AbstractTicketAdapter() {
        Map<String, Object> ticketData = refreshTicket();
        if (ticketData != null) {
            this.accessToken = (String) ticketData.get(K_ACCESS_TOKEN);
            this.jsTicket = (String) ticketData.get(K_JS_TICKET);
            this.tokenExpiresIn = (int) ticketData.get(K_TOKEN_EXPIRES_IN);
            this.ticketExpiresIn = (int) ticketData.get(K_TICKET_EXPIRES_IN);
        }
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getJsTicket() {
        return jsTicket;
    }

    @Override
    public int getTokenExpiresIn() {
        return tokenExpiresIn;
    }

    @Override
    public int getTicketExpiresIn() {
        return ticketExpiresIn;
    }
}
