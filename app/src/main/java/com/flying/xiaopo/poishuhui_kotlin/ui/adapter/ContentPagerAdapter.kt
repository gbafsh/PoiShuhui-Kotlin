package com.flying.xiaopo.poishuhui_kotlin.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Flying SnowBean on 16-3-2.
 */
class ContentPagerAdapter(val fragments: List<Fragment>, val nameList: List<String>, val fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int = fragments.size

        override fun getItem(position: Int): Fragment? {
                return fragments[position]
        }

        override fun getPageTitle(position: Int): CharSequence? =nameList[position]
}