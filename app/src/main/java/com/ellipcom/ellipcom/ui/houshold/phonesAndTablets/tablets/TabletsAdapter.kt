package com.ellipcom.ellipcom.ui.houshold.phonesAndTablets.tablets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.databinding.MainProductItemListRecyclerBinding
import com.ellipcom.ellipcom.diffUtil.TelevisionDiffUtil
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.squareup.picasso.Picasso

class TabletsAdapter : RecyclerView.Adapter<TabletsAdapter.TabletsViewHolder>() {

    private var oldProductList = ArrayList<ProductInformationModel>()

    class TabletsViewHolder(val productItemBinding: MainProductItemListRecyclerBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TabletsViewHolder {
        return TabletsViewHolder(
            MainProductItemListRecyclerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TabletsViewHolder, position: Int) {
        holder.productItemBinding.productName.text = oldProductList[position].productName
        holder.productItemBinding.productActualPrice.text =
            oldProductList[position].productActualPrice
        holder.productItemBinding.productRegularPrice.text =
            oldProductList[position].productRegularPrice

        Picasso.get().load(oldProductList[position].productImageUrl)
            .into(holder.productItemBinding.productImage)

    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }

    fun setData(newList: ArrayList<ProductInformationModel>) {
        val diffUtil = TabletsDiffUtil(
            oldProductList,
            newList
        )
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldProductList = newList
        diffResults.dispatchUpdatesTo(this)
    }
}