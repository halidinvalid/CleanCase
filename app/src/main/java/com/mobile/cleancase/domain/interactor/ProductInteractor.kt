package com.mobile.cleancase.domain.interactor

import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenResponse
import com.mobile.cleancase.presentation.entities.DataHolder

interface ProductInteractor {

    suspend fun getToken(appVersion: String?): DataHolder<TokenResponse?>

    suspend fun getProducts(): DataHolder<ProductResponse?>

    suspend fun getProductDetails(productId: Int): DataHolder<ProductDetailResponse?>

}