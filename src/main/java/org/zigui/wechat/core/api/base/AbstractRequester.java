/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/20 22:13
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import org.apache.http.NameValuePair;
import org.zigui.wechat.core.net.NetworkKit;

import java.util.List;

/**
 * Class Name: AbstractRequester
 * Create Date: 2016/4/20 22:13
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:抽象http请求类
 */
abstract class AbstractRequester implements INet {

    public static final String GET = "get";
    public static final String POST = "post";

    private String uri = null;
    private String method = "post";
    private List<NameValuePair> pairs = null;
    private IParamsParser parser = null;


    protected abstract void pretreatment();

    protected abstract Object postprocessing(String result) throws Exception;

    /**
     * 带参数的请求
     *
     * @param uri   需要请求的地址
     * @param pairs 请求的参数
     */
    public AbstractRequester(String uri, List<NameValuePair> pairs, String method) {
        this.uri = uri;
        this.method = method;
        this.pairs = pairs;
    }

    /**
     * 不带POST参数的或者只能带GET参数的请求
     *
     * @param uri 需要请求的地址
     */
    public AbstractRequester(String uri) {
        this.uri = uri;
        this.method = GET;
    }

    /**
     * 构造一个基本请求器
     *
     * @param uri    需要请求的uri
     * @param parser 参数解析器
     * @param method 请求的方式
     */
    public AbstractRequester(String uri, IParamsParser parser, String method) {
        this.uri = uri;
        this.method = method;
        this.parser = parser;
    }

    /**
     * 开始发送请求并返回响应的内容
     *
     * @return 响应内容
     */
    private String exec() {
        String resp = null;
        if (this.pairs == null && this.parser != null) {
            this.pairs = ((ParamsParserAdapter) this.parser).parse();
        }
        if (POST.equals(this.method.toLowerCase())) {
            resp = isSSHReq(this.uri) ? sshPost(this.uri, this.pairs) : doPost(this.uri, this.pairs);
        } else if (GET.equals(this.method.toLowerCase())) {
            resp = isSSHReq(this.uri) ? sshGet(this.uri, this.pairs) : doGet(this.uri, this.pairs);
        }
        return resp;
    }

    /**
     * 执行API请求
     *
     * @return 返回执行后的请求结果
     * @throws Exception
     */
    public Object doReq() throws Exception {
        pretreatment();
        return postprocessing(exec());
    }

    /**
     * 执行http的POST请求
     *
     * @param uri   需要执行的POST请求URI
     * @param pairs 请求的参数
     * @return 请求API返回的响应结果
     */
    public String doPost(String uri, List<NameValuePair> pairs) {
        return null;
    }

    /**
     * 执行http的GET请求
     *
     * @param uri   需要执行的GET请求URI
     * @param pairs 请求的参数
     * @return 请求API返回的响应结果
     */
    public String doGet(String uri, List<NameValuePair> pairs) {
        return null;
    }

    /**
     * 执行https的POST请求
     *
     * @param uri   请求的URI
     * @param pairs 请求的参数
     * @return 请求API返回的响应结果
     */
    public String sshPost(String uri, List<NameValuePair> pairs) {
        return NetworkKit.sshPost(uri, pairs);
    }

    /**
     * 执行https的GET方法
     *
     * @param uri   需要执行的请求URI
     * @param pairs 请求的参数
     * @return 返回请求的结果
     */
    public String sshGet(String uri, List<NameValuePair> pairs) {
        if (uri.contains("?") && pairs != null) {
            for (NameValuePair pair : pairs) {
                uri += "&" + pair.getName() + "=" + pair.getValue();
            }
        }
        return NetworkKit.sshPost(uri, null);
    }

    /**
     * 判断请求是否是 https 请求
     *
     * @param uri 请求的地址
     * @return 如果是 https 请求，那么返回值为true，否则返回值为false
     */
    private boolean isSSHReq(String uri) {
        return uri.startsWith("https://");
    }
}

