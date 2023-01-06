package com.mobile.cleancase.domain.datasource

import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenRequest
import com.mobile.cleancase.domain.model.TokenResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getToken(tokenRequest: TokenRequest?): Response<TokenResponse?>

    suspend fun getProducts(): Response<ProductResponse?>

    suspend fun getProductDetails(productId: Int?): Response<ProductDetailResponse?>

}