package com.bowoon.android.github_api.viewholders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.bowoon.android.github_api.model.User
import com.bowoon.android.github_api.utils.GlideApp
import com.bumptech.glide.signature.ObjectKey
import kotlinx.android.synthetic.main.user_item.view.*

class UserViewHolder(private val view: View) : AndroidExtensionsViewHolder(view) {
    fun bind(item: User) {
        with(view) {
            GlideApp.with(view.context)
                .load(item.ownerImage)
                .centerCrop()
                .placeholder(ColorDrawable(Color.GRAY))
                .signature(ObjectKey(System.currentTimeMillis()))
                .into(ownerImage)
            ownerName.text = item.name
        }
    }
}