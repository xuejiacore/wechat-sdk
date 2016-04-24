package org.zigui.wechat;

import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.account.WeAccAPI;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.api.material.WeMaterialAPI;
import org.zigui.wechat.core.api.menu.WeMenuAPI;
import org.zigui.wechat.core.api.message.model.Item;
import org.zigui.wechat.core.api.message.model.ModelMsg;
import org.zigui.wechat.core.api.message.model.Template;
import org.zigui.wechat.core.api.message.model.TemplateData;
import org.zigui.wechat.dxapi.WxApi;
import org.zigui.wechat.dxapi.ZHTicket;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Ticket.refreshTickets(true, new ZHTicket());

        System.err.println(Ticket.getAccessToken());

        IObtainResult obtainResult = new WeMenuAPI();
        System.err.println(obtainResult.getResult(WeMenuAPI.API_LIST_MENU, null));
    }
}
