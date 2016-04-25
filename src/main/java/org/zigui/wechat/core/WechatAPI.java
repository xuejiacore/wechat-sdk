/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api
 * Author: Xuejia
 * Date Time: 2016/4/20 22:32
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.exception.CanNotTransferException;
import org.zigui.wechat.core.exception.UnknownResultException;
import org.zigui.wechat.core.exception.WeChatException;
import org.zigui.wechat.core.exception.WechatParameterException;

import java.io.IOException;
import java.util.Map;

/**
 * Class Name: WechatAPI
 * Create Date: 2016/4/20 22:32
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * 微信API接口抽象类
 * <p>
 * 使用该抽象接口调用微信API，使用getResult方法，填入对应的API大类，调用的API名称（在每个API的内部已经定义公有常量）以及
 * API调用所需的参数（以键值对的形式存在），参数的键名以及参数的类型在API参数中已经提供，请自行查阅API常量说明
 */
public abstract class WechatAPI implements IObtainResult {

    private static Logger logger = Logger.getLogger(WechatAPI.class);

    /**
     * 根据指定的API类名，获取对应的处理结果
     *
     * @param resultCls API类
     * @param apiName   需要请求的API名称
     * @param params    附带的API参数
     * @return 返回请求的响应内容
     * @throws WeChatException
     */
    public static Object getResult(Class<? extends IObtainResult> resultCls, String apiName, Map<String, Object> params) throws WeChatException {
        return getResult(resultCls, apiName, params, null);
    }

    /**
     * 根据置顶的API类名，获取对应的处理结果，该结果允许通过转化类型进行转型（如果该结果能被转化）
     *
     * @param resultCls API结果类型
     * @param apiName   调用的API名称
     * @param params    调用API所需的参数
     * @param clz       调用API后进行转化的类（如果为null则不进行转化）
     * @return 如果有指定转化类，那么返回转化后的结果，否则返回一个结果数据，结果数据可能是Json也可能是JsonNode
     * @throws WeChatException
     */
    public static Object getResult(Class<? extends IObtainResult> resultCls, String apiName, Map<String, Object> params,
                                   Class clz) throws WeChatException {
        IObtainResult api = null;
        try {
            api = resultCls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert api != null;
        Object result;
        try {
            result = api.getResult(apiName, params);
        } catch (NullPointerException e) {
            throw new WechatParameterException(api, apiName);
        }
        // 在输出日志中答应请求响应结果
        logger.debug("* RESPONSE:" + result);
        if (result instanceof String && ((String) result).contains("err")) {
            ObjectMapper om = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = om.readTree((String) result);
            } catch (IOException e) {
                throw new UnknownResultException();
            }
            if (jsonNode != null && jsonNode.get("errcode").asInt(0) != 0) {
                // 如果请求的响应状态码不为0，那么将抛出一个错误
                throw new WeChatException(jsonNode);
            }
        } else if (result instanceof JsonNode) {
            if (((JsonNode) result).get("errcode").asInt() != 0) {
                throw new WeChatException((JsonNode) result);
            }
        }
        if (result instanceof String && clz != null) {
            try {
                result = new Gson().fromJson((String) result, clz);
            } catch (RuntimeException e) {
                throw new CanNotTransferException();
            }
        }
        logger.debug("* REQUEST SUCCESS!");
        return result;
    }

}
