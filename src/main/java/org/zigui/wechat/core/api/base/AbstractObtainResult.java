/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/24 21:37
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import org.zigui.wechat.core.exception.WechatError;

import java.util.Map;

/**
 * Class Name: AbstractObtainResult
 * Create Date: 2016/4/24 21:37
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public abstract class AbstractObtainResult implements IObtainResult {
    public void checkParameters(Map<String, Object> params, String... checkItems) {
        for (String checkItem : checkItems) {
            if (!params.containsKey(checkItem)) {
            }
        }
    }
}
