/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.util
 * Author: Xuejia
 * Date Time: 2016/4/20 23:26
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class Name: WePropertyUtil
 * Create Date: 2016/4/20 23:26
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 */
public class WePropertyUtil {
    private static Properties p = new Properties();

    static {

        InputStream is = PropertyUtil.class.getResourceAsStream("/defaultWechatConfig.properties");
        try {
            p.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static String getValue(String key) {

        return p.getProperty(key);
    }
}
