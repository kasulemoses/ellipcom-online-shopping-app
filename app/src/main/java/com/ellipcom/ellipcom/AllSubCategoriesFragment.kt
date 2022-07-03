package com.ellipcom.ellipcom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.databinding.FragmentAllSubCategoriesBinding


class AllSubCategoriesFragment : Fragment() {

    private var _binding:FragmentAllSubCategoriesBinding?=null
    private val binding get() = _binding!!

    private lateinit var householdNv:NestedScrollView
    private lateinit var healthCareNv:NestedScrollView
    private lateinit var educationNv:NestedScrollView
    private lateinit var foodAndDrinksNv:NestedScrollView
    private lateinit var constructionNv:NestedScrollView

    /*
    * household sub category recycler views initialising
    * them
    * */
    private lateinit var catElectronicsRv:RecyclerView
    private lateinit var catPhonesAndTabletsRv:RecyclerView
    private lateinit var catHomeAndOfficeRv:RecyclerView
    private lateinit var catComputingRv:RecyclerView
    private lateinit var catFurnitureRv:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllSubCategoriesBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        bindingViews()
        return binding.root
    }

    private fun bindingViews() {

        householdNv = binding.householdCatsNv
        healthCareNv = binding.healthCareCatsNv
        educationNv = binding.educationCatsNv
        foodAndDrinksNv = binding.foodAndDrinksNv
        constructionNv = binding.constructionCatsNv




        binding.householdMainCat.setOnClickListener {
            householdNv.visibility = View.VISIBLE
            healthCareNv.visibility = View.GONE
            educationNv.visibility = View.GONE
            foodAndDrinksNv.visibility = View.GONE
            constructionNv.visibility = View.GONE
        }

        binding.healthCareMainCat.setOnClickListener {
            householdNv.visibility = View.GONE
            healthCareNv.visibility = View.VISIBLE
            educationNv.visibility = View.GONE
            foodAndDrinksNv.visibility = View.GONE
            constructionNv.visibility = View.GONE
        }

        binding.educationMainCat.setOnClickListener {
            householdNv.visibility = View.GONE
            healthCareNv.visibility = View.GONE
            educationNv.visibility = View.VISIBLE
            foodAndDrinksNv.visibility = View.GONE
            constructionNv.visibility = View.GONE
        }

        binding.foodAndDrinksMainCat.setOnClickListener {

            householdNv.visibility = View.GONE
            healthCareNv.visibility = View.GONE
            educationNv.visibility = View.GONE
            foodAndDrinksNv.visibility = View.VISIBLE
            constructionNv.visibility = View.GONE
        }

        binding.constructionMainCat.setOnClickListener {
            householdNv.visibility = View.GONE
            healthCareNv.visibility = View.GONE
            educationNv.visibility = View.GONE
            foodAndDrinksNv.visibility = View.GONE
            constructionNv.visibility = View.VISIBLE
        }
    }


}