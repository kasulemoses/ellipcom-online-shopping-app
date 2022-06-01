package com.ellipcom.ellipcom.ui.houshold.electronics.television

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.databinding.MainProductItemListRecyclerBinding
import com.ellipcom.ellipcom.diffUtil.MainAppDiffUtil
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.squareup.picasso.Picasso

class TelevisionAdapter : RecyclerView.Adapter<TelevisionAdapter.TelevisionViewHolder>() {

    private var oldTelevisionProductList = ArrayList<ProductData>()

    class TelevisionViewHolder(val productItemBinding: MainProductItemListRecyclerBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelevisionViewHolder {
        return TelevisionViewHolder(
            MainProductItemListRecyclerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TelevisionViewHolder, position: Int) {
        holder.productItemBinding.productName.text = oldTelevisionProductList[position].pdtName
        holder.productItemBinding.productActualPrice.text =
            oldTelevisionProductList[position].pdtPrice
        holder.productItemBinding.productRegularPrice.text =
            oldTelevisionProductList[position].pdtRegularPrice

        Picasso.get().load(oldTelevisionProductList[position].pdtImageUrl)
            .into(holder.productItemBinding.productImage)

    }

    override fun getItemCount(): Int {
        return oldTelevisionProductList.size
    }

    fun setTelevisionData(newTelevisionList: ArrayList<ProductData>) {
        val diffUtil = MainAppDiffUtil(
            oldTelevisionProductList,
            newTelevisionList
        )
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldTelevisionProductList = newTelevisionList
        diffResults.dispatchUpdatesTo(this)
    }
}