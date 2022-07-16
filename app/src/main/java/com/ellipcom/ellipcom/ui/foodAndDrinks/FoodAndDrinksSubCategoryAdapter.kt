package com.ellipcom.ellipcom.ui.foodAndDrinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.MainSubCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.model.CategoryModel
import com.squareup.picasso.Picasso

class FoodAndDrinksSubCategoryAdapter(private var oldProductList:ArrayList<CategoryModel>) :
    RecyclerView.Adapter<FoodAndDrinksSubCategoryAdapter.ProductsViewHolder>() {



    class ProductsViewHolder(val productItemBinding: MainSubCategoryListForRecyclerViewBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)


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
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.productItemBinding.categoryImage)

    }


    override fun getItemCount(): Int {
        return oldProductList.size
    }


}