/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.exception
 * Author: Xuejia
 * Date Time: 2016/4/20 22:33
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zigui.wechat.core.Ticket;

import java.io.IOException;
import java.util.Date;

/**
 * Class Name: WeChatException
 * Create Date: 2016/4/20 22:33
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:微信接口异常基类
 */
public class WeChatException extends Exception {

    /**
     * JsonNode的处理节点
     */
    private JsonNode node = null;

    protected static final int UNKNOWN_EXCEPTION = -1;
    protected static final int CHECK_PARAMETER = 1;
    protected static final int CAN_NOT_TRANSFER = 2;
    protected static final int UNKNOWN_RESULT = 3;
    protected static final int GENERAL = -99;
    private int exceptionType = UNKNOWN_EXCEPTION;


    public WeChatException(int exType) {
        exceptionType = exType;
    }

    /**
     * 构建一个WeChat异常类
     *
     * @param jsonMsg 异常的Json消息
     * @throws IOException
     */
    public WeChatException(String jsonMsg) {
        ObjectMapper om = new ObjectMapper();
        try {
            this.node = om.readTree(jsonMsg);
        } catch (IOException e) {
            this.node = null;
        }
        exceptionType = GENERAL;
    }

    public WeChatException(JsonNode jsonNode) {
        this.node = jsonNode;
        exceptionType = GENERAL;
    }

    /**
     * 构建一个WeChat异常实例
     *
     * @param title   异常主题
     * @param jsonMsg 异常的Json消息
     */
    public WeChatException(String title, String jsonMsg) {
        super(title);
        ObjectMapper om = new ObjectMapper();
        try {
            node = om.readTree(jsonMsg);
        } catch (IOException e) {
            node = null;
        }
    }

    /**
     * 获得错误异常对应的Json节点
     *
     * @return 返回JsonNode的节点
     */
    public JsonNode getNode() {
        return node;
    }

    /**
     * 获得错误信息
     *
     * @return 错误信息
     */
    public String getErrorMsg() {
        return node.get("errmsg").asText("ok");
    }

    /**
     * 获取错误编码
     *
     * @return 错误编码
     */
    public int getErrorCode() {
        return node.get("errcode").asInt(0);
    }

    /**
     * 获得微信异常代码对应的原因解释
     *
     * @return 返回错误代码对应的异常代码
     */
    public String getTransMsg() {
        return WechatError.getError(node.get("errcode").asInt());
    }

    @Override
    public void printStackTrace() {
        if (exceptionType == CHECK_PARAMETER) {
            System.err.println("\n* API调用参数不足 - " + new Date());
        } else if (exceptionType == CAN_NOT_TRANSFER) {
            System.err.println("\n* 无法对结果进行目标结果的转化");
        } else if (exceptionType == UNKNOWN_RESULT) {
            System.err.println("\n* 未知的请求结果数据结构");
        } else {
            int errorCode = node.get("errcode").asInt();
            System.err.println("\n* [ " + errorCode + " ] " + getTransMsg() + " - " + new Date());
            switch (errorCode) {
                case 42001:
                    System.err.println("* access_token refresh time: " + Ticket.getRefreshTime());
            }
        }
        super.printStackTrace();
    }
}
