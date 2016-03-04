package com.flying.xiaopo.poishuhui_kotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R

/**
 * Created by Flying SnowBean on 16-3-2.
 */
class HomeFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
                val rootView = inflater?.inflate(R.layout.fragment_home, container, false)
                return rootView
        }
}