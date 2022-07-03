package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.adapter.MainAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentMainFoodAndDrinksRecyclerviewForSubcatsBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.ui.foodAndDrinks.FoodAndDrinksProductAdapter
import com.ellipcom.ellipcom.ui.foodAndDrinks.FoodAndDrinksSubCategoryAdapter
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class MainFoodAndDrinksRecyclerviewForSubCatsFragment : Fragment() {

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()


    //view binding
    private var _binding: FragmentMainFoodAndDrinksRecyclerviewForSubcatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainFoodAndDrinkRecyclerview: RecyclerView
    private lateinit var subCategoryRecyclerview: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductData>
    private lateinit var subCategoryList: ArrayList<CategoryModel>

    private val productAdapter by lazy { FoodAndDrinksProductAdapter(productList) }
    private val subCategoryAdapter by lazy { FoodAndDrinksSubCategoryAdapter(subCategoryList) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainFoodAndDrinksRecyclerviewForSubcatsBinding.inflate(
            inflater,
            container,
            false
        )

        fireDb = FirebaseFirestore.getInstance()

        mainFoodAndDrinkRecyclerview = binding.mainFoodAndDrinksRv
        mainFoodAndDrinkRecyclerview.setHasFixedSize(true)
        mainFoodAndDrinkRecyclerview.layoutManager = GridLayoutManager(context, 2)

        productList = ArrayList()
        mainFoodAndDrinkRecyclerview.adapter = productAdapter

        attachSubCategoryRVWithData()
        assigningMainFoodAndDrinksRecyclerview()
        //shared view model
//        sharedViewModel.educationViewId.observe(viewLifecycleOwner) {
//            if (it != null) {
//
//            } else {
//                Toast.makeText(context, "there is no viewId", Toast.LENGTH_SHORT).show()
//            }
//
//        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun attachSubCategoryRVWithData() {
        subCategoryRecyclerview = binding.foodAndDrinksSubCatsRV
        subCategoryRecyclerview.setHasFixedSize(true)
        subCategoryRecyclerview.layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, true)

        subCategoryList = ArrayList()

        subCategoryRecyclerview.adapter = subCategoryAdapter

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_CATEGORY)
                .collection(EllipcomAppConstants.FOOD_AND_DRINKS_SUB_CATEGORIES)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                subCategoryList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    subCategoryAdapter.notifyDataSetChanged()
                }
        }
        catch (e: Exception){}

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun assigningMainFoodAndDrinksRecyclerview() {
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_FOOD_AND_DRINKS)
                .collection(EllipcomAppConstants.ALL_FOOD_AND_DRINK_PRODUCT)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                productList.add(product.document.toObject(ProductData::class.java))

                            }
                        }

                    }
                    productAdapter.notifyDataSetChanged()
                }
        }catch (e:Exception){

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}