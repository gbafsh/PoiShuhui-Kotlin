package com.flying.xiaopo.poishuhui_kotlin.domain.data

import org.junit.Assert
import org.junit.Test

/**
 * Created by Flying SnowBean on 16-3-5.
 */
class CoverSourceTest{
    @Test
    fun testCover(){
        var data = CoverSource().obtain("http://ishuhui.net/?PageIndex=1")
        Assert.assertFalse(data.size==0)
    }

    @Test
    fun testBook(){
        var data = BookSource().obtain("http://ishuhui.net/ComicBookList/")
        Assert.assertFalse(data.size==0)
    }
}