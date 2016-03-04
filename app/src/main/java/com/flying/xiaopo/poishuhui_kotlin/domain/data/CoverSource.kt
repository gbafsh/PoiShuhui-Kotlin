package com.flying.xiaopo.poishuhui_kotlin.domain.data

import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import org.jsoup.nodes.Document
import java.util.*

/**
 * Created by Flying SnowBean on 16-3-2.
 */
class CoverSource() : Source<Cover> {
    override fun obtain(url: String): ArrayList<Cover> {
        val list = ArrayList<Cover>()

        val doc = Document(url)
        val elements = doc.select("ul.mangeListBox").select("li")
        for (element in elements) {
            val coverUrl = element.select("img").attr("src")
            val title = element.select("h1").text() + "\n" + element.select("h2").text()
            val link = "http://ishuhui.net" + element.select("div.magesPhoto").select("a").attr("href")
            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }

        return list
    }

}