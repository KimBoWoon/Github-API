package com.bowoon.android.github_api.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.github_api.R
import com.bowoon.android.github_api.adapter.RepoListAdapter
import com.bowoon.android.github_api.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.detail_view.*

class DetailActivity : AppCompatActivity() {
    private val disposeBag = CompositeDisposable()
    private val adapter = RepoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_view)

        val searchKeyword = intent.getStringExtra("searchKeyword")

        repoList.adapter = adapter
        repoList.layoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)

        Repository
            .getUser(searchKeyword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.i("MainActivity", result.toString())
                    adapter.setUser(result)
                },
                { e -> e.printStackTrace() }
            ).addTo(disposeBag)

        Repository
            .getRepoList(searchKeyword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.i("MainActivity", result.toString())
                    adapter.setItem(result)
                },
                { e -> e.printStackTrace() }
            ).addTo(disposeBag)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}