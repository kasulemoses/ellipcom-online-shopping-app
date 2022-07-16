package com.ellipcom.ellipcom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.MainProductItemListRecyclerBinding
import com.ellipcom.ellipcom.diffUtil.MainAppDiffUtil
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.DetailPdtModel
import com.ellipcom.ellipcom.model.ProductData
import com.squareup.picasso.Picasso

class MainHomeAppAdapter(
    private val oldProductList: ArrayList<ProductData>,
    private val onProductClickListener: OnProductClickListener
) :
    RecyclerView.Adapter<MainHomeAppAdapter.ProductsViewHolder>() {

    private var productId = ""



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

        holder.itemView.setOnClickListener {
            onProductClickListener.onProductItemClick(position)

        }


        holder.productItemBinding.productName.text = oldProductList[position].pdtName
        holder.productItemBinding.productActualPrice.text =
            oldProductList[position].pdtPrice
        holder.productItemBinding.productRegularPrice.text =
            oldProductList[position].pdtRegularPrice

        Picasso.get().load(oldProductList[position].pdtImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.productItemBinding.productImage)


//        val actualPrice = oldProductList[position].pdtPrice!!.toInt()
//        val regularPrice = oldProductList[position].pdtRegularPrice!!.toInt()
//
//        val salesBadgePercentage = (actualPrice - regularPrice) * 100 / actualPrice
//
//        holder.productItemBinding.productsSaleBadge.text = "$salesBadgePercentage%"

        //product id capture
        productId = oldProductList[position].pdtId.toString()
    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }


    fun saveProductId(pos: Int): DetailPdtModel {
        val productId = oldProductList[pos].pdtId.toString()
        val productName = oldProductList[pos].pdtName.toString()
        val productImageUrl = oldProductList[pos].pdtImageUrl.toString()
        val productRegularPrice = oldProductList[pos].pdtRegularPrice.toString()
        val productActualPrice = oldProductList[pos].pdtPrice.toString()
        val productShortDescription = oldProductList[pos].pdtShorDescription.toString()
        val productFullDescription = oldProductList[pos].pdtFullDescription.toString()
        val productStatus = oldProductList[pos].pdtDateCreated.toString()
        val productDeliveryType = oldProductList[pos].pdtDeliveryStatus.toString()

        val dPdtModel = DetailPdtModel(
            productId,
            productName,
            productImageUrl,
            productRegularPrice,
            productActualPrice,
            productShortDescription,
            productFullDescription,
            productStatus,
            productDeliveryType
        )


        return dPdtModel
    }
}