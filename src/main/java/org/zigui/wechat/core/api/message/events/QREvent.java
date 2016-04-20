/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.events
 * Author: Xuejia
 * Date Time: 2016/4/20 22:27
 * Copyright: 2016 www.zigui.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.events;

/**
 * Class Name: QREvent
 * Create Date: 2016/4/20 22:27
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * 扫描二维码事件
 * Event: subscribe | SCAN
 * <p>
 * <p>
 * 用户扫描带场景值二维码时，可能推送以下两种事件：
 * <p>
 * 1、如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
 * 用户未关注时，进行关注后的事件推送             事件类型，subscribe
 * 2、如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
 * 用户已关注时的事件推送                         事件类型，SCAN
 */
public class QREvent extends EventBaseMsg {
    /**
     * 1、事件KEY值，qrscene_为前缀，后面为二维码的参数值
     * 2、事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    private String EventKey = null;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String Ticket = null;
}
