package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.adapter.MainSubCatProductAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentAllPdtFromHouseholdSubCatBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.mainSharedViewModel.TestSharedViewModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class AllPdtFromHouseholdSubCatFragment : Fragment(), OnProductClickListener {

    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()
    private val sharedViewModelp: TestSharedViewModel by activityViewModels()

    private var _binding: FragmentAllPdtFromHouseholdSubCatBinding? = null
    private val binding get() = _binding!!

    private lateinit var householdSubCatRv: RecyclerView

    //firebase
    private lateinit var firebaseDb: FirebaseFirestore


    private lateinit var productList: ArrayList<ProductData>

    private val subCatProductAdapter by lazy { MainSubCatProductAppAdapter(productList, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllPdtFromHouseholdSubCatBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        firebaseDb = FirebaseFirestore.getInstance()

        val toolbarTitle = binding.toolbarTitle

        sharedViewModel.electronicSubDetails.observe(viewLifecycleOwner) {
            toolbarTitle.text = it.toString()

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

            if (it.toString().uppercase() == "projectors".uppercase()) {
                val subDb = "electronics_projectors"
                loadingSubCatProducts(subDb)
            } else if (it.toString().uppercase() == "television".uppercase()) {
                val subDb = "electronics_television"
                loadingSubCatProducts(subDb)
            } else if (it.toString().uppercase() == "portable audio".uppercase()) {
                val subDb = "electronics_portableAudio"
                loadingSubCatProducts(subDb)
            } else if (it.toString().uppercase() == "speakers".uppercase()) {
                val subDb = "electronics_speakers"
                loadingSubCatProducts(subDb)
            } else if (it.toString().uppercase() == "camera".uppercase()) {
                val subDb = "electronics_digitalCamera"
                loadingSubCatProducts(subDb)
            } else if (it.toString().uppercase() == "dvd players".uppercase()) {
                val subDb = "electronics_dvdPlayer"
                loadingSubCatProducts(subDb)
            } else if (it.toString().uppercase() == "home theatres".uppercase()) {
                val subDb = "electronics_homeTheatres"
                loadingSubCatProducts(subDb)
            } else {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }

        }


        sharedViewModelp.phonesAndTabletsSubDetails.observe(viewLifecycleOwner) {
            toolbarTitle.text = it.toString()

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

            if (it.toString().uppercase() == "tablets".uppercase()) {
                val subDbp = "phonesAndTablets_tablets"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "laptop bags".uppercase()) {
                val subDbp = "phonesAndTablets_laptopBags"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "land lines".uppercase()) {
                val subDbp = "phonesAndTablets_phoneAndLandLines"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "featured phones".uppercase()) {
                val subDbp = "phonesAndTablets_featuredPhones"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "head phones".uppercase()) {
                val subDbp = "electronics_digitalCamera"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "smart phones".uppercase()) {
                val subDbp = "phonesAndTablets_smartPhones"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "chargers".uppercase()) {
                val subDbp = "phonesAndTablets_phoneChargers"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "memory cards".uppercase()) {
                val subDbp = "phonesAndTablets_memoryCards"
                loadingSubCatProducts(subDbp)
            } else if (it.toString().uppercase() == "connectivity".uppercase()) {
                val subDbp = "phonesAndTablets_dataAndConnectivity"
                loadingSubCatProducts(subDbp)
            } else {

            }

        }

        householdSubCatRv = binding.householdSubCatRv
        householdSubCatRv.setHasFixedSize(true)
        householdSubCatRv.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()

        householdSubCatRv.adapter = subCatProductAdapter


        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadingSubCatProducts(subDb: String) {

        firebaseDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_HOUSEHOLD)
            .collection(subDb)
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

                    subCatProductAdapter.notifyDataSetChanged()

                }
            }

    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun onProductItemClick(position: Int) {

    }
}