package com.mobile.cleancase.feature.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mobile.cleancase.domain.model.ImageDetail

class ImageDiffUtil(
    private val oldList: List<ImageDetail>,
    private val newList: List<ImageDetail>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url === newList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}