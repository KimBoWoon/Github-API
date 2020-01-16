package com.bowoon.android.github_api.model

import com.google.gson.annotations.SerializedName

data class User(
    var name: String,
    @SerializedName("avatar_url")
    var ownerImage: String
)