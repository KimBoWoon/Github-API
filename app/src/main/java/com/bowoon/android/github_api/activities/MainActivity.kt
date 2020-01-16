package com.bowoon.android.github_api.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bowoon.android.github_api.R
import com.bowoon.android.github_api.adapter.RepoListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val disposeBag = CompositeDisposable()
    private val adapter = RepoListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send.setOnClickListener { view ->
            val search = mainEditText.text.toString()

            if (search.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "검색어를 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(applicationContext, DetailActivity::class.java).apply {
                    putExtra("searchKeyword", search)
                }
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }
}
