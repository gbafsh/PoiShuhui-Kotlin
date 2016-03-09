package com.flying.xiaopo.poishuhui_kotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.log
import com.flying.xiaopo.poishuhui_kotlin.snackbar
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.ContentPagerAdapter
import com.flying.xiaopo.poishuhui_kotlin.ui.fragment.BookFragment
import com.flying.xiaopo.poishuhui_kotlin.ui.fragment.HomeFragment
import com.flying.xiaopo.poishuhui_kotlin.ui.fragment.NewsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

//        val maxMem = Runtime.getRuntime().maxMemory().toInt() / 1024
//        log("max->$maxMem")

    }

    private fun init() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { fab.snackbar("Hello PoiShuhui-Kotlin") }
        val fragments = ArrayList<Fragment>()


        fragments.add(HomeFragment())
        fragments.add(BookFragment())
        fragments.add(NewsFragment())


        val nameList = nameResList.map { Int -> getString(Int) }

        viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
        viewPager.offscreenPageLimit = 2

        tabLayout.setupWithViewPager(viewPager)

        Picasso.with(this).setIndicatorsEnabled(true)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
