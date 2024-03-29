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
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.adapter.MainAppAdapter
import com.ellipcom.ellipcom.adapter.MainHomeAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentMainConstructionRecyclerviewBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.ui.construction.ConstructionProductAdapter
import com.ellipcom.ellipcom.ui.construction.ConstructionSubCategoryAdapter
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception


class MainConstructionRecyclerviewFragment : Fragment(), OnProductClickListener {

    private var _binding: FragmentMainConstructionRecyclerviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainConstructionRecyclerview: RecyclerView
    private lateinit var subCategoriesRecyclerview: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductData>
    private lateinit var subCategoriesList: ArrayList<CategoryModel>

    private val productAdapter by lazy { MainHomeAppAdapter(productList, this) }

    private val subCategoriesAdapter by lazy { ConstructionSubCategoryAdapter(subCategoriesList) }

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainConstructionRecyclerviewBinding.inflate(inflater, container, false)

        fireDb = FirebaseFirestore.getInstance()
        mainConstructionRecyclerview = binding.mainConstRecycler
        mainConstructionRecyclerview.setHasFixedSize(true)
        mainConstructionRecyclerview.layoutManager = GridLayoutManager(context,2)

        productList = ArrayList()
        mainConstructionRecyclerview.adapter = productAdapter


        attachSubCategoryRVWithData()

        //shared view model
        sharedViewModel.constructionViewId.observe(viewLifecycleOwner) {
            if (it != null) {
                assigningMainConstructionRecyclerview(it)

            } else {
                Toast.makeText(context, "there is no viewId", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun attachSubCategoryRVWithData() {

        subCategoriesRecyclerview = binding.constructionSubCatsRV
        subCategoriesRecyclerview.setHasFixedSize(true)
        subCategoriesRecyclerview.layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, true)

        subCategoriesList = ArrayList()

        subCategoriesRecyclerview.adapter = subCategoriesAdapter

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_CATEGORY)
                .collection(EllipcomAppConstants.CONSTRUCTION_SUB_CATEGORIES)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                subCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    subCategoriesAdapter.notifyDataSetChanged()
                }
        }
        catch (e:Exception){}

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun assigningMainConstructionRecyclerview(subCategoryViewId: String) {

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_CONSTRUCTION)
                .collection(EllipcomAppConstants.ALL_CONSTRUCTION_PRODUCT)
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
    override fun onProductItemClick(position: Int) {
        val dPdtDetails = productAdapter.saveProductId(position)
        sharedViewModel.savingPdtDetails(dPdtDetails)
        findNavController().navigate(R.id.action_mainConstructionRecyclerviewFragment_to_productDetailsFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}