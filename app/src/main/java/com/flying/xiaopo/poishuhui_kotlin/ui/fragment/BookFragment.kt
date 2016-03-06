package com.flying.xiaopo.poishuhui_kotlin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.data.BookSource
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.flying.xiaopo.poishuhui_kotlin.log
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.CoverAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.*

/**
 * Created by Flying SnowBean on 16-3-5.
 */
class BookFragment : Fragment() {
    var mData = ArrayList<Cover>()
    var adapter = CoverAdapter()
    lateinit var bookList: RecyclerView
    lateinit var bookRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        log("onCreateView")
        var rootView = inflater.inflate(R.layout.fragment_book, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")
        bookRefresh = view.findViewById(R.id.bookRefresh) as SwipeRefreshLayout
        bookList = view.findViewById(R.id.bookList) as RecyclerView

        bookList.layoutManager = GridLayoutManager(context, 2)
        bookList.adapter = adapter

        bookRefresh.setOnRefreshListener {
            load()
        }
        bookRefresh.post { bookRefresh.isRefreshing = true }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        log("setUserVisibleHint")
        if(isVisibleToUser&&mData.size==0){
            //bookRefresh.post { bookRefresh.isRefreshing = true }
            load()
        }

        if(isVisibleToUser){
            log("isVisibleToUser is true")
        }
    }

    private fun load() {
        async() {
            var data = BookSource().obtain("http://ishuhui.net/ComicBookList/")

            uiThread {
                mData = data
                adapter.refreshData(data)
                bookRefresh.isRefreshing = false
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