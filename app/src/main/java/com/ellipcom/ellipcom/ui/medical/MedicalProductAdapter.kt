package com.ellipcom.ellipcom.ui.medical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.MainCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.databinding.MainProductItemListRecyclerBinding
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.squareup.picasso.Picasso

class MedicalProductAdapter(private var oldProductList:ArrayList<ProductData>) :
    RecyclerView.Adapter<MedicalProductAdapter.ProductsViewHolder>() {



    class ProductsViewHolder(val productItemBinding: MainProductItemListRecyclerBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            MainProductItemListRecyclerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.productItemBinding.productName.text = oldProductList[position].pdtName
        holder.productItemBinding.productActualPrice.text =
            oldProductList[position].pdtPrice
        holder.productItemBinding.productRegularPrice.text =
            oldProductList[position].pdtRegularPrice

        Picasso.get().load(oldProductList[position].pdtImageUrl).
                placeholder(R.drawable.ic_launcher_background)
            .into(holder.productItemBinding.productImage)
    }


    override fun getItemCount(): Int {
        return oldProductList.size
    }

}