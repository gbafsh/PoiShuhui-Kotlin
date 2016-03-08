package com.flying.xiaopo.poishuhui_kotlin.ui.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.data.BookDetailSource
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Page
import com.flying.xiaopo.poishuhui_kotlin.snackbar
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.PageAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.*

class BookDetailActivity : AppCompatActivity() {
    lateinit var url: String
    lateinit var pageList: RecyclerView
    lateinit var adapter: PageAdapter
    lateinit var pageRefresh: SwipeRefreshLayout
    var mData = ArrayList<Page>()

    companion object {
        val INTENT_COVER_URL = "cover"
        val INTENT_URL = "url"
        val INTENT_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        //val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        init()
    }

    private fun init() {
        val coverUrl = intent?.getStringExtra(INTENT_COVER_URL)
        var title = intent?.getStringExtra(INTENT_TITLE)
        url = intent.getStringExtra(INTENT_URL)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = title
        Picasso.with(this).load(coverUrl).into(backgroundImage)

        pageRefresh = find(R.id.pageRefresh)
        pageRefresh.setOnRefreshListener { load() }


        pageList = find(R.id.pageList)
        pageList.layoutManager = GridLayoutManager(this, 4)

        adapter = PageAdapter { view: View, position: Int ->
            jump2Read(position)
        }
        pageList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        pageRefresh.post { pageRefresh.isRefreshing = true }
        load()
    }

    private fun load() = async() {
        val bookDetail = BookDetailSource().obtain(url)
        mData = bookDetail.pages as ArrayList<Page>

        uiThread {
            adapter.refreshData(mData)
            pageRefresh.isRefreshing = false
            if (mData.size == 0) {
                showError()
            }
        }
    }

    private fun showError() {
        pageList.snackbar(R.string.page_load_error)
    }


    /**
     * jump to comic page
     */
    private fun jump2Read(position: Int) {

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
