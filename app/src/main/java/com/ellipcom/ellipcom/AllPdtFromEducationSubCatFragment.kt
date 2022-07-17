package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.adapter.MainSubCatProductAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentAllPdtFromEducationSubCatBinding
import com.ellipcom.ellipcom.databinding.FragmentAllPdtFromMedicalSubCatBinding
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.io.FileInputStream


class AllPdtFromEducationSubCatFragment : Fragment() , OnProductClickListener {


    private var _binding: FragmentAllPdtFromEducationSubCatBinding? = null
    private val binding get() = _binding!!

    //firebase
    private lateinit var firebaseDb: FirebaseFirestore

    private lateinit var productList: ArrayList<ProductData>

    private lateinit var educationSubCatRv: RecyclerView
    private lateinit var toolbarTitle: TextView

    private val subCatProductAdapter by lazy { MainSubCatProductAppAdapter(productList, this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAllPdtFromEducationSubCatBinding.inflate(inflater, container, false)

        firebaseDb = FirebaseFirestore.getInstance()

        binding.imageDetailBack.setOnClickListener {
            findNavController().navigate(R.id.action_allPdtFromEducationSubCatFragment_to_allSubCategoriesFragment)
        }

        toolbarTitle = binding.toolbarTitle

        educationSubCatRv = binding.educSubCatRv
        educationSubCatRv.setHasFixedSize(true)
        educationSubCatRv.layoutManager = GridLayoutManager(context, 2)
        productList = ArrayList()

        educationSubCatRv.adapter = subCatProductAdapter

        getSubCatNameFromDb()

        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun loadingSubCatProducts(subDb: String) {

        firebaseDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_EDUCATION)
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

        val fileName = "education_sub_cat_name.tx"
        val filePath = requireContext().applicationContext.filesDir

        val file = File(filePath, fileName)
        val reader = FileInputStream(file)

        val content = ByteArray(file.length().toInt())

        reader.read(content)

        val catName = String(content)

        toolbarTitle.text = catName

        checkArtsSubCatName(catName)
        checkSciSubCatName(catName)
        checkPrimarySubCatName(catName)
        checkEducServSubCatName(catName)

    }

    private fun checkSciSubCatName(catName:String){
        if (catName.uppercase() == "agriculture".uppercase()) {
            val subDb = "agriculture"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "biology".uppercase()) {
            val subDb = "biology"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "chemistry".uppercase()) {
            val subDb = "chemistry"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Food and Nutrition".uppercase()) {
            val subDb = "foodAndNutrition"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "information technology".uppercase()) {
            val subDb = "informationTechnology"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "mathematics".uppercase()) {
            val subDb = "mathematics"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "physics".uppercase()) {
            val subDb = "physics"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "technical drawing".uppercase()) {
            val subDb = "technicalDrawing"
                    loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkArtsSubCatName(catName:String){

        if (catName.uppercase() == "entrepreneurship".uppercase()) {
            val subDb = "entrepreneurship"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "art".uppercase()) {
            val subDb = "art"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Geography".uppercase()) {
            val subDb = "geography"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "literature".uppercase()) {
            val subDb = "literature"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "commerce".uppercase()) {
            val subDb = "commerce"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "History".uppercase()) {
            val subDb = "history"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "C.R.E".uppercase()) {
            val subDb = "christianReligiousEducation"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "languages".uppercase()) {
            val subDb = "subjects_languages"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "I.R.E".uppercase()) {
            val subDb = "islamicReligiousEducation"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "economics".uppercase()) {
            val subDb = "economics"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPrimarySubCatName(catName:String){
        if (catName.uppercase() == "S.S.T".uppercase()) {
            val subDb = "babyLove_oilsAndBabyLotion"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Science".uppercase()) {
            val subDb = "primary_science"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "English".uppercase()) {
            val subDb = "primary_english"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Literacy 1".uppercase()) {
            val subDb = "primary_literacy"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Read and write".uppercase()) {
            val subDb = "primary_readAndWrite"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "Drawing".uppercase()) {
            val subDb = "primary_drawing"
            loadingSubCatProducts(subDb)
        }
        else if (catName.uppercase() == "math".uppercase()) {
            val subDb = "primary_math"
            loadingSubCatProducts(subDb)
        }
        else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEducServSubCatName(catName:String){
        if (catName.uppercase() == "online tutoring".uppercase()) {
            val subDb = "online_tutoring"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Home tutoring".uppercase()) {
            val subDb = "home_tutoring"
            loadingSubCatProducts(subDb)
        } else if (catName.uppercase() == "Facilitation".uppercase()) {
            val subDb = "facilitations"
            loadingSubCatProducts(subDb)
        }   else {
            // Toast.makeText(context, catName, Toast.LENGTH_SHORT).show()
        }
    }



    override fun onProductItemClick(position: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}