package com.mobile.cleancase.feature.di

import com.mobile.cleancase.feature.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productViewModelModules = module {
    viewModel {
        ProductViewModel(
            get()
        )
    }
}