package com.mobile.cleancase.data.api

import android.content.SharedPreferences
import com.mobile.cleancase.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun createNetworkClient(baseUrl: String) =
    retrofitClient(
        baseUrl,
        httpClient()
    )

class BasicAuthInterceptor : Interceptor {

    private val sharedPreferences: SharedPreferences by inject(SharedPreferences::class.java)

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString("token", "").toString()
        val request = chain.request()
        val newUrl =
            request.url.newBuilder().build()
        val newRequest = request
            .newBuilder()
            .addHeader("token", token)
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}

private fun httpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }
    clientBuilder.addInterceptor(BasicAuthInterceptor())
    clientBuilder.readTimeout(120, TimeUnit.SECONDS)
    clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()