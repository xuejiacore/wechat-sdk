/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.account
 * Author: Xuejia
 * Date Time: 2016/4/20 22:11
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.account;


import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.WechatAPI;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.exception.WeChatException;
import org.zigui.wechat.core.net.NetworkKit;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: WeAccAPI
 * Create Date: 2016/4/20 22:11
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:账号管理API封装
 * 支持功能：
 * 1、生成带参数的二维码
 * 2、长链接转短链接接口
 */
public class WeAccAPI implements IObtainResult {

    /**
     * http请求方式: POST
     * 创建一个带场景值的二维码
     */
    private static final String CREATE_QR_SCENCE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

    /**
     * 获取场景值对应的二维码
     * 参数：
     * expire_seconds 有效时长（如果包含这个参数，那么创建临时二维码）
     * scene_id 场景值
     * <p>
     * 返回值：
     * ticket 	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     * expire_seconds 	该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）。
     * url 	二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    public static final String API_CREATE_QR = "CREATE";
    // 获取临时二维码的post数据模板
    private static final String qrPostTmpStr = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
    // 获取永久二维码的post数据模板
    private static final String qrPostLimitStr = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";

    /**
     * HTTP GET请求（请使用https协议）
     * 提醒：TICKET记得进行UrlEncode
     */
    private static final String GET_QR_VIA_TICKET = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
    public static final String API_GET_QR = "GETQR";

    private static final String CGI_LONG_TO_SHORT = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=%s";
    public static final String API_LONG_2_SHORT = "L2S";

    static {
        paramInfo.put(API_CREATE_QR, "1/2P:[expire_seconds(Integer)] & scene_id(Integer)");
        paramInfo.put(API_GET_QR, "1P: ticket(String)");
        paramInfo.put(API_LONG_2_SHORT, "1P: long_url(String)");
    }

    public Object getResult(String apiName, Map<String, Object> params) {
        if (API_CREATE_QR.equals(apiName)) {
            if (params.containsKey("expire_seconds")) {
                return this.createTmpQR((Integer) params.get("expire_seconds"), (Integer) params.get("scene_id"));
            } else {
                return this.createLimitQR((Integer) params.get("scene_id"));
            }
        } else if (API_GET_QR.equals(apiName)) {
            return this.getQR((String) params.get("ticket"));
        } else if (API_LONG_2_SHORT.equals(apiName)) {
            return this.long2Short((String) params.get("long_url"));
        } else {
            return null;
        }
    }

    @Override
    public String necessaryParameter(String apiName) {
        return paramInfo.get(apiName);
    }

    private Object long2Short(String longUrl) {
        return NetworkKit.sshPostJson(String.format(CGI_LONG_TO_SHORT, Ticket.getAccessToken()),
                "{\"action\":\"long2short\",\"long_url\":\"" + longUrl + "\"}");
    }

    /**
     * 使用ticket换取二维码图片
     *
     * @param ticket 换取的ticket
     * @return 返回获得到的二维码图片信息
     * ticket正确情况下，http 返回码是200，是一张图片，可以直接展示或者下载。
     */
    private Object getQR(String ticket) {
        return String.format(GET_QR_VIA_TICKET, ticket);
    }

    /**
     * 创建永久的二维码
     *
     * @param sceneId 二维码的场景值
     * @return 如果操作成功，返回二维码对应的信息
     */
    private Object createLimitQR(Integer sceneId) {
        return NetworkKit.sshPostJson(String.format(CREATE_QR_SCENCE, Ticket.getAccessToken()),
                String.format(qrPostLimitStr, sceneId));
    }

    /**
     * 创建临时二维码
     *
     * @param expireSeconds 临时二维码的有效时长
     * @param sceneId       场景值
     * @return 如果操作成功，返回二维码对应的信息
     */
    private Object createTmpQR(Integer expireSeconds, Integer sceneId) {
        return NetworkKit.sshPostJson(String.format(CREATE_QR_SCENCE, Ticket.getAccessToken()),
                String.format(qrPostTmpStr, expireSeconds, sceneId));
    }

    public static void main(String[] args) {
        Ticket.refreshTickets(true);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("expire_seconds", 300);
        params.put("scene_id", 1);
        params.put("ticket", "gQF_8DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLzFrd0gyYXptVU5JcGdyUjRRbUxHAAIEEv2cVgMELAEAAA%3D%3D");
        try {
            WechatAPI.getResult(WeAccAPI.class, WeAccAPI.API_CREATE_QR, params);
        } catch (WeChatException e) {
            e.printStackTrace();
        }
    }
}

