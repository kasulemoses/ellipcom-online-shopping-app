package com.ellipcom.ellipcom.ui.construction.TechnicalServices.electricians

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentOverallBinding
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.ellipcom.ellipcom.ui.construction.safetyGears.overall.ConstructionOverallAdapter
import com.google.firebase.firestore.FirebaseFirestore


class ElectriciansFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_electricians, container, false)
    }



}