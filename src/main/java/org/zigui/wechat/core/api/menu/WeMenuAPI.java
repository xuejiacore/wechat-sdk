/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.menu
 * Author: Xuejia
 * Date Time: 2016/4/20 22:25
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.menu;

import com.google.gson.Gson;
import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.WechatAPI;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.api.menu.entity.Button;
import org.zigui.wechat.core.api.menu.entity.MainMenu;
import org.zigui.wechat.core.api.menu.entity.Menu;
import org.zigui.wechat.core.api.menu.entity.SubMenu;
import org.zigui.wechat.core.exception.WeChatException;
import org.zigui.wechat.core.net.NetworkKit;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: WeMenuAPI
 * Create Date: 2016/4/20 22:25
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:菜单API封装类
 */
public class WeMenuAPI implements IObtainResult {

    /**
     * 创建菜单
     * POST
     */
    private static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    /**
     * 菜单查询
     * GET
     */
    private static final String MENU_LIST_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
    /**
     * 菜单删除
     * GET
     */
    private static final String MENU_DEL_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

    /**
     * 创建菜单API
     */
    public static final String API_CREATE_MENU = "CREATE";
    /**
     * 列出菜单列表API
     */
    public static final String API_LIST_MENU = "LIST";
    /**
     * 删除菜单API
     */
    public static final String API_DEL_MENU = "DEL";

    public Object getResult(String apiName, Map<String, Object> params) {
        if (API_CREATE_MENU.equals(apiName)) {
            return this.createMenu((Menu) params.get("menu"));
        } else if (API_LIST_MENU.equals(apiName)) {
            return this.listMenu();
        } else if (API_DEL_MENU.endsWith(apiName)) {
            return this.delMenu();
        } else {
            return null;
        }
    }

    /**
     * 删除菜单，注意，即使是有默认的菜单和个性化菜单，调用此接口依然会删除所有的菜单
     *
     * @return 返回删除菜单的结果
     */
    private Object delMenu() {
        return NetworkKit.sshPost(String.format(MENU_DEL_URL, Ticket.getAccessToken()), null);
    }

    /**
     * 列出所有的菜单
     *
     * @return 返回菜单列表的Json数据
     */
    private Object listMenu() {
        return NetworkKit.sshPost(String.format(MENU_LIST_URL, Ticket.getAccessToken()), null);
    }

    /**
     * 创建菜单接口
     *
     * @param menu 需要创建的菜单
     * @return 返回微信服务器的菜单创建结果
     * <p>
     * 测试通过：2016年1月18日13:26:42
     * * {"errcode":0,"errmsg":"ok"}
     */
    private Object createMenu(Menu menu) {
        return NetworkKit.sshPostJson(String.format(MENU_CREATE_URL, Ticket.getAccessToken()), new Gson().toJson(menu), "UTF-8");
    }


    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("menu", getMenu());
        try {
            WechatAPI.getResult(WeMenuAPI.class, WeMenuAPI.API_CREATE_MENU, params);
//            WechatAPI.getResult(WeMenuAPI.class, WeMenuAPI.API_LIST_MENU, params);
        } catch (WeChatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static Menu getMenu() {
        SubMenu btn11 = new SubMenu();
        btn11.setName("天气预报");
        btn11.setType("view");
        btn11.setKey("11");
        btn11.setUrl("http://zigui.ngrok.natapp.cn/pages/promotion/促销活动.html");

        SubMenu btn12 = new SubMenu();
        btn12.setName("公交查询");
        btn12.setType("click");
        btn12.setKey("12");

        SubMenu btn13 = new SubMenu();
        btn13.setName("周边搜索");
        btn13.setType("click");
        btn13.setKey("13");

        SubMenu btn14 = new SubMenu();
        btn14.setName("历史上的今天");
        btn14.setType("click");
        btn14.setKey("14");

        SubMenu btn21 = new SubMenu();
        btn21.setName("歌曲点播");
        btn21.setType("click");
        btn21.setKey("21");

        SubMenu btn22 = new SubMenu();
        btn22.setName("经典游戏");
        btn22.setType("click");
        btn22.setKey("22");

        SubMenu btn23 = new SubMenu();
        btn23.setName("美女电台");
        btn23.setType("click");
        btn23.setKey("23");

        SubMenu btn24 = new SubMenu();
        btn24.setName("人脸识别");
        btn24.setType("click");
        btn24.setKey("24");

        SubMenu btn25 = new SubMenu();
        btn25.setName("聊天唠嗑");
        btn25.setType("click");
        btn25.setKey("25");

        SubMenu btn31 = new SubMenu();
        btn31.setName("Q友圈");
        btn31.setType("click");
        btn31.setKey("31");

        SubMenu btn32 = new SubMenu();
        btn32.setName("电影排行榜");
        btn32.setType("click");
        btn32.setKey("32");

        SubMenu btn33 = new SubMenu();
        btn33.setName("幽默笑话");
        btn33.setType("click");
        btn33.setKey("33");

        MainMenu mainBtn1 = new MainMenu();
        mainBtn1.setName("生活助手");
        mainBtn1.setSub_button(new SubMenu[]{btn11, btn12, btn13, btn14});

        MainMenu mainBtn2 = new MainMenu();
        mainBtn2.setName("休闲驿站");
        mainBtn2.setSub_button(new SubMenu[]{btn21, btn22, btn23, btn24, btn25});

        MainMenu mainBtn3 = new MainMenu();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new SubMenu[]{btn31, btn32, btn33});

        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});

        return menu;
    }
}
/*
{
    "button": [
        {
            "sub_button": [
                {
                    "type": "click",
                    "key": "11",
                    "name": "天气预报"
                },
                {
                    "type": "click",
                    "key": "12",
                    "name": "公交查询"
                },
                {
                    "type": "click",
                    "key": "13",
                    "name": "周边搜索"
                },
                {
                    "type": "click",
                    "key": "14",
                    "name": "历史上的今天"
                }
            ],
            "name": "生活助手"
        },
        {
            "sub_button": [
                {
                    "type": "click",
                    "key": "21",
                    "name": "歌曲点播"
                },
                {
                    "type": "click",
                    "key": "22",
                    "name": "经典游戏"
                },
                {
                    "type": "click",
                    "key": "23",
                    "name": "美女电台"
                },
                {
                    "type": "click",
                    "key": "24",
                    "name": "人脸识别"
                },
                {
                    "type": "click",
                    "key": "25",
                    "name": "聊天唠嗑"
                }
            ],
            "name": "休闲驿站"
        },
        {
            "sub_button": [
                {
                    "type": "click",
                    "key": "31",
                    "name": "Q友圈"
                },
                {
                    "type": "click",
                    "key": "32",
                    "name": "电影排行榜"
                },
                {
                    "type": "click",
                    "key": "33",
                    "name": "幽默笑话"
                }
            ],
            "name": "更多体验"
        }
    ]
}
 */