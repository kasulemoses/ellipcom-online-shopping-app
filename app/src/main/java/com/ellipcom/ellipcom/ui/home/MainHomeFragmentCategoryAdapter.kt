package com.ellipcom.ellipcom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.MainCategoryListForRecyclerViewBinding
import com.ellipcom.ellipcom.diffUtil.MainCategoryDiffUtil
import com.ellipcom.ellipcom.model.CategoryModel
import com.squareup.picasso.Picasso

class MainHomeFragmentCategoryAdapter(private val oldProductList:ArrayList<CategoryModel>) :
    RecyclerView.Adapter<MainHomeFragmentCategoryAdapter.ProductsViewHolder>() {



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
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.categoryItemBinding.categoryImage)

        holder.categoryItemBinding.cateName.text = oldProductList[position].categoryName

        holder.itemView.setOnClickListener {
            if (oldProductList[position].categoryName!!.uppercase() == "household".uppercase()){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainHouseholdRecyclerViewSubCatFragment)
            }
            else if (oldProductList[position].categoryName!!.uppercase() == "construction".uppercase()){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainConstructionRecyclerviewFragment)
            }
            else if (oldProductList[position].categoryName!!.uppercase() == it.context.getString(R.string.educ_cat).uppercase()){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainEducationRecyclerviewFragment)
            }
            else if (oldProductList[position].categoryName!!.uppercase() == "medical".uppercase()){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainMedicalRecyclerViewForSubCatFragment)
            }
            else if (oldProductList[position].categoryName!!.uppercase() == "food and drinks".uppercase()){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainFoodAndDrinksRecyclerviewForSubcatsFragment)

            }
            else{
                Toast.makeText(it.context, oldProductList[position].categoryName, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun getItemCount(): Int {
        return oldProductList.size
    }

//    fun setData(newList: ArrayList<CategoryModel>) {
//        val diffUtil = MainHomeFragmentCategoryDiffUtil(
//            oldProductList,
//            newList
//        )
//        val diffResults = DiffUtil.calculateDiff(diffUtil)
//        oldProductList = newList
//        diffResults.dispatchUpdatesTo(this)
//    }
}