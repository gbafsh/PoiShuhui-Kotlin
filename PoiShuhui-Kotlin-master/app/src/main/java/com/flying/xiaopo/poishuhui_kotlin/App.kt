package com.flying.xiaopo.poishuhui_kotlin

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient

/**
 * @author wupanjie
 */
class App : Application() {
  override fun onCreate() {
    super.onCreate()
    val client = OkHttpClient()
    val picasso = Picasso.Builder(this)
        .downloader(OkHttp3Downloader(client))
        .build()

    Picasso.setSingletonInstance(picasso)
  }
}
