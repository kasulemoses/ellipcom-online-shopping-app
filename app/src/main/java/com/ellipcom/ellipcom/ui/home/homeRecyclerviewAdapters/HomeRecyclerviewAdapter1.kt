package com.ellipcom.ellipcom.ui.home.homeRecyclerviewAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.ProductRecyclerViewItemListBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.squareup.picasso.Picasso

class HomeRecyclerviewAdapter1(
    private val HomeProductAdapter1List: ArrayList<ProductInformationModel>,
    private val onProductClickListener: OnProductClickListener
) :
    RecyclerView.Adapter<HomeRecyclerviewAdapter1.HomeViewHolder1>() {


    private var productId = ""

    class HomeViewHolder1(val recyclerViewItemListBinding: ProductRecyclerViewItemListBinding) :
        RecyclerView.ViewHolder(recyclerViewItemListBinding.root)


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeViewHolder1 {

        return HomeViewHolder1(
            ProductRecyclerViewItemListBinding.inflate(
                LayoutInflater.from(p0.context),
                p0,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(p0: HomeViewHolder1, p1: Int) {


        p0.itemView.setOnClickListener {
            onProductClickListener.onProductItemClick(p1)


        }

        p0.recyclerViewItemListBinding.productActualPrice.text =
            HomeProductAdapter1List[p1].productActualPrice
        p0.recyclerViewItemListBinding.productName.text = HomeProductAdapter1List[p1].productName

        Picasso.get()
            .load(HomeProductAdapter1List[p1].productImageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(p0.recyclerViewItemListBinding.productImage)

        val actualPrice = HomeProductAdapter1List[p1].productActualPrice!!.toInt()
        val regularPrice = HomeProductAdapter1List[p1].productRegularPrice!!.toInt()

        val salesBadgePercentage = (actualPrice - regularPrice) * 100 / actualPrice

        p0.recyclerViewItemListBinding.productsSaleBadge.text = "$salesBadgePercentage%"

        //product id capture
        productId = HomeProductAdapter1List[p1].productId.toString()


    }

    override fun getItemCount(): Int {
        return HomeProductAdapter1List.size
    }

    fun saveProductId(pos: Int): String {
        val _PdtId = HomeProductAdapter1List[pos].productId.toString()
        return _PdtId
    }
}