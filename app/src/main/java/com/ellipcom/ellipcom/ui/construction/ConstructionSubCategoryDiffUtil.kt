package com.ellipcom.ellipcom.ui.construction

import androidx.recyclerview.widget.DiffUtil
import com.ellipcom.ellipcom.model.CategoryModel

class ConstructionSubCategoryDiffUtil(
    private val oldList: ArrayList<CategoryModel>,
    private val newList: ArrayList<CategoryModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].categoryName == newList[newItemPosition].categoryName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].categoryImageUrl != newList[newItemPosition].categoryImageUrl -> {
                false
            }
            oldList[oldItemPosition].categoryId != newList[newItemPosition].categoryId -> {
                false
            }
            else -> true
        }
    }

}