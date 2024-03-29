package com.ellipcom.ellipcom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.databinding.MainCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.diffUtil.MainCategoryDiffUtil
import com.ellipcom.ellipcom.model.CategoryModel
import com.squareup.picasso.Picasso

class MainCategoryAdapter :
    RecyclerView.Adapter<MainCategoryAdapter.ProductsViewHolder>() {

    private var oldProductList = ArrayList<CategoryModel>()

    class ProductsViewHolder(val categoryItemBinding: MainCategoryListForRecyclerViewBinding) :
        RecyclerView.ViewHolder(categoryItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            MainCategoryListForRecyclerViewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        Picasso.get().load(
            oldProductList[position]
                .categoryImageUrl
        )
            .into(holder.categoryItemBinding.categoryImage)

    }


    override fun getItemCount(): Int {
        return oldProductList.size
    }

    fun setData(newList: ArrayList<CategoryModel>) {
        val diffUtil = MainCategoryDiffUtil(
            oldProductList,
            newList
        )
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldProductList = newList
        diffResults.dispatchUpdatesTo(this)
    }
}