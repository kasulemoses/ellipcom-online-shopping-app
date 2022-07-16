package com.ellipcom.ellipcom.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.ellipcom.ellipcom.model.ProductData

class MainAppDiffUtil(
    private val oldList: ArrayList<ProductData>,
    private val newList: ArrayList<ProductData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pdtId == newList[newItemPosition].pdtId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].pdtId != newList[newItemPosition].pdtId -> {
                false
            }
            oldList[oldItemPosition].pdtName != newList[newItemPosition].pdtName -> {
                false
            }
            oldList[oldItemPosition].pdtPrice != newList[newItemPosition].pdtPrice -> {
                false
            }
            oldList[oldItemPosition].pdtRegularPrice != newList[newItemPosition].pdtRegularPrice -> {
                false
            }
            oldList[oldItemPosition].pdtFullDescription != newList[newItemPosition].pdtFullDescription -> {
                false
            }
            oldList[oldItemPosition].pdtShorDescription != newList[newItemPosition].pdtShorDescription -> {
                false
            }
            oldList[oldItemPosition].pdtImageUrl != newList[newItemPosition].pdtImageUrl -> {
                false
            }
            oldList[oldItemPosition].pdtDeliveryStatus != newList[newItemPosition].pdtDeliveryStatus -> {
                false
            }
            oldList[oldItemPosition].pdtDateCreated != newList[newItemPosition].pdtDateCreated -> {
                false
            }
            else -> true
        }
    }

}