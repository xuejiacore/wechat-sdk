/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.user
 * Author: Xuejia
 * Date Time: 2016/4/20 22:31
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.user;

import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.WechatAPI;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.exception.WeChatException;
import org.zigui.wechat.core.net.NetworkKit;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: WeUserAPI
 * Create Date: 2016/4/20 22:31
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description: 微信用户API
 * <p>
 * 参见官方API文档：http://mp.weixin.qq.com/wiki/12/54773ff6da7b8bdc95b7d2667d84b1d4.html
 * <p>
 */
public class WeUserAPI implements IObtainResult {

    /**
     * 获取用户列表
     * http请求方式: GET（请使用https协议）
     * <p>
     * next_openid 第一个拉取的OPENID，不填默认从头开始拉取
     */
    private static final String GET_SUER_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
    /**
     * 获取已经关注公众号的用户信息
     * 参数：
     * nextOpenId 下一个用户的OpenId，如果不填，默认从第一个开始拉取（一次最多能够拉去10000个用户的列表）
     * <p>
     * 返回值：json
     * total 	关注该公众账号的总用户数
     * count 	拉取的OPENID个数，最大值为10000
     * data 	列表数据，OPENID的列表
     * next_openid 	拉取列表的最后一个用户的OPENID
     */
    public static final String API_LIST_USERS = "LIST";

    /**
     * http请求方式: POST（请使用https协议）
     */
    private static final String SET_USER_REMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";

    /**
     * 根据用户的设置用户的备注名称
     * 参数：
     * openid 用户的openId
     * remark 需要设置的备注名称
     * 返回值：
     * 返回操作状态
     */
    public static final String API_USER_REMARK = "REMARK";

    /**
     * http请求方式: GET
     * 获取用户的基本信息（如果在多个公众号中需要共享用户信息，那么需要在开放平台中使用UnionId进行标记）
     */
    private static final String GET_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 根据用户的openid获取用户的信息
     * 参数：
     * openid 用户的openId
     * 返回值：
     * 返回用户的信息Json数据
     */
    public static final String API_USER_INFO = "INFO";

    /**
     * 批量拉去用户的信息
     */
    private static final String GET_USER_INFO_BAT = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=%s";
    /**
     * 根据提供的openid数组列表，批量拉去用户的信息
     * 参数：
     * openid[] 需要拉去的用户openId数组
     * 返回值：
     * 批量用户Json数据
     */
    public static final String API_BAT_INFO = "BAT";

    public Object getResult(String apiName, Map<String, Object> params) {
        if (API_LIST_USERS.equals(apiName)) {
            return this.listUsers((String) params.get("openid"));
        } else if (API_USER_REMARK.equals(apiName)) {
            return this.remarkUser((String) params.get("openid"), (String) params.get("remark"));
        } else if (API_USER_INFO.equals(apiName)) {
            return this.getUserInfo((String) params.get("openid"));
        } else if (API_BAT_INFO.equals(apiName)) {
            return this.getUserInfoBat((String[]) params.get("openids"));
        } else {
            return null;
        }
    }

    /**
     * 批量拉去用户的信息
     *
     * @param openids 用户的openid数组
     * @return 返回批量用户信息的json数据
     */
    private Object getUserInfoBat(String[] openids) {
        StringBuilder postStr = new StringBuilder("{\"user_list\":[");
        for (String openid : openids) {
            postStr.append("{\"openid\":\"").append(openid).append("\",\"lang\":\"zh-CN\"},");
        }
        postStr.deleteCharAt(postStr.length() - 1);
        postStr.append("]}");
        return NetworkKit.sshPostJson(String.format(GET_USER_INFO_BAT, Ticket.getAccessToken()), postStr.toString());
    }

    /**
     * 根据用户的openid获取用户的信息
     *
     * @param openid 需要获取的用户对象的openid
     * @return 返回用户的基本信息json数据
     */
    private Object getUserInfo(String openid) {
        return NetworkKit.sshPost(String.format(GET_USER_INFO, Ticket.getAccessToken(), openid), null);
    }

    /**
     * 获取用户列表（最多能够获取1000个用户列表）
     *
     * @param nextOpenId 下一个用户的openId，不填默认从第一个开始
     * @return 返回用户列表数据
     * <p>
     * e.g.
     * {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
     * total 	关注该公众账号的总用户数
     * count 	拉取的OPENID个数，最大值为10000
     * data 	列表数据，OPENID的列表
     * next_openid 	拉取列表的最后一个用户的OPENID
     */
    public Object listUsers(String nextOpenId) {
        return NetworkKit.sshPost(String.format(GET_SUER_LIST, Ticket.getAccessToken(), nextOpenId), null);
    }

    /**
     * 设置用户的备注名称
     *
     * @param openId 需要进行设置的用户的openId
     * @param remark 需要进行设置的备注名称
     * @return 返回操作的结果
     */
    public Object remarkUser(String openId, String remark) {
        return NetworkKit.sshPostJson(String.format(SET_USER_REMARK, Ticket.getAccessToken()), "{\"openid\":\"" + openId +
                "\",\"remark\":\"" + remark + "\"}");
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", "oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk");
        params.put("remark", "言子圭");
        params.put("openids", new String[]{"oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk"});
        try {
            WechatAPI.getResult(WeUserAPI.class, WeUserAPI.API_BAT_INFO, params);
        } catch (WeChatException e) {
            e.printStackTrace();
        }
    }
}
