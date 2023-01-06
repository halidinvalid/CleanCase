package com.mobile.cleancase.data.di

import com.mobile.cleancase.data.api.createNetworkClient
import com.mobile.cleancase.data.services.ProductService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single {
        createNetworkClient(BASE_URL)
    }
    single {
        (get() as Retrofit).create(ProductService::class.java)
    }
}

private const val BASE_URL = "https://private-214e3e.apiary-mock.com"