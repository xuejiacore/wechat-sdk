/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.exception
 * Author: Xuejia
 * Date Time: 2016/4/24 22:27
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.exception;

import org.zigui.wechat.core.api.base.IObtainResult;

/**
 * Class Name: WechatParameterException
 * Create Date: 2016/4/24 22:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class WechatParameterException extends WeChatException {

    private IObtainResult api = null;
    private String apiName = null;

    public WechatParameterException(IObtainResult api, String apiName) {
        this.api = api;
        this.apiName = apiName;
    }

    @Override
    public void printStackTrace() {
        System.err.println("\n* 必须提供必要的请求参数：" + api.necessaryParameter(apiName));
        super.printStackTrace();
    }
}
