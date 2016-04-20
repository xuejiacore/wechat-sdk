/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.material
 * Author: Xuejia
 * Date Time: 2016/4/20 22:21
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.material;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.zigui.wechat.core.Ticket;
import org.zigui.wechat.core.api.base.IObtainResult;
import org.zigui.wechat.core.net.NetworkKit;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Class Name: WeMaterialAPI
 * Create Date: 2016/4/20 22:21
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:微信的文件上传接口
 */
public class WeMaterialAPI implements IObtainResult {
    // 图片（image）: 1M，支持JPG格式 bmp/png/jpeg/jpg/gif
    public static final String TYPE_IMAGE = "image";
    // 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式 mp3/wma/wav/amr
    public static final String TYPE_VOICE = "voice";
    // 视频（video）：10MB，支持MP4格式
    public static final String TYPE_VIDEO = "video";
    // 缩略图（thumb）：64KB，支持JPG格式
    public static final String TYPE_THUMB = "thumb";
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 临时素材的新增以及获取

    /**
     * 新增临时素材，媒体文件在后台保存时间为3天，即3天后media_id失效。
     * <p>media_id:4TepJ_FsNjjFct0f2YylgMY35zBFZ9Ux1PQ7lbHZSsq4-GrKY82KFt4bnyGNlgOj
     * {"type":"image","media_id":"4TepJ_FsNjjFct0f2YylgMY35zBFZ9Ux1PQ7lbHZSsq4-GrKY82KFt4bnyGNlgOj","created_at":1453196164}
     * <p>
     */
    private static final String CGI_ADD_TMP_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
    /**
     * 新增临时素材
     * 参数：<br/>
     * [media] 文件File类型，需要进行上传的媒体文件<br/>
     * [type] 媒体的类型，使用内置的四个常量，分别有 TYPE_IMAGE TYPE_VOICE TYPE_VIDEO TYPE_THUMB<br/>
     * 其中TYPE_IMAGE允许bmp/png/jpeg/jpg/gif，TYPE_VOICE允许mp3/wma/wav/amr
     */
    public static final String API_ADD_TMP_MATERIAL = "ADDT";

    /**
     * http请求方式: GET,https调用
     * 参数：
     * media_id 素材的ID
     * <p>
     * 返回值：
     * 调用该接口可以直接获得图片的内容，如果不存在，则返回值为:
     * {"errcode":40007,"errmsg":"invalid media_id"}
     */
    private static final String CGI_GET_TMP_MATERIAL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
    /**
     * 获得临时素材
     * 参数：<br/>
     * [media_id] 素材id
     */
    public static final String API_GET_TMP_MATERIAL = "GETT";

// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 永久素材的新增以及获取

    // 新增永久图文消息素材
    /**
     * http请求方式: POST
     * 新增永久图文素材接口
     * <p>
     * TODO:在图文消息的具体内容中，将过滤外部的图片链接，开发者可以通过下述接口上传图片得到URL，放到图文内容中使用
     */
    private static final String CGI_ADD_NEWS = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";
    public static final String API_ADD_NEWS = "ADDN";
    /**
     * http请求方式: POST
     * <p>
     * 使用文件流进行上传
     * 返回值：
     * 如果上传成功，返回值为：
     * {"url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"}
     */
    private static final String CGI_ADD_INNER_IMAGE = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=%s";
    public static final String API_ADD_INNER_IMAGE = "ADDINNER";


    // 新增其他类型永久素材

    /**
     * http请求方式: POST，需使用https
     * <p>
     * 新增视频素材时需要附带有如下的信息
     * {
     * "title":VIDEO_TITLE,
     * "introduction":INTRODUCTION
     * }
     */
    private static final String CGI_ADD_LIMIT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s";
    public static final String API_ADD_LIMIT_MATERIAL = "ADDLIMIT";

    // 永久素材的获取
    /**
     * http请求方式: POST,https调用
     * <p>
     * POST内容
     * {
     * "media_id":MEDIA_ID
     * }
     */
    private static final String CGI_GET_LIMIT_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";
    public static final String API_GET_LIMIT_MATERIAL = "GETLIMIT";


// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /**
     * http请求方式: POST
     */
    private static final String CGI_BATCH_GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
    public static final String API_BATCH_GET_MATERIAL = "BATCHGET";

    /**
     * 上传素材到公众号的素材库中
     *
     * @param file         需要进行上传的素材，素材的类型可以为：
     * @param title        上传的素材的标题
     * @param introduction 上传的素材的相关介绍内容
     * @return 返回上传的素材对应的素材类型、素材的media_id以及素材的创建时间
     */
    public static String uploadMaterial(String cgi, File file, String mediaType, String title, String introduction) {
        try {

            //这块是用来处理如果上传的类型是video的类型的
            JsonObject j = new JsonObject();
            j.addProperty("title", title);
            j.addProperty("introduction", introduction);

            // 拼装请求地址
            String uploadMediaUrl;
            uploadMediaUrl = String.format(cgi, Ticket.getAccessToken(), mediaType);

            URL url = new URL(uploadMediaUrl);
            String result = null;
            long filelength = file.length();
            String fileName = file.getName();
            String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String type = "image/jpg"; //我这里写死
            /**
             *  你们需要在这里根据文件后缀suffix将type的值设置成对应的mime类型的值
             */
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false); // post方式不能使用缓存
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            // 设置边界,这里的boundary是http协议里面的分割符，不懂的可惜百度(http 协议 boundary)，这里boundary 可以是任意的值(111,2222)都行
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 请求正文信息
            // 第一部分：

            StringBuilder sb = new StringBuilder();


            //这块是post提交type的值也就是文件对应的mime类型值
            sb.append("--"); // 必须多两道线 这里说明下，这两个横杠是http协议要求的，用来分隔提交的参数用的，不懂的可以看看http 协议头
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"type\" \r\n\r\n"); //这里是参数名，参数名和值之间要用两次
            sb.append(type).append("\r\n"); //参数的值

            //这块是上传video是必须的参数，你们可以在这里根据文件类型做if/else 判断
            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"description\" \r\n\r\n");
            sb.append(j.toString()).append("\r\n");

            /**
             * 这里重点说明下，上面两个参数完全可以卸载url地址后面 就想我们平时url地址传参一样，
             * http://api.weixin.qq.com/cgi-bin/material/add_material?access_token=##ACCESS_TOKEN##&type=""&description={} 这样，如果写成这样，上面的
             * 那两个参数的代码就不用写了，不过media参数能否这样提交我没有试，感兴趣的可以试试
             */

            sb.append("--"); // 必须多两道线
            sb.append(BOUNDARY);
            sb.append("\r\n");
            //这里是media参数相关的信息，这里是否能分开下我没有试，感兴趣的可以试试
            sb.append("Content-Disposition: form-data;name=\"media\";filename=\"").
                    append(fileName).append("\";filelength=\"").append(filelength).append("\" \r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            System.out.println(sb.toString());
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            // 结尾部分，这里结尾表示整体的参数的结尾，结尾要用"--"作为结束，这些都是http协议的规定
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = null;
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();

            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readTree(result);

            if (node.has("media_id")) {
                System.err.println("media_id:" + node.get("media_id").asText());
            } else {
                System.err.println(result);
            }
            System.err.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public Object getResult(String apiName, Map<String, Object> params) {
        if (API_ADD_TMP_MATERIAL.equals(apiName)) {
            // 新增临时素材
            return this.addTmpMaterial((File) params.get("media"), (String) params.get("type"));
        } else if (API_GET_TMP_MATERIAL.equals(apiName)) {
            // 根据media_id获取临时素材
            return this.getTmpMaterial((String) params.get("media_id"));
        } else if (CGI_ADD_LIMIT_MATERIAL.equals(apiName)) {
            // 新增永久素材
            return null;
//            return this.addLimitMaterial();
        } else if (API_GET_LIMIT_MATERIAL.equals(apiName)) {
            // 获得永久素材
            return this.getLimitMaterial((String) params.get("media_id"));
        } else if (API_BATCH_GET_MATERIAL.equals(apiName)) {
            // 获取永久素材列表
            return this.batchGetMaterial((String) params.get("type"), (Integer) params.get("offset"), (Integer) params.get("count"));
        } else if (API_ADD_NEWS.equals(apiName)) {
            // 新增永久图文素材
            return this.addNews((List<Article>) params.get("articles"));
        } else {
            return null;
        }
    }

    private Object addNews(List<Article> articles) {

        return null;
    }

    private Object batchGetMaterial(String type, int offset, int count) {
        return NetworkKit.sshPostJson(String.format(CGI_BATCH_GET_MATERIAL, Ticket.getAccessToken()), "{\"type\":\"" + type + "\"," +
                "\"offset\":" + offset + ",\"count\":" + count + "}");
    }

    private Object getLimitMaterial(String mediaId) {
        return NetworkKit.sshPostJson(String.format(CGI_GET_LIMIT_MATERIAL, Ticket.getAccessToken()), "{\"media_id\":\"" + mediaId + "\"}");
    }

    /**
     * 上传一个临时的素材到素材库中
     *
     * @param media 需要进行上传的媒体文件
     * @param type  媒体文件对应的文件类型
     * @return 上传成功，返回上传成功的素材对应的文件类型、文件的media_id、上传时间的json字符串
     */
    private Object addTmpMaterial(File media, String type) {
        return uploadMaterial(CGI_ADD_TMP_MATERIAL, media, type, "", "");
    }

    /**
     * 通过一个素材id获得一个临时素材
     *
     * @param mediaId 需要得到的临时素材
     * @return 返回素材内容
     */
    private Object getTmpMaterial(String mediaId) {
        System.err.println("获取临时素材正在开发：" + mediaId);
        return NetworkKit.sshPost(String.format(CGI_GET_TMP_MATERIAL, Ticket.getAccessToken(), mediaId), null);
    }

    public static void main(String[] args) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("type", WeMaterialAPI.IMAGE);
//        params.put("offset", 0);
//        params.put("count", 50);
////        params.put("media_id", 50);
//        try {
//            WechatAPI.getResult(WeMaterialAPI.class, WeMaterialAPI.API_BATCH_GET_MATERIAL, params);
//        } catch (WeChatException e) {
//            e.printStackTrace();
//        }
//        List<Article> articles = new ArrayList<Article>();
//        Article article = new Article();
//
//        article.setTitle("Title");
//        article.setContent("CONTENT");
//        article.setDigest("sd");
//        article.setAuthor("setAuthor");
//        article.setContent_source_url("setContent_source_url");
//        article.setShow_cover_pic(1);
//
//        articles.add(article);
//        article = new Article();
//        article.setTitle("Title2");
//        article.setContent("CONTENT2");
//        article.setDigest("s2d2");
//        article.setAuthor("setAuthor2");
//        article.setContent_source_url("setContent_source_url2");
//        article.setShow_cover_pic(0);
//        articles.add(article);
//
//        Map<String, List<Article>> s = new HashMap<String, List<Article>>();
//        s.put("articles", articles);
//        Gson gson = new Gson();
//        System.err.println(gson.toJson(s));

//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("media", new File("F:\\uploaddemo.jpg"));
//        params.put("type", WeMaterialAPI.TYPE_IMAGE);
//        params.put("media_id", "4TepJ_FsNjjFct0f2YylgMY35zBFZ9Ux1PQ7lbHZSsq4-GrKY82KFt4bnyGNlgOj");
//
//        try {
//            WechatAPI.getResult(WeMaterialAPI.class, WeMaterialAPI.API_ADD_NEWS, params);
//        } catch (WeChatException e) {
//            e.printStackTrace();
//        }

    }
}

