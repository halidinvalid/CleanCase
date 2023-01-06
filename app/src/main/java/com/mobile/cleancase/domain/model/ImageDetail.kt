package com.mobile.cleancase.domain.model

import com.google.gson.annotations.SerializedName

data class ImageDetail(
    @SerializedName("url") var url: String?,
    @SerializedName("width") var width: Int?,
    @SerializedName("height") var height: Int?,
)
