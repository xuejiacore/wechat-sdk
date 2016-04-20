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
import org.zigui.wechat.core.api.base.JsonRequester;
import org.zigui.wechat.core.net.NetworkKit;
import org.zigui.wechat.dxapi.ApiAuthor.JsonSign;
import org.zigui.wechat.dxapi.ApiAuthor.XmlSign;
import org.zigui.wechat.dxapi.WxApi;
import org.zigui.wechat.dxapi.protocol.request.RequestProtocol;
import org.zigui.wechat.dxapi.ApiPropertyUtil;
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
    private static final int API_TYPE_WEIXIN = 1;
    private static final int API_TYPE_MATRICES = 2;
    private static final int API_REQUEST_TYPE = Integer.parseInt(WePropertyUtil.getValue("API_REQUEST_TYPE"));


    public static void main(String[] args) {
        // TODO:token失效时请运行该类重新刷新token
        Ticket.refreshAccessToken(true);
    }

    /**
     * 获取公众号的access_token
     */
    private static final String CGI_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 获取网页js的使用ticket
     */
    private static final String CGI_GET_JS_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" +
            "access_token=%s&type=jsapi";
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
    //TODO
    private static String accessToken = "bCKzWClByaAePVlsEexZfGr5QAyB1a_A6v6a__P76dHrXswm8PnYCHs7oa2_nMslQvRl-4MLCLGm0fQeCgJ0WMR7tBXBSVCDL9ER27LF1qVRI7pyIlNGy13WeTyRWf4eXEOiAGAAHR";
    /**
     * 微信的JS接口
     */
    //TODO
    private static String jsTicket = "sM4AOVdWfPE4DxkXGEs8VFQjvD9jxriOkvKohmSCT1E86KSMSVl7bP-dt2KrUKOCm8IIfe315s8lxE-OrFYalQ";
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
    public static void refreshAccessToken(boolean force) {
        if (force && timer != null) {
            timer.cancel();
            effective = false;
            timer = null;
        } else if (!force && effective) {
            logger.warn("* The current credentials are valid, and this operation is not allowed under the non forced refresh mode!");
            return;
        }
        Ticket.obtainToken();
        Ticket.obtainJsTicket();
        refreshTime = new Date();
        timing();
    }

    /**
     * 获取JsTicket
     */
    private static void obtainJsTicket() {
        String json = null;
        if (API_REQUEST_TYPE == API_TYPE_WEIXIN) {
            json = NetworkKit.sshPost(String.format(CGI_GET_JS_TICKET, accessToken), null);
            logger.debug("* Js Ticket的获取渠道：微信官方接口");
        } else if (API_REQUEST_TYPE == API_TYPE_MATRICES) {
            try {
                RequestProtocol requestProtocol = new RequestProtocol(ApiPropertyUtil.getProperty(ApiPropertyUtil.WX_SYSACC),
                        RequestProtocol.API_GET_TICKET, JsonSign.class);
                json = NetworkKit.jsonPost(WxApi.CGI_GET_TICKET, requestProtocol.toString());
                logger.debug("* Js Ticket的获取渠道：微信矩阵接口");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ObjectMapper om = new ObjectMapper();
        try {
            jsTicket = om.readTree(json).get("ticket").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("* js ticket:【" + jsTicket + "】");
    }

    /**
     * 获取微信token
     */
    private static void obtainToken() {
        String respData;
        JsonNode jsonNode = null;

        String timeKey = API_REQUEST_TYPE == API_TYPE_MATRICES ? "expire_time" : "expires_in";
        if (API_REQUEST_TYPE == API_TYPE_MATRICES) {
            // 使用微信矩阵的接口获取access_token
            try {
                RequestProtocol requestProtocol = new RequestProtocol(ApiPropertyUtil.getProperty(ApiPropertyUtil.WX_SYSACC), RequestProtocol.API_GET_TOKEN, XmlSign.class);
                respData = NetworkKit.xmlPost(WxApi.CGI_GET_TOKEN, requestProtocol.toString());
                ObjectMapper om = new ObjectMapper();
                jsonNode = om.readTree(respData);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.debug("* Token获取渠道：微信矩阵接口");
        } else if (API_REQUEST_TYPE == API_TYPE_WEIXIN) {
            // 使用微信提供的接口获取access_token
            jsonNode = JsonRequester.reqsWith(String.format(CGI_GET_ACCESS_TOKEN, Ticket.getAppId(),
                    Ticket.getAppSecret()), null, JsonRequester.POST);
            logger.debug("* Token获取渠道：微信官方接口");
        }
        if (jsonNode != null) {
            setAccessToken(jsonNode.get("access_token").asText());
            setExpiresIn(jsonNode.get(timeKey).asInt());
            effective = true;
            logger.debug("* Refresh access token!!\taccess_token: 【" + Ticket.getAccessToken() + "】\texpires_in: " + Ticket.getExpiresIn() + " s");
        } else {
            logger.warn("* 请求access_token失败");
        }
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
        Map<String, String> ret = new HashMap<>();
        String json = NetworkKit.sshPost(String.format(CGI_GET_WEB_ACCESS_TOKEN, appId, appSecret, code), null);
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
                refreshAccessToken(false);
            }
            // TODO:为了能够比较准确地获得矩阵平台刷新的token，将延时2s后再重新获取矩阵平台更新后的token
        }, (expiresIn + 2) * 1000);
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
