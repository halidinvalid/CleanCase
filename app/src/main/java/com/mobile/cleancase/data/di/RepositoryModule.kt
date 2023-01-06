package com.mobile.cleancase.data.di

import com.mobile.cleancase.data.repository.ProductRepositoryImpl
import com.mobile.cleancase.domain.datasource.RemoteDataSource
import com.mobile.cleancase.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideProductsRepository(
        remoteDataSource: RemoteDataSource,
    ): ProductRepository =
        ProductRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    single {
        provideProductsRepository(
            remoteDataSource = get()
        )
    }
}