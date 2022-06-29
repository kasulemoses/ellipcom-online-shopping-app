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

class MainHomeFragmentCategoryAdapter(private var oldProductList:ArrayList<CategoryModel>) :
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
            .into(holder.categoryItemBinding.categoryImage)

        holder.categoryItemBinding.cateName.text = oldProductList[position].categoryName

        holder.itemView.setOnClickListener {
            if (oldProductList[position].categoryName == "household"){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainHouseholdRecyclerViewSubCatFragment)
            }
            else if (oldProductList[position].categoryName == "construction"){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainConstructionRecyclerviewFragment)
            }
            else if (oldProductList[position].categoryName == it.context.getString(R.string.educ_cat)){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainEducationRecyclerviewFragment)
            }
            else if (oldProductList[position].categoryName == it.context.getString(R.string.medic_cat)){
                it.findNavController().navigate(R.id.action_navigation_home_to_mainMedicalRecyclerViewForSubCatFragment)
            }
            else if (oldProductList[position].categoryName == "food_and_drinks"){
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