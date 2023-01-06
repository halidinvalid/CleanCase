package com.mobile.cleancase.domain.di

import com.mobile.cleancase.domain.interactor.ProductInteractor
import com.mobile.cleancase.domain.interactor.ProductInteractorImp
import org.koin.dsl.module

val productDomainModules = module {
    single<ProductInteractor> { ProductInteractorImp(get()) }

}
