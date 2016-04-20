/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/20 22:17
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import org.apache.http.NameValuePair;

import java.io.InputStream;
import java.util.List;

/**
 * Class Name: ParamsParserAdapter
 * Create Date: 2016/4/20 22:17
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:参数解析器的适配器
 */
public class ParamsParserAdapter implements IParamsParser {

    public List<NameValuePair> parse() {
        return null;
    }

    public List<NameValuePair> parse(String str) {
        return null;
    }

    public List<NameValuePair> parse(InputStream ins) {
        return null;
    }

    public List<NameValuePair> parse(Byte[] bytes) {
        return null;
    }
}
