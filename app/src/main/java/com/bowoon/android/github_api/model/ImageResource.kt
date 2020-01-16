package com.bowoon.android.github_api.model

import android.os.Parcel
import android.os.Parcelable

data class ImageResource(
    val width: Int,
    val height: Int,
    val url: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun toString(): String = "$width x $height"
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageResource> {
        override fun createFromParcel(parcel: Parcel): ImageResource {
            return ImageResource(parcel)
        }

        override fun newArray(size: Int): Array<ImageResource?> {
            return arrayOfNulls(size)
        }
    }
}