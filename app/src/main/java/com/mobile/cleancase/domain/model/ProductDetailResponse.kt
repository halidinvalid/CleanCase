package com.mobile.cleancase.domain.model

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    @SerializedName("images") var images: List<ImageDetail?>,
    @SerializedName("title") var title: String?,
    @SerializedName("subTitle") var subTitle: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("price") var price: Int?
)