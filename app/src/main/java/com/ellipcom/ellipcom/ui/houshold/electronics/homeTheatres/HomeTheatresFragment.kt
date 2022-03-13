package com.ellipcom.ellipcom.ui.houshold.electronics.homeTheatres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ellipcom.ellipcom.databinding.FragmentHomeTheatresBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class HomeTheatresFragment : Fragment() {
    private var _binding: FragmentHomeTheatresBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductInformationModel>

    private val homeTheatresAdapter by lazy { HomeTheatresAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeTheatresBinding.inflate(inflater, container, false)


        fireDb = FirebaseFirestore.getInstance()

        binding.homeTheatreRV.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()
        binding.homeTheatreRV.adapter = homeTheatresAdapter
        attachingDataToRvHomeTheatres()
        return binding.root
    }
    private fun attachingDataToRvHomeTheatres() {

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

                            homeTheatresAdapter.setData(productList)
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