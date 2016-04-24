/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.ipaddress
 * Author: Xuejia
 * Date Time: 2016/4/20 22:19
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.ipaddress;

import com.google.gson.Gson;
import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.net.NetworkKit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class Name: WeServerIp
 * Create Date: 2016/4/20 22:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:微信服务器IP工具类
 */
public class WeServerIp implements IObtainResult {

    /**
     * {"ip_list":["127.0.0.1","127.0.0.1"]}
     */
    private static final String GET_SERV_IPS = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";

    /**
     * 获取微信服务器的IP地址集合
     * <p>
     * 通过此API返回的结果为一个String数组，该数组中包含了微信服务器的IP地址
     */
    public static final String API_GET_IP_LIST = "IP_LIST";

    private List<String> ip_list = null;

    private List<String> excludeIp = null;

    /**
     * 获取被允许的微信服务器列表
     *
     * @return 返回服务器IP列表
     */
    public List<String> getAllowIPs() {
        if (this.ip_list != null && this.excludeIp != null) {
            List<String> totalIPs = new ArrayList<String>();
            Collections.copy(totalIPs, this.ip_list);
            totalIPs.removeAll(this.excludeIp);
            return totalIPs;
        } else {
            return this.ip_list;
        }
    }

    public List<String> getIp_list() {
        return ip_list;
    }

    public void setIp_list(List<String> ip_list) {
        this.ip_list = ip_list;
    }

    public List<String> getExcludeIp() {
        return excludeIp;
    }

    public void setExcludeIp(List<String> excludeIp) {
        this.excludeIp = excludeIp;
    }

    public Object getResult(String apiName, Map<String, Object> params) {
        if (API_GET_IP_LIST.equals(apiName)) {
            String jsonResult = NetworkKit.sshPost(String.format(GET_SERV_IPS, Ticket.getAccessToken()), null);
            Gson gson = new Gson();
            return gson.fromJson(jsonResult, WeServerIp.class).getIp_list();
        }
        return null;
    }

    @Override
    public String necessaryParameter(String apiName) {
        return null;
    }
}
