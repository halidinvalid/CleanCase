package com.mobile.cleancase.presentation.extension

import androidx.lifecycle.MutableLiveData
import com.mobile.cleancase.presentation.entities.DataHolder


fun <T : Any?> MutableLiveData<DataHolder<T?>>.loadingData() = apply {
    postValue(DataHolder.Loading())
}

fun <T : Any?> MutableLiveData<DataHolder<T?>>.responseData(responseMethod: DataHolder<T?>) =
    apply {
        this.postValue(responseMethod)
    }