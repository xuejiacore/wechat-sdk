/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.base
 * Author: Xuejia
 * Date Time: 2016/4/24 23:01
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.base;

/**
 * Class Name: AbstractAPI
 * Create Date: 2016/4/24 23:01
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public abstract class AbstractAPI {
    protected Object checkEmpty(Object ck, boolean checkEmptyStr) {
        if (ck == null) {
            throw new NullPointerException();
        }
        if (ck instanceof String && checkEmptyStr && ((String) ck).length() < 1) {
            throw new NullPointerException();
        }
        return ck;
    }

    protected Object checkEmpty(Object ck) {
        return this.checkEmpty(ck, true);
    }
}
