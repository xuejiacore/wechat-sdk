/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.net
 * Author: Xuejia
 * Date Time: 2016/4/20 22:38
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Class Name: WeSign
 * Create Date: 2016/4/20 22:38
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:检验服务器地址的有效性
 */
public class WeSign {

    public static final String KEY_URL = "url";
    public static final String KEY_JS_TICKET = "jsapi_ticket";
    public static final String KEY_NONCESTR = "noncestr";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String KEY_SIGNATURE = "signature";

    /**
     * 检验服务器地址的有效性，确保请求的来源是微信服务器
     *
     * @return 如果根据参数进行判断，是来自微信服务器，那么返回值为true，否则返回值为false
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        if (isEmpty(signature) || isEmpty(timestamp) || isEmpty(nonce)) {
            return false;
        }

        String[] arr = new String[]{Ticket.getToken(), timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典顺序进行排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String anArr : arr) {
            content.append(anArr);
        }
        MessageDigest md;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符拼接成一个字符串进行SHA1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 将加密后的字符串与signature进行比较，如果不相同，那么说明请求的来源不是微信服务器
        return tmpStr != null && tmpStr.equals(signature.toUpperCase());
    }

    /**
     * 使用自动生成的字符串和时间戳获得对应的签名
     *
     * @param jsApiTicket js Api 票据
     * @param url         需要使用js Api的页面url，需要动态获得
     * @return 返回在页面中可用的jsapi_ticket
     */
    public static Map<String, String> checkJsSignature(String jsApiTicket, String url) {
        return checkJsSignature(jsApiTicket, url, create_nonce_str(), create_timestamp());
    }

    /**
     * 获得JS Signature 签名的加密方法
     *
     * @param jsApiTicket js Api的获取
     * @param url         需要使用js Api的页面url，需要动态获得
     * @return 返回在页面中可用的jsapi_ticket
     */
    public static Map<String, String> checkJsSignature(String jsApiTicket, String url, String nonceStr, String timestamp) {
        Map<String, String> ret = new HashMap<>();
        String tmpStr;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        tmpStr = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(tmpStr.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put(KEY_URL, url);
        ret.put(KEY_JS_TICKET, jsApiTicket);
        ret.put(KEY_NONCESTR, nonceStr);
        ret.put(KEY_TIMESTAMP, timestamp);
        ret.put(KEY_SIGNATURE, signature);

        return ret;
    }

    /**
     * 将字节数组转化为字符串
     *
     * @param byteArray 需要进行转换的字节数组
     * @return 转换后的十六进制的字符串
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (byte aByteArray : byteArray) {
            strDigest += byteToHexStr(aByteArray);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制的字符串
     *
     * @param mByte 需要进行转化的字节
     * @return 返回对应的十六进制字符
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        return new String(tempArr);
    }

    /**
     * 判断参数是否为空
     *
     * @param tst 需要进行判断的参数
     * @return 如果参数是null或者是空字符串，那么返回值为true，否则返回值为false
     */
    private static boolean isEmpty(String tst) {
        return tst == null || tst.length() == 0;
    }


    /**
     * 将字节数组编码为十六进制
     *
     * @param hash 需要进行编码的字节数组
     * @return 返回编码后的字符串
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 获得随机字符串
     *
     * @return 随机字符串
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获得时间戳
     *
     * @return 返回时间戳
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
