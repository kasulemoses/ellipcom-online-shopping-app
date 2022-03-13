package com.ellipcom.ellipcom.ui.construction.constructionMaterials.glasses

import androidx.recyclerview.widget.DiffUtil
import com.ellipcom.ellipcom.model.ProductInformationModel

class ConstructionGlassesDiffUtil(
    private val oldList: ArrayList<ProductInformationModel>,
    private val newList: ArrayList<ProductInformationModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].productId == newList[newItemPosition].productId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].productId != newList[newItemPosition].productId -> {
                false
            }
            oldList[oldItemPosition].productName != newList[newItemPosition].productName -> {
                false
            }
            oldList[oldItemPosition].productActualPrice != newList[newItemPosition].productActualPrice -> {
                false
            }
            oldList[oldItemPosition].productRegularPrice != newList[newItemPosition].productRegularPrice -> {
                false
            }
            oldList[oldItemPosition].productFullDescription != newList[newItemPosition].productFullDescription -> {
                false
            }
            oldList[oldItemPosition].productShortDescription != newList[newItemPosition].productShortDescription -> {
                false
            }
            oldList[oldItemPosition].productImageUrl != newList[newItemPosition].productImageUrl -> {
                false
            }
            oldList[oldItemPosition].productDeliveryType != newList[newItemPosition].productDeliveryType -> {
                false
            }
            oldList[oldItemPosition].productStatus != newList[newItemPosition].productStatus -> {
                false
            }
            else -> true
        }
    }

}