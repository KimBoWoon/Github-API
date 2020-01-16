package com.bowoon.android.github_api.utils

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import com.bowoon.android.github_api.model.ImageResource
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.io.IOException

@GlideModule
class AppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.prepend(
            File::class.java,
            BitmapFactory.Options::class.java,
            BitmapSizeDecoder()
        )
        registry.register(
            BitmapFactory.Options::class.java,
            ImageResource::class.java,
            OptionsSizeResourceTranscoder()
        )

    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        builder.setDefaultRequestOptions(
            RequestOptions
                .skipMemoryCacheOf(true)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
    }

    class BitmapSizeDecoder : ResourceDecoder<File, BitmapFactory.Options> {
        @Throws(IOException::class)
        override fun handles(file: File, options: Options): Boolean {
            return true
        }

        override fun decode(
            file: File,
            width: Int,
            height: Int,
            options: Options
        ): Resource<BitmapFactory.Options>? {
            val bmOptions: BitmapFactory.Options = BitmapFactory.Options()
            bmOptions.inJustDecodeBounds = true
            BitmapFactory.decodeFile(file.absolutePath, bmOptions)
            Log.i("BitmapSizeDecoder", "${bmOptions.outHeight} x ${bmOptions.outWidth}")
            return SimpleResource(bmOptions)
        }
    }

    class OptionsSizeResourceTranscoder : ResourceTranscoder<BitmapFactory.Options, ImageResource> {
        override fun transcode(
            resource: Resource<BitmapFactory.Options>,
            options: Options
        ): Resource<ImageResource> {
            val bmOptions = resource.get()
            val size = ImageResource(
                bmOptions.outWidth,
                bmOptions.outHeight
            )
            Log.i("OSRT", "${size.height} x ${size.width}")
            return SimpleResource(size)
        }
    }
}