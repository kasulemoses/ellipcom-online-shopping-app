package com.ellipcom.ellipcom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.adapter.MainAAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentMainConstructionRecyclerviewBinding
import com.ellipcom.ellipcom.databinding.FragmentMainHouseholdRecyclerViewSubCatBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.ellipcom.ellipcom.ui.construction.safetyGears.helmets.HelmetAdapter
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception


class MainHouseholdRecyclerViewSubCatFragment : Fragment() {

    private var _binding: FragmentMainHouseholdRecyclerViewSubCatBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainHouseholdRecyclerview: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductData>

    private val productAdapter by lazy { MainAAppAdapter() }

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
        mainHouseholdRecyclerview.layoutManager = LinearLayoutManager(context)

        productList = ArrayList()
        mainHouseholdRecyclerview.adapter = productAdapter

        //shared view model
        sharedViewModel.householdSubCatViewId.observe(viewLifecycleOwner) {
            if (it != null) {
                assigningMainHouseholdRecyclerview(it)


            } else {
                Toast.makeText(context, "there is no viewId", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    private fun assigningMainHouseholdRecyclerview(householdSubCat: String) {
        Toast.makeText(context, householdSubCat, Toast.LENGTH_SHORT).show()
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_HOUSEHOLD)
                .collection(householdSubCat)
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