package org.zigui.wechat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.api.material.WeMaterialAPI;
import org.zigui.wechat.core.net.NetworkKit;
import org.zigui.wechat.dxapi.ApiAuthor.XmlSign;
import org.zigui.wechat.dxapi.WxApi;
import org.zigui.wechat.dxapi.protocol.request.RequestProtocol;
import org.zigui.wechat.dxapi.ApiPropertyUtil;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Ticket.refreshAccessToken(false);
        IObtainResult weUserAPI = new WeMaterialAPI();
        Map<String, Object> params = new HashMap<>();
//        params.put("openid","oCraWwO3Dk9ask9Em1wau7L8nTsE");
        params.put("type", WeMaterialAPI.TYPE_IMAGE);
        params.put("offset", 0);
        params.put("count", 10);
        System.err.println(weUserAPI.getResult(WeMaterialAPI.API_BATCH_GET_MATERIAL, params));
    }
}
