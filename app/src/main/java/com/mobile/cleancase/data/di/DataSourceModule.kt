package com.mobile.cleancase.data.di


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mobile.cleancase.R
import com.mobile.cleancase.data.datasource.RemoteDataSourceImp
import com.mobile.cleancase.domain.datasource.RemoteDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataSourceModule = module {

    single<RemoteDataSource> { RemoteDataSourceImp(get()) }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidApplication()).edit()
    }

    single {
        getSharedPrefs(androidApplication())
    }
}

fun getSharedPrefs(app: Application): SharedPreferences {
    return app.getSharedPreferences(
        app.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )
}