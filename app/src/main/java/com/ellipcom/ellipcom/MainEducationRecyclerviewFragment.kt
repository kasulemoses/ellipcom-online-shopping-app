package com.ellipcom.ellipcom

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
import com.ellipcom.ellipcom.databinding.FragmentMainEducationRecyclerviewBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.ui.education.EducationSubCategoryAdapter
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class MainEducationRecyclerviewFragment : Fragment() {

    private var _binding: FragmentMainEducationRecyclerviewBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainEducationRecyclerview: RecyclerView
    private lateinit var educationSubCategoriesRecyclerview: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductData>
    private lateinit var educSubCategoriesList: ArrayList<CategoryModel>

    private val productAdapter by lazy { MainAppAdapter() }
    private val educSubCategoriesAdapter by lazy { EducationSubCategoryAdapter() }

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainEducationRecyclerviewBinding.inflate(inflater, container, false)

        fireDb = FirebaseFirestore.getInstance()

        mainEducationRecyclerview = binding.educationSubCatMainRecyclerView
        mainEducationRecyclerview.setHasFixedSize(true)
        mainEducationRecyclerview.layoutManager = GridLayoutManager(context, 2)

        productList = ArrayList()
        mainEducationRecyclerview.adapter = productAdapter

        attachSubCategoryRVWithData()

        //shared view model
        sharedViewModel.educationViewId.observe(viewLifecycleOwner) {
            if (it != null) {
                assigningMainConstructionRecyclerview(it)
            } else {
                Toast.makeText(context, "there is no viewId", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun attachSubCategoryRVWithData() {
        educationSubCategoriesRecyclerview = binding.educationSubCatsRV
        educationSubCategoriesRecyclerview.setHasFixedSize(true)
        educationSubCategoriesRecyclerview.layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, true)

        educSubCategoriesList = ArrayList()

        educationSubCategoriesRecyclerview.adapter = educSubCategoriesAdapter

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_CATEGORY)
                .collection(EllipcomAppConstants.EDUCATION_SUB_CATEGORIES)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                educSubCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                                educSubCategoriesAdapter.setData(educSubCategoriesList)
                            }
                        }

                    }

                }
        }
        catch (e: Exception){}

    }

    private fun assigningMainConstructionRecyclerview(educationViewId: String) {

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_EDUCATION)
                .collection(EllipcomAppConstants.ALL_EDUCATION_PRODUCT)
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

                                productAdapter.setData(productList)
                            }
                        }

                    }


                }

        }
        catch (e:Exception){}


    }


}