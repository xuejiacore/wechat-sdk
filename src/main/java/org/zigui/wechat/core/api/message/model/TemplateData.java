/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.model
 * Author: Xuejia
 * Date Time: 2016/4/20 23:12
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.model;

/**
 * Class Name: TemplateData
 * Create Date: 2016/4/20 23:12
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:
 * 模板消息数据
 * <p>
 * 测试模板的内容如下：
 * <p>
 * 模板ID:e_WUa9BksV2v61WA3fVkF5CHtYah32jlltovuF3UJrg
 * <p>
 * {{first.DATA}}
 * 订单商品：{{key1.DATA}}
 * 订单编号：{{key2.DATA}}
 * 支付金额：{{key3.DATA}}
 * 支付时间：{{key4.DATA}}
 * {{remark.DATA}}
 */
public class TemplateData {
    /**
     * 称谓、问候语
     */
    private Item first = null;

    /**
     * 关键字1
     */
    private Item key1 = null;
    /**
     * 关键字2
     */
    private Item key2 = null;
    /**
     * 关键字3
     */
    private Item key3 = null;
    /**
     * 关键字4
     */
    private Item key4 = null;

    /**
     * 结语、备注
     */
    private Item remark = null;

    public Item getFirst() {
        return first;
    }

    public void setFirst(Item first) {
        this.first = first;
    }

    public Item getKey1() {
        return key1;
    }

    public void setKey1(Item key1) {
        this.key1 = key1;
    }

    public Item getKey2() {
        return key2;
    }

    public void setKey2(Item key2) {
        this.key2 = key2;
    }

    public Item getKey3() {
        return key3;
    }

    public void setKey3(Item key3) {
        this.key3 = key3;
    }

    public Item getKey4() {
        return key4;
    }

    public void setKey4(Item key4) {
        this.key4 = key4;
    }

    public Item getRemark() {
        return remark;
    }

    public void setRemark(Item remark) {
        this.remark = remark;
    }
}

