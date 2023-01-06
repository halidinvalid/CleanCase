package com.mobile.cleancase.feature.homepage

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.cleancase.databinding.FragmentProductBinding
import com.mobile.cleancase.domain.model.ProductItem
import com.mobile.cleancase.feature.ProductViewModel
import com.mobile.cleancase.feature.adapter.ProductHorizontalAdapter
import com.mobile.cleancase.feature.adapter.ProductVerticalAdapter
import com.mobile.cleancase.feature.detail.ProductDetailActivity
import com.mobile.cleancase.presentation.base.BaseFragment
import com.mobile.cleancase.presentation.extension.launchActivity
import com.mobile.cleancase.presentation.extension.observeResponse
import com.mobile.cleancase.presentation.extension.setup
import com.mobile.cleancase.presentation.navigation.UiNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val productViewModel: ProductViewModel by viewModel()
    override val uiNavigation = UiNavigation.ROOT

    private val productVerticalAdapter by lazy {
        ProductVerticalAdapter(onItemClickListener = {
            context?.launchActivity<ProductDetailActivity> {
                putExtra(ProductDetailActivity.BUNDLE_PRODUCT_ID, it)
            }
        })
    }

    private val productHorizontalAdapter by lazy {
        ProductHorizontalAdapter(onItemClickListener = {
            context?.launchActivity<ProductDetailActivity> {
                putExtra(ProductDetailActivity.BUNDLE_PRODUCT_ID, it)
            }
        })
    }

    override fun initView() {
        super.initView()
        binding.apply {
            recyclerViewVerticalProducts.apply {
                setup(
                    layoutManager = LinearLayoutManager(context),
                    orientation = LinearLayoutManager.VERTICAL,
                    adapter = productVerticalAdapter
                )
            }

            recyclerViewHorizontalProducts.apply {
                setup(
                    layoutManager = LinearLayoutManager(context),
                    orientation = LinearLayoutManager.HORIZONTAL,
                    adapter = productHorizontalAdapter
                )
            }
        }
    }

    override fun fetchData() {
        super.fetchData()
        productViewModel.getProducts()
    }

    override fun observeData() {
        super.observeData()
        productViewModel.productsLiveData.observeResponse(
            owner = viewLifecycleOwner,
            progressView = binding.progressBar,
            success = {
                it?.banners?.let { productList ->
                    binding.apply {
                        if (productList.isNotEmpty()) {
                            recyclerViewVerticalProducts.visibility = View.VISIBLE
                            textViewEmptyList.visibility = View.GONE
                            productVerticalAdapter.updateList(productList as MutableList<ProductItem>)
                        } else {
                            recyclerViewVerticalProducts.visibility = View.GONE
                            textViewEmptyList.visibility = View.VISIBLE
                        }
                    }
                }
                it?.sliders?.let { productList ->
                    binding.apply {
                        if (productList.isNotEmpty()) {
                            recyclerViewHorizontalProducts.visibility = View.VISIBLE
                            textViewEmptyList.visibility = View.GONE
                            productHorizontalAdapter.updateList(productList as MutableList<ProductItem>)
                        } else {
                            recyclerViewHorizontalProducts.visibility = View.GONE
                            textViewEmptyList.visibility = View.VISIBLE
                        }
                    }
                }
            },
            fail = {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        )
    }

    companion object {
        fun newInstance() = ProductFragment()
    }
}