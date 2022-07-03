package com.ellipcom.ellipcom.ui.medical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.MainCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.model.CategoryModel
import com.squareup.picasso.Picasso

class MedicalSubCategoryAdapter(private var oldProductList:ArrayList<CategoryModel>) :
    RecyclerView.Adapter<MedicalSubCategoryAdapter.ProductsViewHolder>() {



    class ProductsViewHolder(val productItemBinding: MainCategoryListForRecyclerViewBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)


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
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.productItemBinding.categoryImage)

    }


    override fun getItemCount(): Int {
        return oldProductList.size
    }

}