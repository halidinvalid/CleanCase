package com.mobile.cleancase.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobile.cleancase.databinding.ItemProductVerticalListBinding
import com.mobile.cleancase.domain.model.ProductItem
import com.mobile.cleancase.presentation.extension.loadImage

class ProductVerticalAdapter(private val onItemClickListener: ((Int?) -> Unit)? = null) :
    RecyclerView.Adapter<ProductVerticalAdapter.ProductViewHolder>() {

    private var oldProductList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding =
            ItemProductVerticalListBinding.inflate(
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

    fun updateList(list: MutableList<ProductItem>) {
        val diffResult = DiffUtil.calculateDiff(ProductDiffUtil(oldProductList, list))
        diffResult.dispatchUpdatesTo(this)
        oldProductList.clear()
        oldProductList = list
    }

    inner class ProductViewHolder(private val itemBinding: ItemProductVerticalListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(productItemData: ProductItem?) {
            itemBinding.apply {
                productItemData?.let { productItem ->
                    productItem.image?.let { image ->
                        image.url?.loadImage(
                            context = root.context,
                            imageView = imageViewProduct,
                            width = image.width!!,
                            height = image.height!!
                        )
                    }
                    itemView.setOnClickListener {
                        onItemClickListener?.invoke(productItem.id)
                    }
                }
            }
        }
    }
}