/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.net
 * Author: Xuejia
 * Date Time: 2016/4/20 22:36
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Class Name: NetworkKit
 * Create Date: 2016/4/20 22:36
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:http请求支持类：网络工具箱
 */
public class NetworkKit {

    private static Logger logger = Logger.getLogger(NetworkKit.class.getName());

    /**
     * HttpClient
     */
    private static CloseableHttpClient httpClient = null;
    /**
     * HttpPost
     */
    private static HttpPost httpPost = null;
    /**
     * HttpGet
     */
    private static HttpGet httpGet = null;

    /**
     * Https的Post方式实现
     *
     * @param url    请求的URL
     * @param params 请求的参数
     * @return 返回请求响应
     */
    public static String sshPost(String url, List<NameValuePair> params) {
        requestLog(url, params);
        String result = null;
        try {
            httpClient = new SSLHttpClient();
            httpPost = new HttpPost(url);
            if (params != null && params.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 使用https将Json数据Post到指定的URL中
     *
     * @param url      请求的URL
     * @param jsonData 需要Post的Json数据
     * @param encoding 字符编码
     * @return 返回请求响应
     */
    public static String sshPostJson(String url, String jsonData, String encoding) {
        logger.debug("* 【 url: " + url + " 】");
        logger.debug("* * 【 jsonData: " + jsonData + " 】" + encoding);

        String result = null;
        try {
            httpClient = new SSLHttpClient();
            httpPost = new HttpPost(url);

            StringEntity entity = new StringEntity(jsonData, encoding);//解决中文乱码问题
            entity.setContentEncoding(encoding);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 使用GBK编码POST Json数据
     *
     * @param url      请求的URL
     * @param jsonData 需要POST的Json数据
     * @return 返回请求响应
     */
    public static String sshPostJson(String url, String jsonData) {
        return sshPostJson(url, jsonData, "UTF-8");
    }

    /**
     * 使用POST方法请求
     *
     * @param url    请求的URL
     * @param params 请求的参数
     * @return 返回请求响应
     */
    public static String post(String url, List<NameValuePair> params) throws IOException {
        requestLog(url, params);

        httpClient = HttpClients.createDefault();
        httpPost = new HttpPost(url);
        UrlEncodedFormEntity uefEntity;
        String result = null;
        uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(uefEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, "UTF-8");
            }
        }

        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送携带有XML数据的请求
     *
     * @param url     需要请求的地址
     * @param xmlData 需要携带的XML数据内容
     * @return 返回请求的响应内容
     * @throws IOException
     */
    public static String xmlPost(String url, String xmlData) throws IOException {
        return contentPost(url, "xml", xmlData);
    }

    /**
     * 发送携带有JSON数据的请求
     *
     * @param url      需要请求的地址
     * @param jsonData 需要携带的XML数据内存
     * @return 返回请求的响应内容
     * @throws IOException
     */
    public static String jsonPost(String url, String jsonData) throws IOException {
        return contentPost(url, "json", jsonData);
    }

    /**
     * 发送携带有内容的请求
     *
     * @param url         需要请求的内容
     * @param contentType 请求的内容的类型
     * @param contentData 请求的数据内容
     * @return 返回请求的响应内容
     * @throws IOException
     */
    public static String contentPost(String url, String contentType, String contentData) throws IOException {
        //关闭
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");

        //创建httpclient工具对象
        httpClient = HttpClients.createDefault();
        //创建post请求方法
        httpPost = new HttpPost(url);
        System.err.println(contentData);
        StringEntity entity = new StringEntity(contentData);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "text/" + contentType);
        httpPost.setHeader("charset", "utf-8");

        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                return EntityUtils.toString(resEntity, "UTF-8");
            }
        }
        return null;
//        InputStream inputStream = response.getEntity().getContent();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        String tmp;
//        StringBuilder stringBuilder = new StringBuilder();
//        while ((tmp = reader.readLine()) != null) {
//            stringBuilder.append(tmp);
//        }
//        reader.close();
//        inputStream.close();
//        return stringBuilder.toString();
    }

    /**
     * 使用GET方法请求
     *
     * @param url    请求的URL
     * @param params 请求的参数
     * @return 返回请求响应
     */
    public static String get(String url, List<NameValuePair> params) {
        requestLog(url, params);
        httpClient = HttpClients.createDefault();
        String result = null;
        try {
            // 如果携带有get参数，那么对请求的地址进行参数的组合拼装
            if (params != null) {
                url += params.size() > 0 ? "?" : "";
                for (NameValuePair param : params) {
                    url += param.getName() + "=" + param.getValue() + "&";
                }
            }
            url = url.substring(0, url.length() - 1);

            httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "UTF-8");
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 打印请求的日志
     *
     * @param url   请求的URL
     * @param pairs 请求的参数
     */
    private static void requestLog(String url, List<NameValuePair> pairs) {
        String params = "";
        if (pairs != null) {
            for (NameValuePair pair : pairs) {
                params += pair.getName() + "=" + pair.getValue() + "&";
            }
            params = params.substring(0, params.length() - 1);
        }
        logger.debug("* 【 url: " + url + " 】");
        logger.debug("* *【 params: " + params + " 】");
    }

    /**
     * 判断请求的响应是否是Json格式的字符串
     *
     * @param tstStr 需要进行测试的字符串
     * @return 返回测试字符串对应的数据结构形式
     */
    private String isJson(String tstStr) {
        if (tstStr.trim().startsWith("{")) {
            return "JSON";
        } else if (tstStr.trim().startsWith("<")) {
            return "XML";
        } else {
            return "UNKNOWN";
        }
    }

}
