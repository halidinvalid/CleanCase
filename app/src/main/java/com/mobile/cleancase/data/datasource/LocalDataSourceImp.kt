package com.mobile.cleancase.data.datasource

import com.mobile.cleancase.domain.datasource.LocalDataSource
import com.mobile.cleancase.domain.model.ProductResponse

class LocalDataSourceImp() : LocalDataSource {

    override suspend fun getCacheProducts(
    ): ProductResponse? {
        //Not yet implemented
        return null
    }

}