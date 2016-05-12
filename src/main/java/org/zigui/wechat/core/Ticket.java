/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core
 * Author: Xuejia
 * Date Time: 2016/4/20 22:37
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.zigui.wechat.core.base.DefaultTicket;
import org.zigui.wechat.core.base.ITicket;
import org.zigui.wechat.core.net.NetworkKit;
import org.zigui.wechat.util.WePropertyUtil;

import java.io.IOException;
import java.util.*;

/**
 * Class Name: Ticket
 * Create Date: 2016/4/20 22:37
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:WeChat的常用参数
 */
public class Ticket {
    private static Logger logger = Logger.getLogger(Ticket.class.getName());

    private static final int ALPHA = 1;                     // 刷新token以及ticket的周期因素
    private static ITicket iTicket = null;


    /**
     * 获取网页授权后的access_token
     */
    private static final String CGI_GET_WEB_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 公众号ID
     */
    private static String appId = WePropertyUtil.getValue("WECHAT_APPID");
    /**
     * 公众号预设token
     */
    private static String token = WePropertyUtil.getValue("WECHAT_TOKEN");
    /**
     * 公众号的安全码
     */
    private static String appSecret = WePropertyUtil.getValue("WECHAT_SECRET");
    /**
     * 通过验证后获得access_token
     */
    private static String accessToken = "zj8KCihcy2C4UpeU9-c1r3jmsY_RxFKbQUZI4VuoRJx9kE1-oSoMhQlCOwuOBooGzqdavynqXj14JOxcNB8WPpFlY_yUDBWWJV_ffdl0UOr88SacFZOWleJeqJm9FtH9COGhAFALXP";
    /**
     * 微信的JS接口
     */
    private static String jsTicket = "sM4AOVdWfPE4DxkXGEs8VFQjvD9jxriOkvKohmSCT1FIt1PNtJHTXWchLODMOMqdvw_rGkYtVC9Oz1B8v0DPLg";
    /**
     * access_token的有效时长，默认为7200s
     */
    private static int expiresIn = 0;
    /**
     * 当前的access_token是否有效
     * TODO:在生产环境中需要将改值直接设置为false
     */
    private static boolean effective = "true".equals(WePropertyUtil.getValue("TOKEN_EFFECTIVE"));
    private static Timer timer = null;
    private static Date refreshTime = null;

    /**
     * 刷新 access_token 凭据
     * 注意，该刷新不管原有的 access_token 是否有效，都会请求新的票据，微信有获取 access_token 次数限制！！
     *
     * @param force 是否进行强制刷新
     */
    public static void refreshTickets(boolean force) {
        if (force && timer != null) {
            timer.cancel();
            effective = false;
            timer = null;
        } else if (!force && effective) {
            logger.warn("* The current credentials are valid, and this operation is not allowed under the non forced refresh mode!");
            return;
        }
        if (iTicket == null) {
            iTicket = new DefaultTicket();
            logger.debug("* 默认方式获取");
        }
        iTicket.obtainTicket();

        setAccessToken(iTicket.getAccessToken());
        setExpiresIn(iTicket.getTokenExpiresIn());
        effective = true;
        logger.debug("* Refresh access token!!\taccess_token: 【" + Ticket.getAccessToken() + "】\texpires_in: " + Ticket.getExpiresIn() + " s");

        jsTicket = iTicket.getJsTicket();
        logger.debug("* JS ticket:【" + jsTicket + "】");
        refreshTime = new Date();
        timing();
    }

    /**
     * 使用一个Ticket接口获得相关参数信息
     *
     * @param force   是否进行强制刷新
     * @param iTicket 处理后的Ticket必要参数
     */
    public static void refreshTickets(boolean force, ITicket iTicket) {
        Ticket.iTicket = iTicket;
        if (iTicket.getAccessToken() == null
                || iTicket.getTokenExpiresIn() == 0
                || iTicket.getJsTicket() == null
                || iTicket.getTicketExpiresIn() == 0)
            try {
                throw new Exception("请提供完整的参数信息");
            } catch (Exception e) {
                e.printStackTrace();
            }
        refreshTickets(force);
    }

    /**
     * 获得网页授权的token
     *
     * @param code 授权回传的code
     * @return 返回包含有 access_token, refresh_token, openid内容的Map对象
     */
    public static Map<String, String> getWebAccessToken(String code) {
        if (code == null) {
            return null;
        }
        Map<String, String> ret = new HashMap<String, String>();
        String  json = NetworkKit.sshPost(String.format(CGI_GET_WEB_ACCESS_TOKEN, appId, appSecret, code), null);
        if (!json.contains("scope")) {
            return null;
        }
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = om.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert jsonNode != null;
        ret.put("access_token", jsonNode.get("access_token").asText());
        ret.put("refresh_token", jsonNode.get("refresh_token").asText());
        ret.put("openid", jsonNode.get("openid").asText());

        logger.debug("* web access info:【" + String.join(",", ret.values()) + "】");
        return ret;
    }

    /**
     * 根据获得的 access_token 有效时长，在有效时长的 120s 以前进行 access_token 的刷新操作
     */
    public static void timing() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                effective = false;
                refreshTickets(false);
            }
        }, (expiresIn + ALPHA) * 1000);
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        Ticket.appId = appId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Ticket.token = token;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static void setAppSecret(String appSecret) {
        Ticket.appSecret = appSecret;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        Ticket.accessToken = accessToken;
    }

    public static String getJsTicket() {
        return jsTicket;
    }

    public static void setJsTicket(String jsTicket) {
        Ticket.jsTicket = jsTicket;
    }

    public static int getExpiresIn() {
        return expiresIn;
    }

    public static void setExpiresIn(int expiresIn) {
        Ticket.expiresIn = expiresIn;
    }

    public static boolean isEffective() {
        return effective;
    }

    public static void setEffective(boolean effective) {
        Ticket.effective = effective;
    }

    public static Date getRefreshTime() {
        return refreshTime;
    }

    public static void setRefreshTime(Date refreshTime) {
        Ticket.refreshTime = refreshTime;
    }
}
