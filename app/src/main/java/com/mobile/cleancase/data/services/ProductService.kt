package com.mobile.cleancase.data.services

import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenRequest
import com.mobile.cleancase.domain.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {

    @POST("/initialize")
    suspend fun getToken(@Body tokenRequest: TokenRequest?): Response<TokenResponse?>

    @GET("/home-page")
    suspend fun getProducts(): Response<ProductResponse?>

    @GET("/product/detail/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int?): Response<ProductDetailResponse?>
}