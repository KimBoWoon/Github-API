package com.bowoon.android.github_api.viewholders

import android.view.View
import com.bowoon.android.github_api.model.Repo
import kotlinx.android.synthetic.main.repo_item.view.*

class RepoListViewHolder(private val view: View) : AndroidExtensionsViewHolder(view) {
    fun bind(item: Repo) {
        with(view) {
            repoName.text = item.name
            repoDescription.text = item.description
            repoStarCount.text = item.starCount.toString()
        }
    }
}