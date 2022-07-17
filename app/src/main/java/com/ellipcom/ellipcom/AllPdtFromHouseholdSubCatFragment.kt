package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.adapter.MainSubCatProductAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentAllPdtFromHouseholdSubCatBinding
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.model.SubCatStored
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class AllPdtFromHouseholdSubCatFragment : Fragment(), OnProductClickListener {

    private var _binding: FragmentAllPdtFromHouseholdSubCatBinding? = null
    private val binding get() = _binding!!

    private lateinit var householdSubCatRv: RecyclerView
    private lateinit var toolbarTitle: TextView

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


        binding.imageDetailBack.setOnClickListener {
            findNavController().navigate(R.id.action_allPdtFromHouseholdSubCatFragment_to_allSubCategoriesFragment)
        }

        toolbarTitle = binding.toolbarTitle

        householdSubCatRv = binding.householdSubCatRv
        householdSubCatRv.setHasFixedSize(true)
        householdSubCatRv.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()

        householdSubCatRv.adapter = subCatProductAdapter

        getSubCatNameFromDb()

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

    private fun getSubCatNameFromDb() {

        val fileName = "sub_cat_name.tx"
        val filePath = requireContext().applicationContext.filesDir

        val file = File(filePath, fileName)
        val reader = FileInputStream(file)

        val content = ByteArray(file.length().toInt())

        reader.read(content)

        val catName = String(content)

        toolbarTitle.text = catName

        checkElectronicsSubCatName(catName)
        checkPhonesSubCatName(catName)
        checkHOSubCatName(catName)
        checkComputingSubCatName(catName)
        checkFurnitureSubCatName(catName)


    }

    private fun checkFurnitureSubCatName(catName:String){
        if (catName.uppercase() == "sideboards".uppercase()) {
            val subDb = "furniture_sideboards"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "sofas".uppercase()) {
            val subDb = "furniture_sofas"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "beds".uppercase()) {
            val subDb = "furniture_beds"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "chairs".uppercase()) {
            val subDb = "furniture_chairs"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "doors".uppercase()) {
            val subDb = "furniture_doors"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "tables".uppercase()) {
            val subDb = "furniture_tables"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkComputingSubCatName(catName:String){
        if (catName.uppercase() == "CPU's".uppercase()) {
            val subDb = "computing_cpu"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "laptop bags".uppercase()) {
            val subDb = "computing_laptopBags"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "desktop computers".uppercase()) {
            val subDb = "computing_desktopComputer"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "hard drive".uppercase()) {
            val subDb = "computing_externalAndInternalHardDrive"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "USB flash drive".uppercase()) {
            val subDb = "computing_usbFlashDrives"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "computer accessories".uppercase()) {
            val subDb = "computing_accessories"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "laptops".uppercase()) {
            val subDb = "computing_laptops"
            loadingSubCatProducts(subDb)
        }  else if (catName.uppercase() == "monitors".uppercase()) {
            val subDb = "computing_computerMonitors"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "printer and scanners".uppercase()) {
            val subDb = "computing_printerAndScanners"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkHOSubCatName(catName:String){
        if (catName.uppercase() == "small appliances".uppercase()) {
            val subDb = "homeAndOffice_smallAppliances"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "vacuum cleaner".uppercase()) {
            val subDb = "homeAndOffice_vacuumCleaners"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "extensions".uppercase()) {
            val subDb = "homeAndOffice_electricExtensions"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "cookers".uppercase()) {
            val subDb = "homeAndOffice_cookers"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "utensils".uppercase()) {
            val subDb = "homeAndOffice_utensils"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "microwave".uppercase()) {
            val subDb = "homeAndOffice_microwaves"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "washing machine".uppercase()) {
            val subDb = "homeAndOffice_washingMachines"
            loadingSubCatProducts(subDb)
        }  else if (catName.uppercase() == "carpets".uppercase()) {
            val subDb = "homeAndOffice_carpets"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "refrigerators".uppercase()) {
            val subDb = "homeAndOffice_refrigerator"
            loadingSubCatProducts(subDb)
        }
        else {
           // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkElectronicsSubCatName(catName:String){
        if (catName.uppercase() == "projectors".uppercase()) {
            val subDb = "electronics_projectors"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "television".uppercase()) {
            val subDb = "electronics_television"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "portable audio".uppercase()) {
            val subDb = "electronics_portableAudio"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "speakers".uppercase()) {
            val subDb = "electronics_speakers"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "camera".uppercase()) {
            val subDb = "electronics_digitalCamera"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "dvd players".uppercase()) {
            val subDb = "electronics_dvdPlayer"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "home theatres".uppercase()) {
            val subDb = "electronics_homeTheatres"
            loadingSubCatProducts(subDb)
        } else {
           // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPhonesSubCatName(catName:String){
        if (catName.uppercase() == "tablets".uppercase()) {
            val subDbp = "phonesAndTablets_tablets"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "laptop bags".uppercase()) {
            val subDbp = "phonesAndTablets_laptopBags"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "land lines".uppercase()) {
            val subDbp = "phonesAndTablets_phoneAndLandLines"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "featured phones".uppercase()) {
            val subDbp = "phonesAndTablets_featuredPhones"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "head phones".uppercase()) {
            val subDbp = "electronics_digitalCamera"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "smart phones".uppercase()) {
            val subDbp = "phonesAndTablets_smartPhones"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "chargers".uppercase()) {
            val subDbp = "phonesAndTablets_phoneChargers"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "memory cards".uppercase()) {
            val subDbp = "phonesAndTablets_memoryCards"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "connectivity".uppercase()) {
            val subDbp = "phonesAndTablets_dataAndConnectivity"
            loadingSubCatProducts(subDbp)
        } else {
            //Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun onProductItemClick(position: Int) {

    }
}