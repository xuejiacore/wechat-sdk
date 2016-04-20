/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.events
 * Author: Xuejia
 * Date Time: 2016/4/20 22:27
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.events;

/**
 * Class Name: FeebackMsg
 * Create Date: 2016/4/20 22:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:消息发送反馈
 * 目前针对模板消息的发送反馈
 */
public class FeebackMsg extends EventBaseMsg {

    /**
     * 因为用户拒收消息发送失败
     */
    private static final String USER_BLOCK = "failed:user block";
    /**
     * 消息发送成功
     */
    private static final String SUCCESS = "success";
    /**
     * 其他原因导致发送失败
     */
    private static final String SYSTEM_FAILED = "failed: system failed";

    /**
     * 消息的ID
     */
    private String MsgID = null;
    /**
     * 消息的发送状态
     */
    private String Status = null;

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
/*
1、送达成功时，推送的XML如下：
    <xml>
       <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
       <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
       <CreateTime>1395658920</CreateTime>
       <MsgType><![CDATA[event]]></MsgType>
       <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
       <MsgID>200163836</MsgID>
       <Status><![CDATA[success]]></Status>
   </xml>

2、送达由于用户拒收（用户设置拒绝接收公众号消息）而失败时，推送的XML如下：
    <xml>
       <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
       <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
       <CreateTime>1395658984</CreateTime>
       <MsgType><![CDATA[event]]></MsgType>
       <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
       <MsgID>200163840</MsgID>
       <Status><![CDATA[failed:user block]]></Status>
   </xml>

3、送达由于其他原因失败时，推送的XML如下：
   <xml>
        <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
        <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
        <CreateTime>1395658984</CreateTime>
        <MsgType><![CDATA[event]]></MsgType>
        <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
        <MsgID>200163840</MsgID>
        <Status><![CDATA[failed: system failed]]></Status>
   </xml>
*/
