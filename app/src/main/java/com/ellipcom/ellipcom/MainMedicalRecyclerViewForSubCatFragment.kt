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
import com.ellipcom.ellipcom.databinding.FragmentMainMedicalRecyclerViewForSubCatBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.ui.medical.MedicalProductAdapter
import com.ellipcom.ellipcom.ui.medical.MedicalSubCategoryAdapter
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class MainMedicalRecyclerViewForSubCatFragment : Fragment() {

    //view binding
    private var _binding: FragmentMainMedicalRecyclerViewForSubCatBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainMedicalRecyclerview: RecyclerView
    private lateinit var subCategoryRv: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductData>
    private lateinit var subCategoryList: ArrayList<CategoryModel>

    private val productAdapter by lazy { MedicalProductAdapter(productList) }
    private val subCategoryAdapter by lazy { MedicalSubCategoryAdapter(subCategoryList) }

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentMainMedicalRecyclerViewForSubCatBinding.inflate(inflater, container, false)

        fireDb = FirebaseFirestore.getInstance()

        mainMedicalRecyclerview = binding.mainMedicalSubCatRv
        mainMedicalRecyclerview.setHasFixedSize(true)
        mainMedicalRecyclerview.layoutManager = GridLayoutManager(context, 2)

        productList = ArrayList()
        mainMedicalRecyclerview.adapter = productAdapter

        attachSubCategoryRVWithData()
        assigningMainMedicalRecyclerview()

        //shared view model
//        sharedViewModel.medicalSubCatViewId.observe(viewLifecycleOwner) {
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
        subCategoryRv = binding.medicalSubCatsRV
        subCategoryRv.setHasFixedSize(true)
        subCategoryRv.layoutManager = GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, true)

        subCategoryList = ArrayList()

        subCategoryRv.adapter = subCategoryAdapter

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_CATEGORY)
                .collection(EllipcomAppConstants.MEDICAL_SUB_CATEGORIES)
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
        catch (e:Exception){}

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun assigningMainMedicalRecyclerview() {

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_HEALTH_CARE)
                .collection(EllipcomAppConstants.ALL_MEDICAL_PRODUCT)
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
        catch (e:Exception){

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}