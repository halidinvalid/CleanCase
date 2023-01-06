package com.mobile.cleancase.data.datasource

import com.mobile.cleancase.data.services.ProductService
import com.mobile.cleancase.domain.datasource.RemoteDataSource
import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenRequest
import com.mobile.cleancase.domain.model.TokenResponse
import retrofit2.Response

class RemoteDataSourceImp(private val productServices: ProductService) : RemoteDataSource {

    override suspend fun getToken(tokenRequest: TokenRequest?): Response<TokenResponse?> =
        productServices.getToken(tokenRequest)

    override suspend fun getProducts(): Response<ProductResponse?> =
        productServices.getProducts()

    override suspend fun getProductDetails(productId: Int?): Response<ProductDetailResponse?> =
        productServices.getProductDetails(productId)

}