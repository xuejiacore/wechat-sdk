/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.model
 * Author: Xuejia
 * Date Time: 2016/4/20 23:12
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.model;

import org.zigui.wechat.core.exception.WeChatException;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: Template
 * Create Date: 2016/4/20 23:12
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:模板消息的封装
 */
public class Template {
    /**
     * 接收者的微信号对应的OPEN_ID
     */
    private String touser = null;
    /**
     * 模板的ID
     */
    private String template_id = null;
    /**
     * 点击模板消息需要打开的地址
     */
    private String url = null;
    /**
     * 模板消息的内容
     */
    private TemplateData data = null;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TemplateData getData() {
        return data;
    }

    public void setData(TemplateData data) {
        this.data = data;
    }

    /**
     * {{first.DATA}} 订单商品：{{key1.DATA}} 订单编号：{{key2.DATA}} 支付金额：{{key3.DATA}} 支付时间：{{key4.DATA}} {{remark.DATA}}
     * 测试结果
     */
    public static void main(String[] args) {
        Template template = new Template();
        TemplateData data = new TemplateData();
        data.setFirst(new Item("您好，您的交易信息如下：", "#0099cc"));
        data.setKey1(new Item("Sony XLS9H", "#0099cc"));
        data.setKey2(new Item("DDBH_20160118092745", "#0099cc"));
        data.setKey3(new Item("2500.00 元", "#0099cc"));
        data.setKey4(new Item("2016-01-18 09:27:57", "#0099cc"));
        data.setRemark(new Item("欢迎您再次购买", "#0099cc"));
        template.setData(data);
        template.setTouser("oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk");
        template.setUrl("http://www.baidu.com");
        template.setTemplate_id("e_WUa9BksV2v61WA3fVkF5CHtYah32jlltovuF3UJrg");


        Map<String, Object> params = new HashMap<String, Object>();
        params.put("template", template);
        params.put("templateNo", "OPENTMTM01");
        try {
            System.err.println(ModelMsg.getResult(ModelMsg.class, ModelMsg.API_SEND, params));
//            System.err.println(ModelMsg.getResult(ModelMsg.class, ModelMsg.API_GET_TEMPLATE_ID, params));
//            System.err.println(ModelMsg.getResult(ModelMsg.class, ModelMsg.API_GET_LIST, null));
//            System.err.println(ModelMsg.getResult(ModelMsg.class, ModelMsg.API_GET_LIST, null));
        } catch (WeChatException e) {
            e.printStackTrace();
        }

//        ModelMsg modelMsg = new ModelMsg();
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("template", template);
//        System.err.println(modelMsg.getResult(ModelMsg.API_SEND, params));
    }
}

/*
{
    "touser": "user_open_id",
    "template_id": "e_WUa9BksV2v61WA3fVkF5CHtYah32jlltovuF3UJrg",
    "url": "http://www.baidu.com",
    "data": {
        "first": {
            "value": "您的交易信息如下：",
            "color": "#0099cc"
        },
        "key1": {
            "value": "Sony XLS9H",
            "color": "#0099cc"
        },
        "key2": {
            "value": "DDBH_20160118092745",
            "color": "#0099cc"
        },
        "key3": {
            "value": "2500.00 元",
            "color": "#0099cc"
        },
        "key4": {
            "value": "2016-01-18 09:27:57",
            "color": "#0099cc"
        },
        "remark": {
            "value": "欢迎您再次购买",
            "color": "#0099cc"
        }
    }
}
*/
