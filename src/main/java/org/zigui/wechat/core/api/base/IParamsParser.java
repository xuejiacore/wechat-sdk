/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/20 22:16
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import org.apache.http.NameValuePair;

import java.io.InputStream;
import java.util.List;

/**
 * Class Name: IParamsParser
 * Create Date: 2016/4/20 22:16
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:参数解析接口
 */
public interface IParamsParser {
    /**
     * 解析字符串
     *
     * @param str 需要解析的字符串信息
     * @return 返回解析后的参数列表
     */
    List<NameValuePair> parse(String str);

    /**
     * 解析输入流
     *
     * @param ins 需要解析的输入流
     * @return 返回解析后的输入流参数列表
     */
    List<NameValuePair> parse(InputStream ins);

    /**
     * 解析字节流
     *
     * @param bytes 需要解析的字节流
     * @return 返回解析后的字节流参数列表
     */
    List<NameValuePair> parse(Byte[] bytes);
}