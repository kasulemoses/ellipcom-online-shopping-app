package com.ellipcom.ellipcom.ui.navigationCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentMainCategoryBinding
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.android.material.card.MaterialCardView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class BottomNavigationCategoryFragment : Fragment() {

    private lateinit var bottomNavigationCategoryViewModel: BottomNavigationCategoryViewModel
    private var _binding: FragmentMainCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //views
    private lateinit var cardCategoryHousehold: MaterialCardView
    private lateinit var educationCategoryCardView: MaterialCardView
    private lateinit var foodAndDrinksCategoryCardView: MaterialCardView
    private lateinit var constructionCategoryCardView: MaterialCardView
    private lateinit var healthCareCategoryCardView: MaterialCardView
    private lateinit var servicesCategoryCardView: MaterialCardView

    //category views
    private lateinit var householdCatImg: ImageView
    private lateinit var educationCatImg: ImageView
    private lateinit var foodAndDrinksCatImg: ImageView
    private lateinit var constructionCatImg: ImageView
    private lateinit var healthcareCatImg: ImageView
    private lateinit var servicesCatImg: ImageView

    private lateinit var householdCatTitle: TextView
    private lateinit var educationCatTitle: TextView
    private lateinit var foodAndDrinksCatTitle: TextView
    private lateinit var constructionCatTitle: TextView
    private lateinit var servicesCatTitle: TextView
    private lateinit var healthcareCatTitle: TextView

    //firebase database
    private lateinit var fireDb: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainCategoryBinding.inflate(inflater, container, false)

        bottomNavigationCategoryViewModel =
            ViewModelProvider(this).get(BottomNavigationCategoryViewModel::class.java)

        fireDb = FirebaseFirestore.getInstance()
        initializingViews()

        return binding.root
    }

    private fun initializingViews() {

        householdCatImg = binding.imgHouseholdCat
        educationCatImg = binding.imgEducationCat
        foodAndDrinksCatImg = binding.imgFoodAndDrinksCat
        constructionCatImg = binding.imgConstructionCat
        healthcareCatImg = binding.imgHealthcareCat
        servicesCatImg = binding.imgServicesCat

        householdCatTitle = binding.householdCatTitle
        educationCatTitle = binding.educationCatTitle
        foodAndDrinksCatTitle = binding.foodAndDrinksCatTitle
        constructionCatTitle = binding.constructionCatTitle
        servicesCatTitle = binding.servicesCatTitle
        healthcareCatTitle = binding.healthcareCatTitle


        cardCategoryHousehold = binding.cardCategoryHousehold
        educationCategoryCardView = binding.cardEducation
        foodAndDrinksCategoryCardView = binding.cardFoodAndDrinks
        constructionCategoryCardView = binding.cardConstruction
        healthCareCategoryCardView = binding.cardHealthCare
        servicesCategoryCardView = binding.cardServices

        loadingHouseholdCatImageAndTitleFromdB()
        loadingEducationCatImageAndTitleFromdB()
        loadingConstructionCatImageAndTitleFromdB()
        loadingHealthcareCatImageAndTitleFromdB()
        loadingServicesCatImageAndTitleFromdB()
        loadingFoodAndDrinksCatImageAndTitleFromdB()

        cardCategoryHousehold.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_categories_to_householdSubCategoriesFragment)
        }

        educationCategoryCardView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_categories_to_educationSubCategoryFragment)
        }

        foodAndDrinksCategoryCardView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_categories_to_foodAndDrinksSubCategoriesFragment)
        }

        constructionCategoryCardView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_categories_to_constructionSubCategoriesFragment)
        }

        healthCareCategoryCardView.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_categories_to_healthCareSubCategoriesFragment)
        }

        servicesCategoryCardView.setOnClickListener {

        }


    }

    private fun loadingFoodAndDrinksCatImageAndTitleFromdB() {
        fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_FOOD_AND_DRINKS)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_FOOD_AND_DRINKS_CATEGORY_DATA)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (data in value.documentChanges) {
                        if (data.type == DocumentChange.Type.ADDED) {

                            val catData = data.document.toObject(CategoryModel::class.java)

                            Picasso.get().load(catData.categoryImageUrl).into(foodAndDrinksCatImg)
                            foodAndDrinksCatTitle.text = catData.categoryName


                        }
                    }
                }
            }
    }

    private fun loadingServicesCatImageAndTitleFromdB() {
        fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_SERVICES)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_SERVICES_CATEGORY_DATA)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (data in value.documentChanges) {
                        if (data.type == DocumentChange.Type.ADDED) {

                            val catData = data.document.toObject(CategoryModel::class.java)

                            Picasso.get().load(catData.categoryImageUrl).into(servicesCatImg)
                            servicesCatTitle.text = catData.categoryName

                        }
                    }
                }
            }
    }

    private fun loadingHealthcareCatImageAndTitleFromdB() {
        fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_HEALTH_CARE)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_HEALTH_CARE_CATEGORY_DATA)

            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (data in value.documentChanges) {
                        if (data.type == DocumentChange.Type.ADDED) {

                            val catData = data.document.toObject(CategoryModel::class.java)

                            Picasso.get().load(catData.categoryImageUrl).into(healthcareCatImg)
                            healthcareCatTitle.text = catData.categoryName

                        }
                    }
                }
            }
    }

    private fun loadingConstructionCatImageAndTitleFromdB() {
        fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_CONSTRUCTION)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_CONSTRUCTION_CATEGORY_DATA)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (data in value.documentChanges) {
                        if (data.type == DocumentChange.Type.ADDED) {

                            val catData = data.document.toObject(CategoryModel::class.java)
                            Picasso.get().load(catData.categoryImageUrl).into(constructionCatImg)
                            constructionCatTitle.text = catData.categoryName

                        }
                    }
                }
            }
    }

    private fun loadingEducationCatImageAndTitleFromdB() {
        fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_EDUCATION)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_EDUCATION_CATEGORY_DATA)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (data in value.documentChanges) {
                        if (data.type == DocumentChange.Type.ADDED) {

                            val catData = data.document.toObject(CategoryModel::class.java)

                            Picasso.get().load(catData.categoryImageUrl).into(educationCatImg)
                            educationCatTitle.text = catData.categoryName

                        }
                    }
                }
            }
    }

    private fun loadingHouseholdCatImageAndTitleFromdB() {
        fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_HOUSEHOLD)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_HOUSEHOLD_CATEGORY_DATA)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (data in value.documentChanges) {
                        if (data.type == DocumentChange.Type.ADDED) {

                            val catData = data.document.toObject(CategoryModel::class.java)

                            Picasso.get().load(catData.categoryImageUrl).into(householdCatImg)
                            householdCatTitle.text = catData.categoryName

                        }
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}