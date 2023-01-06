package com.mobile.cleancase.data.repository

import com.mobile.cleancase.data.extension.apiCall
import com.mobile.cleancase.domain.datasource.RemoteDataSource
import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenRequest
import com.mobile.cleancase.domain.model.TokenResponse
import com.mobile.cleancase.domain.repository.ProductRepository
import com.mobile.cleancase.presentation.entities.DataHolder


class ProductRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource
) : ProductRepository {

    override suspend fun getToken(appVersion: String?): DataHolder<TokenResponse?> {
        return apiCall {
            remoteDataSource.getToken(TokenRequest(appVersion))
        }
    }

    override suspend fun getProducts(
    ): DataHolder<ProductResponse?> {
        return apiCall {
            remoteDataSource.getProducts()
        }
    }

    override suspend fun getProductDetails(productId: Int?): DataHolder<ProductDetailResponse?> {
        return apiCall {
            remoteDataSource.getProductDetails(productId)
        }
    }
}
