package com.mobile.cleancase.domain.model

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("id") var id: Int?,
    @SerializedName("image") var image: ImageDetail?,
)


