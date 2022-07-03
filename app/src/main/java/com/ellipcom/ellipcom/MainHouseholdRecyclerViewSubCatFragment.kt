package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.adapter.MainAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentMainHouseholdRecyclerViewSubCatBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.ui.houshold.HouseholdProductAdapter
import com.ellipcom.ellipcom.ui.houshold.HouseholdSubCategoryAdapter
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception


class MainHouseholdRecyclerViewSubCatFragment : Fragment() {

    private var _binding: FragmentMainHouseholdRecyclerViewSubCatBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainHouseholdRecyclerview: RecyclerView
    private lateinit var subCategoryRv: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductData>
    private lateinit var subCategoryList: ArrayList<CategoryModel>

    private val productAdapter by lazy { HouseholdProductAdapter(productList) }
    private val subCategoryAdapter by lazy { HouseholdSubCategoryAdapter(subCategoryList) }

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainHouseholdRecyclerViewSubCatBinding.inflate(inflater, container, false)

        fireDb = FirebaseFirestore.getInstance()
        mainHouseholdRecyclerview = binding.mainHouseholdSubCatRv
        mainHouseholdRecyclerview.setHasFixedSize(true)
        mainHouseholdRecyclerview.layoutManager = GridLayoutManager(context,2)

        productList = ArrayList()
        mainHouseholdRecyclerview.adapter = productAdapter

        binding.categoryImageBack.setOnClickListener {
            findNavController().navigate(R.id.action_mainHouseholdRecyclerViewSubCatFragment_to_navigation_home)
        }

        attachSubCategoryRVWithData()
        assigningMainHouseholdRecyclerview()

        //shared view model
//        sharedViewModel.householdSubCatViewId.observe(viewLifecycleOwner) {
//            if (it != null) {
//                assigningMainHouseholdRecyclerview(it)
//
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
        subCategoryRv = binding.householdSubCats
        subCategoryRv.setHasFixedSize(true)
        subCategoryRv.layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, true)

        subCategoryList = ArrayList()

        subCategoryRv.adapter = subCategoryAdapter

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_CATEGORY)
                .collection(EllipcomAppConstants.HOUSEHOLD_SUB_CATEGORIES)
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
    private fun assigningMainHouseholdRecyclerview() {
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_HOUSEHOLD)
                .collection("All_household_products")
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

        }
        catch (e:Exception){}

    }


}