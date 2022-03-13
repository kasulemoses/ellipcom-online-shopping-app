package com.ellipcom.ellipcom.ui.houshold.electronics.projectors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ellipcom.ellipcom.databinding.FragmentProjectorsBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class ProjectorsFragment : Fragment() {

    private var _binding: FragmentProjectorsBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductInformationModel>

    private val projectorsAdapter by lazy { ProjectorsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProjectorsBinding.inflate(inflater, container, false)


        fireDb = FirebaseFirestore.getInstance()

        binding.projectorsRV.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()
        binding.projectorsRV.adapter = projectorsAdapter
        attachingDataToRvProjectors()
        return binding.root
    }

    private fun attachingDataToRvProjectors() {

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

                            projectorsAdapter.setData(productList)
                        }
                    }

                }

            }
    }


}