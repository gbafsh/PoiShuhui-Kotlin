package com.flying.xiaopo.poishuhui_kotlin.ui.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.NewsContainer
import com.flying.xiaopo.poishuhui_kotlin.domain.network.NewsSource
import com.flying.xiaopo.poishuhui_kotlin.log
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.NewsContainerAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.*

/**
 * Third Page
 * Created by Flying SnowBean on 16-3-2.
 */
class NewsFragment : Fragment() {
    var mData = ArrayList<NewsContainer>()

    lateinit var newsList: RecyclerView

    lateinit var newsRefresh: SwipeRefreshLayout

    lateinit var adapter: NewsContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
        log("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState == null) log("savedInstanceState==null")
        log("onCreateView")
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) log("savedInstanceState==null")
        log("onViewCreated")

        newsRefresh = view.findViewById(R.id.newsRefresh) as SwipeRefreshLayout
        newsList = view.findViewById(R.id.newsList) as RecyclerView

        newsList.layoutManager = LinearLayoutManager(context)
        adapter = NewsContainerAdapter()
        newsList.adapter = adapter

        newsRefresh.setOnRefreshListener {
            load()
        }
        newsRefresh.post { newsRefresh.isRefreshing = true }
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
            var data = NewsSource().obtain("http://ishuhui.net/CMS/")
            uiThread {
                mData = data
                adapter.refreshData(data)
                newsRefresh.isRefreshing = false
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