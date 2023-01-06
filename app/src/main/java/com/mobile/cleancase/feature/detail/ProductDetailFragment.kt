package com.mobile.cleancase.feature.detail

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.cleancase.databinding.FragmentProductDetailBinding
import com.mobile.cleancase.domain.model.ImageDetail
import com.mobile.cleancase.feature.ProductViewModel
import com.mobile.cleancase.feature.adapter.ImageAdapter
import com.mobile.cleancase.presentation.base.BaseFragment
import com.mobile.cleancase.presentation.extension.observeResponse
import com.mobile.cleancase.presentation.extension.setup
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment :
    BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {

    private var productId: Int = 0
    private val productViewModel: ProductViewModel by viewModel()

    private val imageAdapter by lazy {
        ImageAdapter()
    }

    override fun initView() {
        super.initView()
        arguments?.apply {
            productId = getInt(BUNDLE_PRODUCT_ID)
        }

        binding.apply {
            recyclerViewHorizontalProducts.apply {
                setup(
                    layoutManager = LinearLayoutManager(context),
                    orientation = LinearLayoutManager.HORIZONTAL,
                    adapter = imageAdapter
                )
            }
        }
    }

    override fun fetchData() {
        super.fetchData()
        productViewModel.getProductDetails(productId)
    }

    override fun observeData() {
        super.observeData()
        productViewModel.productDetailsLiveData.observeResponse(
            owner = viewLifecycleOwner,
            progressView = binding.progressBar,
            success = { productDetailResponse ->
                productDetailResponse?.let {
                    binding.apply {
                        it.images.let { imageList ->
                            imageAdapter.updateList(imageList as MutableList<ImageDetail>)
                        }
                        textViewProductTitle.text = it.title
                        textViewProductSubTitle.text = it.subTitle
                        textViewProductDescription.text = it.description
                        textViewProductPrice.text = it.price.toString()
                    }
                }
            },
            fail = {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                Log.d("ProductDetail", it.toString())
            }
        )
    }

    companion object {
        private const val BUNDLE_PRODUCT_ID = "product-id"

        fun newInstance(
            productId: Int,
        ) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDLE_PRODUCT_ID, productId)
            }
        }
    }
}