package com.mobile.cleancase.domain.interactor

import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenResponse
import com.mobile.cleancase.domain.repository.ProductRepository
import com.mobile.cleancase.presentation.entities.DataHolder

class ProductInteractorImp(private var repositories: ProductRepository) : ProductInteractor {

    override suspend fun getToken(appVersion: String?): DataHolder<TokenResponse?> {
        if (appVersion.isNullOrEmpty()) {
            return DataHolder.Error("App Version cannot be null or empty")
        }
        return repositories.getToken(appVersion)
    }

    override suspend fun getProducts(): DataHolder<ProductResponse?> {
        return repositories.getProducts()
    }

    override suspend fun getProductDetails(productId: Int): DataHolder<ProductDetailResponse?> {
        return repositories.getProductDetails(productId)
    }
}