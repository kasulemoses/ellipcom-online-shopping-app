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
import com.ellipcom.ellipcom.databinding.FragmentAllPdtFromMedicalSubCatBinding
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.io.FileInputStream


class AllPdtFromMedicalSubCatFragment : Fragment() , OnProductClickListener {

    private var _binding: FragmentAllPdtFromMedicalSubCatBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var firebaseDb: FirebaseFirestore

    private lateinit var productList: ArrayList<ProductData>

    private lateinit var medicalSubCatRv: RecyclerView
    private lateinit var toolbarTitle: TextView

    private val subCatProductAdapter by lazy { MainSubCatProductAppAdapter(productList, this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentAllPdtFromMedicalSubCatBinding.inflate(inflater, container, false)

        binding.imageDetailBack.setOnClickListener {
            findNavController().navigate(R.id.action_allPdtFromMedicalSubCatFragment_to_allSubCategoriesFragment)
        }

        firebaseDb  = FirebaseFirestore.getInstance()
        toolbarTitle = binding.toolbarTitle

        medicalSubCatRv = binding.medicalSubCatRv
        medicalSubCatRv.setHasFixedSize(true)
        medicalSubCatRv.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()

        medicalSubCatRv.adapter = subCatProductAdapter

        getSubCatNameFromDb()

        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun loadingSubCatProducts(subDb: String) {

        firebaseDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_HEALTH_CARE)
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

        val fileName = "medical_sub_cat_name.tx"
        val filePath = requireContext().applicationContext.filesDir

        val file = File(filePath, fileName)
        val reader = FileInputStream(file)

        val content = ByteArray(file.length().toInt())

        reader.read(content)

        val catName = String(content)

        toolbarTitle.text = catName

        checkHMSubCatName(catName)
        checkPCareSubCatName(catName)
        checkBLoveSubCatName(catName)
        checkSRHSubCatName(catName)
        checkMServiceSubCatName(catName)

    }

    private fun checkHMSubCatName(catName:String){
        if (catName.uppercase() == "pain killers".uppercase()) {
            val subDb = "homeMedicine_homeMedicinePainKillers"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "herbal and syrups".uppercase()) {
            val subDb = "homeMedicine_herbalAndSyrups"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "vitamin and food supplement".uppercase()) {
            val subDb = "homeMedicine_vitaminAndSupplements"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Antibiotics".uppercase()) {
            val subDb = "homeMedicine_antibiotics"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPCareSubCatName(catName:String){

        if (catName.uppercase() == "cotton".uppercase()) {
            val subDb = "personalCare_Cotton"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "creams and lotion".uppercase()) {
            val subDb = "personalCare_creamsAndLotion"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "deodorants and perfume".uppercase()) {
            val subDb = "personalCare_deodorantsAndPerfume"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "gloves and masks".uppercase()) {
            val subDb = "personalCare_glovesAndMasks"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkBLoveSubCatName(catName:String){
        if (catName.uppercase() == "oils and baby lotion".uppercase()) {
            val subDb = "babyLove_oilsAndBabyLotion"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "pumpers and mama kits".uppercase()) {
            val subDb = "babyLove_humpersAndMamaKits"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Huggies".uppercase()) {
            val subDb = "babyLove_huggies"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "pampers and diapers".uppercase()) {
            val subDb = "babyLove_pampersAndDiapers"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "baby soap".uppercase()) {
            val subDb = "babyLove_babySoap"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkSRHSubCatName(catName:String){
        if (catName.uppercase() == "contraceptive".uppercase()) {
            val subDb = "sexualAndReproductiveHealth_contraceptives"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "HIV self test kits".uppercase()) {
            val subDb = "sexualAndReproductiveHealth_hivSelfTestKits"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "pregnancy test kit".uppercase()) {
            val subDb = "sexualAndReproductiveHealth_pregnancyTestStrip"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "sanitary pads".uppercase()) {
            val subDb = "sexualAndReproductiveHealth_sanitaryPad"
            loadingSubCatProducts(subDb)
        }  else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkMServiceSubCatName(catName:String){
        if (catName.uppercase() == "family doctor".uppercase()) {
            val subDbp = "familyDoctor"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "home nurses".uppercase()) {
            val subDbp = "homeNurses"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "personal doctor".uppercase()) {
            val subDbp = "personalDoctor"
            loadingSubCatProducts(subDbp)
        } else if (catName.uppercase() == "school nurses".uppercase()) {
            val subDbp = "schoolNurses"
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