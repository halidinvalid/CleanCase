package com.mobile.cleancase.domain.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("sliders") var sliders: List<ProductItem?>,
    @SerializedName("banners") var banners: List<ProductItem?>
)