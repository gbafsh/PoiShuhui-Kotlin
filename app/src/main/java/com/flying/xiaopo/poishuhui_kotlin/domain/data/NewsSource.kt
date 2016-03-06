package com.flying.xiaopo.poishuhui_kotlin.domain.data

import com.flying.xiaopo.poishuhui_kotlin.domain.model.News
import com.flying.xiaopo.poishuhui_kotlin.domain.model.NewsContainer
import com.flying.xiaopo.poishuhui_kotlin.getHtml
import org.jsoup.Jsoup
import java.util.*

/**
 * Created by Flying SnowBean on 16-3-5.
 */
class NewsSource() : Source<NewsContainer> {
    override fun obtain(url: String): ArrayList<NewsContainer> {
        var list = ArrayList<NewsContainer>()

        val html = getHtml(url)
        var doc = Jsoup.parse(html)
        //        var doc = Jsoup.connect(url).get()

        val elements = doc.select("div.reportersBox").select("div.reportersMain")
        for (element in elements) {
            val title = element.select("div.mangeListTitle").select("span").text()
            var newsList = ArrayList<News>()

            for (ele in element.select("ul.reportersList").select("li").select("a")) {
                val title_news = ele.select("span")[0].text().replace("&amp", "\t")
                val createdTime = ele.select("span")[1].text()
                val link = "http://ishuhui.net/" + ele.attr("href")
                val news = News(title_news, createdTime, link)
                newsList.add(news)
            }

            val newsContainer = NewsContainer(title, newsList)

            list.add(newsContainer)
        }

        return list

    }

}