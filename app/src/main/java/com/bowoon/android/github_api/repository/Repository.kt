package com.bowoon.android.github_api.repository

import android.util.Log
import com.bowoon.android.github_api.api.provideProductApi
import com.bowoon.android.github_api.model.RepoList
import com.bowoon.android.github_api.model.User
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

object Repository {
    private val disposeBag = CompositeDisposable()

    fun getRepoList(searchKeyword: String): Single<RepoList> {
        return Single.create { emitter ->
            provideProductApi()
                .getRepoList(searchKeyword)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        Log.i("MainActivity", it.toString())
                        it.sortByDescending { item -> item.starCount }
                        emitter.onSuccess(it)
                    },
                    { e -> emitter.onError(e) }
                ).addTo(disposeBag)
        }
    }

    fun getUser(searchKeyword: String): Single<User> {
        return Single.create { emitter ->
            provideProductApi()
                .getUser(searchKeyword)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { emitter.onSuccess(it) },
                    { e -> emitter.onError(e) }
                ).addTo(disposeBag)
        }
    }
}