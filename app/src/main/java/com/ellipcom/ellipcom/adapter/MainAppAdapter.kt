package com.ellipcom.ellipcom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.databinding.MainProductItemListRecyclerBinding
import com.ellipcom.ellipcom.diffUtil.MainAppDiffUtil
import com.ellipcom.ellipcom.model.ProductData
import com.squareup.picasso.Picasso

class MainAppAdapter :
    RecyclerView.Adapter<MainAppAdapter.ProductsViewHolder>() {

    private var oldProductList = ArrayList<ProductData>()

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

        Picasso.get().load(oldProductList[position].pdtImageUrl)
            .into(holder.productItemBinding.productImage)

    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }

    fun setData(newList: ArrayList<ProductData>) {
        val diffUtil = MainAppDiffUtil(
            oldProductList,
            newList
        )
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldProductList = newList
        diffResults.dispatchUpdatesTo(this)
    }
}