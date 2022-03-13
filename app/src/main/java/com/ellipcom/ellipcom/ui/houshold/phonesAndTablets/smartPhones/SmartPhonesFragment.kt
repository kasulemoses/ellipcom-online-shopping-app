package com.ellipcom.ellipcom.ui.houshold.phonesAndTablets.smartPhones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ellipcom.ellipcom.databinding.FragmentSmartPhonesBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class SmartPhonesFragment : Fragment() {

    private var _binding: FragmentSmartPhonesBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductInformationModel>

    private val smartPhonesAdapter by lazy { SmartPhonesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSmartPhonesBinding.inflate(inflater, container, false)


        fireDb = FirebaseFirestore.getInstance()

        binding.smartPhoneRV.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()
        binding.smartPhoneRV.adapter = smartPhonesAdapter
        attachingDataToRvsmartPhones()
        return binding.root
    }

    private fun attachingDataToRvsmartPhones() {
        fireDb.collection("test1")
            .document("doc_test2")
            .collection("col_test3")
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                        .show()
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (product in value.documentChanges) {
                        if (product.type == DocumentChange.Type.ADDED) {

                            productList.add(product.document.toObject(ProductInformationModel::class.java))

                            smartPhonesAdapter.setData(productList)
                        }
                    }

                }

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding  = null
    }



}