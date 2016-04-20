/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/20 22:16
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Class Name: JsonRequester
 * Create Date: 2016/4/20 22:16
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:Json格式的请求器
 */
public class JsonRequester extends AbstractRequester {

    /**
     * 请求单例
     */
    private static JsonRequester requester = null;

    /**
     * 构建一个Json格式的请求器
     *
     * @param uri    请求的uri
     * @param pairs  请求的参数
     * @param method 请求的方式
     */
    private JsonRequester(String uri, List<NameValuePair> pairs, String method) {
        super(uri, pairs, method);
    }

    /**
     * 构建一个Json格式的请求器
     *
     * @param uri    请求的uri
     * @param parser 请求的解析器
     * @param method 请求的方式
     */
    private JsonRequester(String uri, IParamsParser parser, String method) {
        super(uri, parser, method);
    }

    /**
     * 构建一个Json请求器
     *
     * @param uri 请求的uri
     */
    private JsonRequester(String uri) {
        super(uri);
    }

    /**
     * 发出一个Json请求，返回一个解析成JsonNode对象的实例
     *
     * @param uri    请求的uri
     * @param pairs  请求的参数
     * @param method 请求的方法
     * @return 返回实例化的JsonNode对象
     */
    public static JsonNode reqsWith(String uri, List<NameValuePair> pairs, String method) {
        if (requester == null) {
            synchronized (JsonRequester.class) {
                if (requester == null) {
                    requester = new JsonRequester(uri, pairs, method);
                }
            }
        }
        try {
            return (JsonNode) requester.doReq();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行一个请求
     *
     * @param uri    请求的uri
     * @param parser 请求的参数解析器
     * @param method 请求的方法
     * @return 返回实例化后的JsonNode
     */
    public static JsonNode reqs(String uri, IParamsParser parser, String method) {
        if (requester == null) {
            synchronized (JsonRequester.class) {
                if (requester == null) {
                    requester = new JsonRequester(uri, parser, method);
                }
            }
        }
        try {
            return (JsonNode) requester.doReq();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行一个无参请求
     *
     * @param uri 需要请求的参数
     * @return 返回实例化后的JsonNode对象
     */
    public static JsonNode reqs(String uri) {
        if (requester == null) {
            synchronized (JsonRequester.class) {
                if (requester == null) {
                    requester = new JsonRequester(uri);
                }
            }
        }
        try {
            return (JsonNode) requester.doReq();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void pretreatment() {
    }

    @Override
    protected JsonNode postprocessing(String result) throws Exception {
        ObjectMapper om = new ObjectMapper();
        return om.readTree(result);
    }

}
