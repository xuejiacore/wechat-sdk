/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

/**
 * Class Name: BaseMessage
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * 消息基类（公众帐号 -> 普通用户）
 * <p>
 * 1 回复文本消息 text
 * 2 回复图片消息 image
 * 3 回复语音消息 voice
 * 4 回复视频消息 video
 * 5 回复音乐消息 music
 * 6 回复图文消息 news
 */
public class BaseMessage {
    /**
     * 接收方的账号（收到的OpenID）
     */
    private String ToUserName;
    /**
     * 开发者的微信号
     */
    private String FromUserName;
    /**
     * 消息的创建时间（整型）
     */
    private long CreateTime;
    /**
     * 回复的消息类型
     */
    private String MsgType;
    /**
     * 位 0x0001 被标识时，星标刚收到的消息
     */
    private int FuncFlag;

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

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
