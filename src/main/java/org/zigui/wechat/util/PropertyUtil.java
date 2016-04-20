/**
 * Project Name:wxzh
 * Date:2016年1月22日下午3:48:28
 *
 * @author huangjiahan
 * Copyright (c) 2016, Bonc Ltd. All Rights Reserved.
 */
package org.zigui.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtil {

    private static Properties p = new Properties();

    static {

        InputStream is = PropertyUtil.class.getResourceAsStream("/config.properties");
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
