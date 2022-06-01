package com.ellipcom.ellipcom.ui.education.educationServices.homeTutoring

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentHomeTutoringBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class HomeTutoringFragment : Fragment() {

    private var _binding: FragmentHomeTutoringBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductInformationModel>

    private val productAdapter by lazy { HomeTutoringAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeTutoringBinding.inflate(inflater, container, false)


        fireDb = FirebaseFirestore.getInstance()

        binding.homeTutoringRV.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()
        binding.homeTutoringRV.adapter = productAdapter

        binding.homeTutoringLinearBack.setOnClickListener {

        }
        attachingDataToRv()


        return binding.root
    }

    private fun attachingDataToRv() {

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

                            productAdapter.setData(productList)
                        }
                    }

                }

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}