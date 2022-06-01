package com.ellipcom.ellipcom.ui.houshold.electronics.television

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ellipcom.ellipcom.databinding.FragmentTelevisionBinding
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class TelevisionFragment : Fragment() {

    private var _binding: FragmentTelevisionBinding? = null
    private val binding get() = _binding!!

    //adapter
    private val televisionAdapter by lazy { TelevisionAdapter() }

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var tvArrayList: ArrayList<ProductData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTelevisionBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        fireDb = FirebaseFirestore.getInstance()

        binding.tvRV.layoutManager = GridLayoutManager(context, 2)
        tvArrayList = ArrayList()
        binding.tvRV.adapter = televisionAdapter

        attachingDataToRvTv()

        return binding.root
    }

    private fun attachingDataToRvTv() {

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

                            tvArrayList.add(product.document.toObject(ProductData::class.java))

                            televisionAdapter.setTelevisionData(tvArrayList)
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