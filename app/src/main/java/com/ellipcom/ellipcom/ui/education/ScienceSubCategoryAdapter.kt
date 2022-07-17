package com.ellipcom.ellipcom.ui.education

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnEducationSubCategoryClickListener
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.MainCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.databinding.MainSubCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.diffUtil.MainCategoryDiffUtil
import com.ellipcom.ellipcom.model.CategoryModel
import com.squareup.picasso.Picasso

class ScienceSubCategoryAdapter(
    private val oldProductList: ArrayList<CategoryModel>,
    private val onCategoryClickListener: OnEducationSubCategoryClickListener
) :
    RecyclerView.Adapter<ScienceSubCategoryAdapter.ProductsViewHolder>() {


    class ProductsViewHolder(val categoryItemBinding: MainSubCategoryListForRecyclerViewBinding) :
        RecyclerView.ViewHolder(categoryItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            MainSubCategoryListForRecyclerViewBinding.inflate(
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
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.categoryItemBinding.categoryImage)

        holder.categoryItemBinding.cateName.text = oldProductList[position].categoryName

        holder.itemView.setOnClickListener {
            onCategoryClickListener.onScienceCategoryItemClick(position)
        }

    }

    fun storeSubCatInfo(position: Int): String {
        return oldProductList[position].categoryName.toString()

    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }

}