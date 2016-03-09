package com.flying.xiaopo.poishuhui_kotlin.domain.network

/**
 * Created by Flying SnowBean on 16-3-5.
 */
interface Source<T> {
    fun obtain(url:String): T
}