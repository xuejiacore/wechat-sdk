/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.req
 * Author: Xuejia
 * Date Time: 2016/4/20 23:17
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.req;

/**
 * Class Name: BaseMsg
 * Create Date: 2016/4/20 23:17
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:消息基类，包括了公有的开发者微信号、消息发送者的微信号、消息的创建时间、消息的类型
 */
public class BaseMsg {
    /**
     * 开发者的微信号
     */
    private String ToUserName;
    /**
     * 消息发送者的微信号
     */
    private String FromUserName;
    /**
     * 消息的创建时间（整型）
     */
    private long CreateTime;
    /**
     * 普通消息类型
     * text                                 文本消息
     * image                                图片消息
     * voice                                语音消息
     * video                                视频消息
     * shortvideo                           小视频消息
     * location                             地理位置消息
     * link                                 链接消息
     * ------------------------------------------------------------------
     * 事件推送消息类型
     * subscribe | unsubscribe              关注/取消关注事件
     * subscribe | SCAN                     扫描带参数二维码事件
     * LOCATION                             上报地理位置事件
     * CLICK                                点击菜单拉取消息时的事件推送
     * VIEW                                 点击菜单跳转链接时的事件推送
     */
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
