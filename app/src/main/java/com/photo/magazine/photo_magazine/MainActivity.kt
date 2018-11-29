package com.photo.magazine.photo_magazine

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.photo.magazine.photo_magazine.api.ZhihuService
import com.photo.magazine.photo_magazine.api.searchRepos
import com.photo.magazine.photo_magazine.model.Repo
import com.photo.magazine.photo_magazine.ui.RepoAdapter
import com.photo.magazine.photo_magazine.ui.RepoSearchViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RepoSearchViewModel
    private val adapter = RepoAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this, Inject.provideViewModelFactory(this))
                .get(RepoSearchViewModel::class.java)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)
        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = adapter
        viewModel.repos.observe(this, Observer<List<Repo>> { repos ->
            Log.d("nxb", "repos= $repos")
            Log.d("nxb", "数据："+repos.toString()+ "size ${repos?.size}")
            adapter.submitList(repos)
        })

        val query = "摄影"
        viewModel.searchRepo(query)
//
//        searchRepos(ZhihuService.create(), "摄影", { repos ->
//            for (repo in repos) {
//                Log.d("nxb", repo.toString())
//            }
//
//        }, {error ->
//            Log.d("nxb", error)
//        })

        val nums: Collection<Int> = setOf(1, 23,1)
        nums.max()
    }

//    private fun setupScrollListener() {
//        val layoutManager = recycler.layoutManager as LinearLayoutManager
//        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val totalItemCount = layoutManager.itemCount
//                val visibleItemCount = layoutManager.childCount
//                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
//
////                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
//            }
//        })
//    }
}
