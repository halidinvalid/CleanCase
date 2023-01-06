package com.mobile.cleancase.domain.datasource

import com.mobile.cleancase.domain.model.ProductResponse


interface LocalDataSource {

    suspend fun getCacheProducts(): ProductResponse?

}