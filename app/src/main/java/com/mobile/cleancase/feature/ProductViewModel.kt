package com.mobile.cleancase.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.cleancase.domain.interactor.ProductInteractor
import com.mobile.cleancase.domain.model.ProductDetailResponse
import com.mobile.cleancase.domain.model.ProductResponse
import com.mobile.cleancase.domain.model.TokenResponse
import com.mobile.cleancase.presentation.base.BaseViewModel
import com.mobile.cleancase.presentation.entities.DataHolder
import com.mobile.cleancase.presentation.extension.launchViewModelScope
import com.mobile.cleancase.presentation.extension.loadingData
import com.mobile.cleancase.presentation.extension.responseData

class ProductViewModel(
    private val productInteractor: ProductInteractor
) : BaseViewModel() {

    private val tokenMutableLiveData = MutableLiveData<DataHolder<TokenResponse?>>()
    val tokenLiveData: LiveData<DataHolder<TokenResponse?>>
        get() = tokenMutableLiveData

    private val productsMutableLiveData = MutableLiveData<DataHolder<ProductResponse?>>()
    val productsLiveData: LiveData<DataHolder<ProductResponse?>>
        get() = productsMutableLiveData

    private val productDetailsMutableLiveData =
        MutableLiveData<DataHolder<ProductDetailResponse?>>()
    val productDetailsLiveData: LiveData<DataHolder<ProductDetailResponse?>>
        get() = productDetailsMutableLiveData

    fun getToken(appVersion: String?) = launchViewModelScope {
        tokenMutableLiveData
            .loadingData()
            .responseData(productInteractor.getToken(appVersion))
    }

    fun getProducts() = launchViewModelScope {
        productsMutableLiveData
            .loadingData()
            .responseData(productInteractor.getProducts())
    }

    fun getProductDetails(productId: Int) = launchViewModelScope {
        productDetailsMutableLiveData
            .loadingData()
            .responseData(productInteractor.getProductDetails(productId))
    }
}