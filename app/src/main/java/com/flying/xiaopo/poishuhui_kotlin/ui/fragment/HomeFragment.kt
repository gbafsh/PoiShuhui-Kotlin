package com.flying.xiaopo.poishuhui_kotlin.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.flying.xiaopo.poishuhui_kotlin.domain.network.CoverSource
import com.flying.xiaopo.poishuhui_kotlin.log
import com.flying.xiaopo.poishuhui_kotlin.snackbar
import com.flying.xiaopo.poishuhui_kotlin.ui.activity.ComicActivity
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.CoverAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.*

/**
 * First Page
 * Created by Flying SnowBean on 16-3-2.
 */
class HomeFragment : Fragment() {
    var mData = ArrayList<Cover>()

    lateinit var coverList: RecyclerView

    lateinit var homeRefresh: SwipeRefreshLayout

    lateinit var adapter: CoverAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        log("onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        log("onCreateView")

        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")
        initView(view)
    }

    /**
     * init setting view
     */
    private fun initView(view: View) {
        homeRefresh = view.findViewById(R.id.homeRefresh) as SwipeRefreshLayout
        coverList = view.findViewById(R.id.homeList) as RecyclerView

        coverList.layoutManager = GridLayoutManager(context, 2)

        adapter = CoverAdapter { view: View, position: Int -> jump2Comic(position) }
        coverList.adapter = adapter

        homeRefresh.setOnRefreshListener {
            load()
        }
        homeRefresh.post { homeRefresh.isRefreshing = true }
    }

    private fun jump2Comic(position: Int) {
//        homeRefresh.snackbar(mData[position].link)
        var intent = Intent(context, ComicActivity().javaClass)
        intent.putExtra(ComicActivity.INTENT_COMIC_URL, mData[position].link)
        startActivity(intent)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        log("setUserVisibleHint")
        if (isVisibleToUser && mData.size == 0) {

            load()
        }

        if (isVisibleToUser) {
            log("isVisibleToUser is true")
        }
    }

    private fun load() {
        async() {
            val data = CoverSource().obtain("http://ishuhui.net/?PageIndex=1")

            uiThread {
                mData = data
                adapter.refreshData(data)
                homeRefresh.isRefreshing = false
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        log("onAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        log("onDetach")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        log("onSaveInstanceState")
    }


}