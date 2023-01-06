package com.mobile.cleancase

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobile.cleancase.feature.ProductViewModel
import com.mobile.cleancase.feature.homepage.ProductActivity
import com.mobile.cleancase.presentation.extension.launchActivity
import com.mobile.cleancase.presentation.extension.observeResponse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by viewModel()
    private val sharedPreferencesEditor: SharedPreferences.Editor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        productViewModel.getToken(BuildConfig.VERSION_NAME)
        productViewModel.tokenLiveData.observeResponse(
            owner = this,
            progressView = null,
            success = {
                it?.token?.let { token ->
                    sharedPreferencesEditor.putString("token", token).apply()
                    launchActivity<ProductActivity> { finish() }
                }
            }, fail = {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )
    }
}