package com.mobile.cleancase.feature.homepage

import com.mobile.cleancase.presentation.base.BaseActivity

class ProductActivity : BaseActivity() {

    override fun provideInitialFragment() = ProductFragment.newInstance()

}