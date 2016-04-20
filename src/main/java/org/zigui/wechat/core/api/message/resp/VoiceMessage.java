/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:20
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

import org.zigui.wechat.core.api.message.resp.entity.Voice;

/**
 * Class Name: VoiceMessage
 * Create Date: 2016/4/20 23:20
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:音频消息
 */
public class VoiceMessage extends BaseMessage {
    private org.zigui.wechat.core.api.message.resp.entity.Voice Voice = null;

    public org.zigui.wechat.core.api.message.resp.entity.Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }
}
