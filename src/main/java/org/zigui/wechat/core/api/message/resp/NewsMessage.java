/**
 * Project: WeChat
 * Package Name: org.zigui.wechat.core.api.message.resp
 * Author: Xuejia
 * Date Time: 2016/4/20 23:19
 * Copyright: 2016 www.bonc.com.cn. All rights reserved.
 **/
package org.zigui.wechat.core.api.message.resp;

import org.zigui.wechat.core.api.message.resp.entity.Article;

import java.util.List;

/**
 * Class Name: NewsMessage
 * Create Date: 2016/4/20 23:19
 * Creator: Xuejia
 * Version: v1.0
 * Updater: Xuejia
 * Date Time:
 * Description:文本消息
 */
public class NewsMessage extends BaseMessage {
    /**
     * 图文消息个数，限制为10条以内
     */
    private int ArticleCount;
    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}