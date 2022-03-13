package com.ellipcom.ellipcom.ui.houshold.electronics.portableAudio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ellipcom.ellipcom.databinding.FragmentPortableAudioBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class PortableAudioFragment : Fragment() {

    private var _binding: FragmentPortableAudioBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductInformationModel>

    private val portableAudioAdapter by lazy { PortableAudioAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPortableAudioBinding.inflate(inflater, container, false)


        fireDb = FirebaseFirestore.getInstance()

        binding.portableAudioRV.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()
        binding.portableAudioRV.adapter = portableAudioAdapter
        attachingDataToRvPortableAudio()
        return binding.root
    }

    private fun attachingDataToRvPortableAudio() {
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

                            portableAudioAdapter.setData(productList)
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