/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.model
 * Author: Xuejia
 * Date Time: 2016/4/20 23:11
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.model;

import com.google.gson.Gson;
import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.WechatAPI;
import org.zigui.wechat.core.net.NetworkKit;

import java.util.Map;

/**
 * Class Name: ModelMsg
 * Create Date: 2016/4/20 23:11
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:模板消息的API封装
 */
public class ModelMsg extends WechatAPI {
    /**
     * 修改公众号所属的行业
     * POST数据：
     * {
     * "industry_id1":"1",
     * "industry_id2":"4"
     * }
     */
    private static final String SET_INDUSTRY_URL = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";

    /**
     * 获得模板
     * {
     * "template_id_short":"TM00015"
     * }
     * <p>
     * 返回：
     * {
     * "errcode":0,
     * "errmsg":"ok",
     * "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"
     * }
     */
    private static final String GET_TEMPLATE_ID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";

    /**
     * 发送模板消息
     */
    private static final String SEND_TEMPLATE_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    /**
     * 获得模板库的模板列表
     *
     * @deprecated URL不可用，原因未知！！
     * GET
     */
    private static final String GET_TEMPLATE_LIST = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";

    /**
     * 设置行业编号
     * <p>
     * 参数：
     * {
     * "industry_id1":"1",
     * "industry_id2":"4"
     * }
     */
    public static final String API_SET_INDUSTRY = "SET_INDUST";
    /**
     * 获得模板ID
     * <p>
     * 参数：
     * templateNo: 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    public static final String API_GET_TEMPLATE_ID = "GET_ID";
    /**
     * 发送模板消息
     * <p>
     * 参数：
     * template: Template实例
     */
    public static final String API_SEND = "SEND";

    /**
     * 获得模板的列表
     */
    public static final String API_GET_LIST = "LIST";

    public Object getResult(String apiName, Map<String, Object> params) {
        if (API_SET_INDUSTRY.equals(apiName)) {
            return this.setIndustry();
        } else if (API_GET_TEMPLATE_ID.equals(apiName)) {
            return this.getTemplateId((String) params.get("templateNo"));
        } else if (API_SEND.equals(apiName)) {
            return this.sendTemplate((Template) params.get("template"));
        } else if (API_GET_LIST.equals(apiName)) {
            return this.getTemplateList();
        } else {
            return null;
        }
    }

    /**
     * 获取模板消息列表
     *
     * @return 返回模板列表
     * @deprecated 获取模板列表不可用，原因未知
     */
    private Object getTemplateList() {
        return NetworkKit.sshPost(String.format(GET_TEMPLATE_LIST, Ticket.getAccessToken()), null);
    }

    /**
     * 发送模板消息
     *
     * @return 返回发送的结果
     */
    private Object sendTemplate(Template template) {
        return NetworkKit.sshPostJson(String.format(SEND_TEMPLATE_MSG, Ticket.getAccessToken()), new Gson().toJson(template), "UTF-8");
    }

    /**
     * 获得模板消息的ID
     *
     * @param no 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * @return 返回模板消息的ID
     * <p>
     * e.g.
     * {
     * "errcode":0,
     * "errmsg":"ok",
     * "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"
     * }
     */
    private Object getTemplateId(String no) {
        return NetworkKit.sshPostJson(String.format(GET_TEMPLATE_ID, Ticket.getAccessToken()), "{\"template_id_short\":\"" + no + "\"}");
    }

    /**
     * 设置行业编号
     *
     * @return 返回设置的结果
     */
    private Object setIndustry() {
        return null;
    }
}