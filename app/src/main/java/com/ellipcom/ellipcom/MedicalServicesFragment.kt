package com.ellipcom.ellipcom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ellipcom.ellipcom.databinding.FragmentLaptopsBinding
import com.ellipcom.ellipcom.databinding.FragmentMedicalServicesBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.ellipcom.ellipcom.ui.houshold.computing.laptops.LaptopsAdapter
import com.google.firebase.firestore.FirebaseFirestore


class MedicalServicesFragment : Fragment() {

    private var _binding: FragmentMedicalServicesBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //arraylist
    private lateinit var productList: ArrayList<ProductInformationModel>

    private val productAdapter by lazy { LaptopsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medical_services, container, false)
    }


}