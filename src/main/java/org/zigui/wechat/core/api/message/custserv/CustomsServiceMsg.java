/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.custserv
 * Author: Xuejia
 * Date Time: 2016/4/20 22:26
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.custserv;

import org.zigui.wechat.core.api.base.IObtainResult;

import java.util.Map;

/**
 * Class Name: CustomsServiceMsg
 * Create Date: 2016/4/20 22:26
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * TODO:注意，客服API正在开发中，不可用！！！
 * <p>
 * 1 客服帐号管理
 * 1.1 添加客服帐号
 * 1.2 修改客服帐号
 * 1.3 删除客服帐号
 * 1.4 设置客服帐号的头像
 * 1.5 获取所有客服账号
 * 1.6 接口的统一参数说明
 * <p>
 * 2 客服接口-发消息
 * <p>
 * 参数 	是否必须 	说明
 * access_token 	是 	 调用接口凭证
 * kf_account 	    是 	 完整客服账号，格式为：账号前缀@公众号微信号
 * kf_nick 	        是 	 客服昵称
 * kf_id 	        是 	 客服工号
 * nickname 	    是 	 客服昵称，最长6个汉字或12个英文字符
 * password 	    否 	 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
 * media 	        是 	 该参数仅在设置客服头像时出现，是form-data中媒体文件标识，有filename、filelength、content-type等信息
 */
public class CustomsServiceMsg implements IObtainResult {
    /**
     * 添加客服帐号
     */
    private static final String ADD_CUSTMS_SERVICE = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=%s";
    /**
     * 修改客服帐号
     */
    private static final String UPDATE_CUSTMS_SERVICE = "https://api.weixin.qq.com/customservice/kfaccount/update?access_token=%s";
    /**
     * 删除客服帐号
     */
    private static final String DELETE_CUSTMS_SERVICE = "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=%s";
    /**
     * 设置客服帐号的头像
     */
    private static final String SET_HEAD_IMG = "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=%s&kf_account=%s";
    /**
     * 获取所有客服账号
     */
    private static final String GET_CS_ACCT_LIST = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=%s";
    /**
     * 客服接口-发消息
     */
    private static final String SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

    public static final String API_ADD_CUSTMS_SERVICE = "ADD";
    public static final String API_UPDATE_CUSTMS_SERVICE = "UPDATE";
    public static final String API_DELETE_CUSTMS_SERVICE = "DEL";
    public static final String API_SET_HEAD_IMG = "SET_HEAD";
    public static final String API_GET_CS_ACCT_LIST = "GET_ACCTS";
    public static final String API_SEND_MSG = "SEND";

    private Object sendMsg() {
        return null;
    }

    private Object setHeadImg() {
        return null;
    }

    private Object delCustmsService() {
        return null;
    }

    private Object updateCustmsService() {
        return null;
    }

    private Object addCustmsService() {
        return null;
    }

    private Object getAcctList() {
        return null;
    }

    public Object getResult(String apiName, Map<String, Object> params) {
        Object result = null;
        if (API_ADD_CUSTMS_SERVICE.equals(apiName)) {
            result = this.addCustmsService();
        } else if (API_UPDATE_CUSTMS_SERVICE.equals(apiName)) {
            result = this.updateCustmsService();
        } else if (API_DELETE_CUSTMS_SERVICE.equals(apiName)) {
            result = this.delCustmsService();
        } else if (API_SET_HEAD_IMG.equals(apiName)) {
            result = this.setHeadImg();
        } else if (API_GET_CS_ACCT_LIST.equals(apiName)) {
            result = this.getAcctList();
        } else if (API_SEND_MSG.equals(apiName)) {
            result = this.sendMsg();
        }
        return result;
    }

    @Override
    public String necessaryParameter(String apiName) {
        return null;
    }
}
