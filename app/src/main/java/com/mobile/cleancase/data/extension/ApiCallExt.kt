package com.mobile.cleancase.data.extension

import com.mobile.cleancase.presentation.entities.DataHolder
import retrofit2.Response

suspend fun <T> apiCall(
    apiCallResponse: suspend () -> Response<T>
): DataHolder<T> {
    try {
        val apiCallRes = apiCallResponse()
        if (apiCallRes.isSuccessful) {
            val body = apiCallRes.body()
            body?.let {
                return DataHolder.Success(it)
            }
            return DataHolder.Error("Api call failed ${apiCallRes.code()} ${apiCallRes.message()}")
        } else {
            return DataHolder.Error("Api call failed ${apiCallRes.code()} ${apiCallRes.message()}")
        }
    } catch (throwable: Throwable) {
        return DataHolder.Error("Api call failed ${throwable.message ?: throwable.toString()}")
    }
}


