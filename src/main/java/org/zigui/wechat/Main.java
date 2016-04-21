package org.zigui.wechat;

import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.api.material.WeMaterialAPI;
import org.zigui.wechat.core.api.message.model.Item;
import org.zigui.wechat.core.api.message.model.ModelMsg;
import org.zigui.wechat.core.api.message.model.Template;
import org.zigui.wechat.core.api.message.model.TemplateData;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Ticket.refreshTickets(false, new Ticket.ITicket() {
            @Override
            public Map<String, Object> getToken() {
                Map<String, Object> data = new HashMap<>();
                data.put("access_token", "jludMGPK7MoKY_V3ICuAKDrHyV_m7YcaK-OPTwlxWCILh_iskBTRrQTV6aCT_rYh5NTcLL1cyBpzf54kakb7q1ChCJTDZFqFoJBHeDHrW-CoPALYsdNJXTnGNTeaGXmTSJJbAAAHYY");
                data.put("expires_in", 7200);
                return data;
            }

            @Override
            public Map<String, Object> getTicket() {
                Map<String, Object> data = new HashMap<>();
                data.put("ticket", "sM4AOVdWfPE4DxkXGEs8VFQjvD9jxriOkvKohmSCT1FKe56KGgg1JTbgh3s1yXf9-_geDytbGIPyqArqT2t3rg");
                data.put("expires_in", 7200);
                return data;
            }
        });
        IObtainResult weUserAPI = new ModelMsg();
        Map<String, Object> params = new HashMap<>();
//        params.put("openid","oCraWwO3Dk9ask9Em1wau7L8nTsE");
        Template template = new Template();
        template.setTemplate_id("e_WUa9BksV2v61WA3fVkF5CHtYah32jlltovuF3UJrg");
        template.setTouser("oAm5Vt_CzAdnFZ4SHGTwZZPxVdYk");
        template.setUrl("http://mall.zhuhai.gd.cn/wxzh/mvc/light/homepage");
        TemplateData data = new TemplateData();
        data.setFirst(new Item("测试"));
        data.setKey1(new Item("商品名称"));
        data.setKey2(new Item("20160421094806"));
        data.setKey3(new Item("支付金额"));
        data.setKey4(new Item("2016-04-21 09:48:49"));
        data.setRemark(new Item("点击查看原文"));
        template.setData(data);
        params.put("template", template);
        System.err.println(weUserAPI.getResult(ModelMsg.API_SEND, params));



    }
}
