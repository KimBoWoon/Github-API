package com.bowoon.android.github_api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.github_api.R
import com.bowoon.android.github_api.model.Repo
import com.bowoon.android.github_api.model.User
import com.bowoon.android.github_api.viewholders.RepoListViewHolder
import com.bowoon.android.github_api.viewholders.UserViewHolder

class RepoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Repo>()
    private val userItem = User("", "")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            return RepoListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false))
        } else {
            return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != 0) {
            (holder as RepoListViewHolder).bind(items[position])
        } else {
            (holder as UserViewHolder).bind(userItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            1
        } else {
            0
        }
    }

    fun setItem(items: List<Repo>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setUser(userItem: User) {
        this.userItem.name = userItem.name
        this.userItem.ownerImage = userItem.ownerImage
        notifyDataSetChanged()
    }
}