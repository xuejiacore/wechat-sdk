/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/20 22:15
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: IObtainResult
 * Create Date: 2016/4/20 22:15
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:请求返回的结果
 */
public interface IObtainResult {
    Map<String, String> paramInfo = new HashMap<>();

    /**
     * 获得API请求的结果
     *
     * @param name   调用的API名称
     * @param params 调用API名称对应的参数，键值对类型
     * @return 返回请求的结果
     */
    Object getResult(String name, Map<String, Object> params);

    String necessaryParameter(String apiName);
}
