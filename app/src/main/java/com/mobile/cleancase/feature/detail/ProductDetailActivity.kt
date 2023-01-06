package com.mobile.cleancase.feature.detail

import androidx.fragment.app.Fragment
import com.mobile.cleancase.presentation.base.BaseActivity

class ProductDetailActivity : BaseActivity() {

    private var productId: Int = 0

    override fun provideInitialFragment(): Fragment {
        intent?.apply {
            productId = getIntExtra(BUNDLE_PRODUCT_ID, 0)
        }
        return ProductDetailFragment.newInstance(productId)
    }

    companion object {
        const val BUNDLE_PRODUCT_ID = "product-id"
    }
}