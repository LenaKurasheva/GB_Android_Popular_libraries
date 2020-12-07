package com.lenakurasheva.gb_popular_libraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CachedImage (
    @Expose val url: String,
    @Expose val path: String,
    @Expose val bites: ByteArray
) : Parcelable