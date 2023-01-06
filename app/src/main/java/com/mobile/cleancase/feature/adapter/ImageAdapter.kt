package com.mobile.cleancase.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobile.cleancase.databinding.ItemProductHorizontalListBinding
import com.mobile.cleancase.domain.model.ImageDetail
import com.mobile.cleancase.presentation.extension.loadImage

class ImageAdapter() :
    RecyclerView.Adapter<ImageAdapter.ProductViewHolder>() {

    private var oldProductList = mutableListOf<ImageDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding =
            ItemProductHorizontalListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(oldProductList[position])
    }

    fun updateList(list: MutableList<ImageDetail>) {
        val diffResult = DiffUtil.calculateDiff(ImageDiffUtil(oldProductList, list))
        diffResult.dispatchUpdatesTo(this)
        oldProductList.clear()
        oldProductList = list
    }

    inner class ProductViewHolder(private val itemBinding: ItemProductHorizontalListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(productItemData: ImageDetail?) {
            itemBinding.apply {
                productItemData?.let { image ->
                    image.url?.loadImage(
                        context = root.context,
                        imageView = imageViewProduct,
                        width = image.width!!,
                        height = image.height!!
                    )
                }
            }
        }
    }
}