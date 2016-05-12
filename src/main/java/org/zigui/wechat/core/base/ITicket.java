/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core
 * Author: Xuejia
 * Date Time: 2016/4/21 14:44
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.base;

import java.util.Map;

/**
 * Class Name: ITicket
 * Create Date: 2016/4/21 14:44
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public interface ITicket {
    String K_ACCESS_TOKEN = "TOKEN";
    String K_JS_TICKET = "TICKET";
    String K_TOKEN_EXPIRES_IN = "TOKEN_TIME";
    String K_TICKET_EXPIRES_IN = "TICKET_TIME";

    /**
     * 获取网页js的使用ticket
     */
    String CGI_GET_JS_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" +
            "access_token=%s&type=jsapi";

    /**
     * 获取公众号的access_token
     */
    String CGI_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=%s&secret=%s";

    /**
     * 获得access_token
     *
     * @return access_token
     */
    String getAccessToken();

    /**
     * 获得JsTicket
     *
     * @return JsTicket
     */
    String getJsTicket();

    /**
     * 获得AccessToken的有效时长
     *
     * @return 获得AccessToken的有效时长
     */
    int getTokenExpiresIn();

    /**
     * 获得JsTicket的有效时长
     *
     * @return 获得JsTicket的有效时长
     */
    int getTicketExpiresIn();

    /**
     * Ticket的刷新操作
     *
     * @return 刷新Ticket，返回的参数
     */
    Map<String, Object> refreshTicket();

    /**
     * 获得Ticket
     */
    void obtainTicket();
}
