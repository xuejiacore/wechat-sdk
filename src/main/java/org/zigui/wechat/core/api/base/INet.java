/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/20 22:14
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Class Name: INet
 * Create Date: 2016/4/20 22:14
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:网络接口
 */
public interface INet {
    /**
     * HTTP POST 网络请求接口
     *
     * @param uri   发出http Post请求
     * @param pairs 请求参数
     * @return 返回请求执行结果
     */
    String doPost(String uri, List<NameValuePair> pairs);

    /**
     * HTTP GET 网络请求接口
     *
     * @param uri   发出http Post请求
     * @param pairs 请求参数
     * @return 返回执行的结果
     */
    String doGet(String uri, List<NameValuePair> pairs);

    /**
     * HTTPS POST 网络请求接口
     *
     * @param uri   发出https Post请求
     * @param pairs 请求参数
     * @return 返回执行的结果
     */
    String sshPost(String uri, List<NameValuePair> pairs);

    /**
     * HTTPS GET 网络请求接口
     *
     * @param uri   发出https Get请求
     * @param pairs 请求参数
     * @return 返回执行的结果
     */
    String sshGet(String uri, List<NameValuePair> pairs);
}
