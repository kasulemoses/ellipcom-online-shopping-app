package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnCategoryClickListener
import com.ellipcom.ellipcom.Interface.OnElectronicCategoryClickListener
import com.ellipcom.ellipcom.adapter.MainSubCategoryAdapter
import com.ellipcom.ellipcom.databinding.FragmentAllSubCategoriesBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.mainSharedViewModel.TestSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.ui.houshold.*
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class AllSubCategoriesFragment : Fragment(),OnElectronicCategoryClickListener {

    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()
    private val sharedViewModelp: TestSharedViewModel by activityViewModels()

    private var _binding: FragmentAllSubCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var householdNv: NestedScrollView
    private lateinit var healthCareNv: NestedScrollView
    private lateinit var educationNv: NestedScrollView
    private lateinit var foodAndDrinksNv: NestedScrollView
    private lateinit var constructionNv: NestedScrollView

    /*
    * household sub category recycler views initialising
    * them
    * */
    private lateinit var catElectronicsRv: RecyclerView
    private lateinit var catPhonesAndTabletsRv: RecyclerView
    private lateinit var catHomeAndOfficeRv: RecyclerView
    private lateinit var catComputingRv: RecyclerView
    private lateinit var catFurnitureRv: RecyclerView

    /*
   * health care sub category recycler views initialising
   * them
   * */
    private lateinit var catHomeMedicineRv: RecyclerView
    private lateinit var catPersonalCareRv: RecyclerView
    private lateinit var catBabyLoveRv: RecyclerView
    private lateinit var catMedicalServicesRv: RecyclerView
    private lateinit var catSexualAndReproductiveHealthRv: RecyclerView

    /*
   * education sub category recycler views initialising
   * them
   * */
    private lateinit var catScienceRv: RecyclerView
    private lateinit var catArtRv: RecyclerView
    private lateinit var catPrimaryRv: RecyclerView
    private lateinit var catEducationServicesRv: RecyclerView

    /*
 * food and drinks sub category recycler views initialising
 * them
 * */

    private lateinit var catFoodRv: RecyclerView
    private lateinit var catDrinksRv: RecyclerView

    /*
   * construction category recycler views initialising
   * them
   * */
    private lateinit var catSafetyGearRv: RecyclerView
    private lateinit var catBuildingToolsRv: RecyclerView
    private lateinit var catConstructionMaterialsRv: RecyclerView
    private lateinit var catPlumbingMaterialsRv: RecyclerView
    private lateinit var catElectricalMaterialsRv: RecyclerView
    private lateinit var catCarpentryToolsRv: RecyclerView
    private lateinit var catConstructionEquipmentsRv: RecyclerView
    private lateinit var catTechnicalServicesRv: RecyclerView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //health care
    private lateinit var safetyGearSubCategoriesList: ArrayList<CategoryModel>
    private val safetyGearCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            safetyGearSubCategoriesList
        )
    }

    private lateinit var buildingToolsSubCategoriesList: ArrayList<CategoryModel>
    private val buildingToolsCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            buildingToolsSubCategoriesList
        )
    }

    private lateinit var constructionMaterialsSubCategoriesList: ArrayList<CategoryModel>
    private val constructionMaterialsCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            constructionMaterialsSubCategoriesList
        )
    }

    private lateinit var plumbingMaterialsSubCategoriesList: ArrayList<CategoryModel>
    private val plumbingMaterialsCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            plumbingMaterialsSubCategoriesList
        )
    }

    private lateinit var electricalMaterialsSubCategoriesList: ArrayList<CategoryModel>
    private val electricalMaterialsCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            electricalMaterialsSubCategoriesList
        )
    }

    private lateinit var carpentryToolsSubCategoriesList: ArrayList<CategoryModel>
    private val carpentryToolsCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            carpentryToolsSubCategoriesList
        )
    }

    private lateinit var constructionEquipmentsSubCategoriesList: ArrayList<CategoryModel>
    private val constructionEquipmentsCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            constructionEquipmentsSubCategoriesList
        )
    }

    private lateinit var technicalServicesSubCategoriesList: ArrayList<CategoryModel>
    private val technicalServicesCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            technicalServicesSubCategoriesList
        )
    }


    //household
    private lateinit var electronicsSubCategoriesList: ArrayList<CategoryModel>
    private val electCategoryAdapter by lazy {
        ElectronicsSubCategoryAdapter(
            electronicsSubCategoriesList
        ,this)
    }

    private lateinit var phonesAndTabletsSubCategoriesList: ArrayList<CategoryModel>
    private val phonesAndTabletsCategoryAdapter by lazy {
        PhonesAndTabletsSubCategoryAdapter(
            phonesAndTabletsSubCategoriesList
        ,this)
    }

    private lateinit var homeAndOfficeSubCategoriesList: ArrayList<CategoryModel>
    private val homeAndOfficeCategoryAdapter by lazy {
        HomeAndOfficeSubCategoryAdapter(
            homeAndOfficeSubCategoriesList
        )
    }

    private lateinit var computingSubCategoriesList: ArrayList<CategoryModel>
    private val computingCategoryAdapter by lazy {
        ComputingSubCategoryAdapter(
            computingSubCategoriesList
        )
    }

    private lateinit var furnitureSubCategoriesList: ArrayList<CategoryModel>
    private val furnitureCategoryAdapter by lazy {
        FurnitureSubCategoryAdapter(
            furnitureSubCategoriesList
        )
    }

    //health care
    private lateinit var homeMedicineSubCategoriesList: ArrayList<CategoryModel>
    private val homeMedicineCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            homeMedicineSubCategoriesList
        )
    }

    private lateinit var personalCareSubCategoriesList: ArrayList<CategoryModel>
    private val personalCareCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            personalCareSubCategoriesList
        )
    }

    private lateinit var babyLoveSubCategoriesList: ArrayList<CategoryModel>
    private val babyLoveCategoryAdapter by lazy { MainSubCategoryAdapter(babyLoveSubCategoriesList) }

    private lateinit var medicalServicesSubCategoriesList: ArrayList<CategoryModel>
    private val medicalServicesCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            medicalServicesSubCategoriesList
        )
    }

    private lateinit var sexualAndReproductiveHealthSubCategoriesList: ArrayList<CategoryModel>
    private val sexualAndReproductiveHealthCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            sexualAndReproductiveHealthSubCategoriesList
        )
    }

    //education
    private lateinit var scienceSubCategoriesList: ArrayList<CategoryModel>
    private val scienceCategoryAdapter by lazy { MainSubCategoryAdapter(scienceSubCategoriesList) }

    private lateinit var artSubCategoriesList: ArrayList<CategoryModel>
    private val artCategoryAdapter by lazy { MainSubCategoryAdapter(artSubCategoriesList) }

    private lateinit var primarySubCategoriesList: ArrayList<CategoryModel>
    private val primaryCategoryAdapter by lazy { MainSubCategoryAdapter(primarySubCategoriesList) }

    private lateinit var educationServicesSubCategoriesList: ArrayList<CategoryModel>
    private val educationServicesCategoryAdapter by lazy {
        MainSubCategoryAdapter(
            educationServicesSubCategoriesList
        )
    }

    //food and drinks
    private lateinit var foodSubCategoriesList: ArrayList<CategoryModel>
    private val foodCategoryAdapter by lazy { MainSubCategoryAdapter(foodSubCategoriesList) }

    private lateinit var drinksSubCategoriesList: ArrayList<CategoryModel>
    private val drinksCategoryAdapter by lazy { MainSubCategoryAdapter(drinksSubCategoriesList) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllSubCategoriesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        fireDb = FirebaseFirestore.getInstance()
        household()

        binding.subCategoryImageBack.setOnClickListener {
            findNavController().navigate(R.id.action_allSubCategoriesFragment_to_navigation_home)
        }
        attachImagesToMainSubCategory()
        bindingViews()
        return binding.root
    }

    private fun bindingViews() {

        householdNv = binding.householdCatsNv
        healthCareNv = binding.healthCareCatsNv
        educationNv = binding.educationCatsNv
        foodAndDrinksNv = binding.foodAndDrinksNv
        constructionNv = binding.constructionCatsNv

        healthCare()
        educationSubCats()
        restaurantSubCats()
        constructionSubCat()


        /*
    * household sub category recycler views initialising
    * them
    * */
        catElectronicsRv = binding.catElectronicsRv
        catPhonesAndTabletsRv = binding.catPhonesAndTabletsRv
        catHomeAndOfficeRv = binding.catHomeAndOfficeRv
        catComputingRv = binding.catComputingRv
        catFurnitureRv = binding.catFurnitureRv

        val spanCount:Int = 4


        //electronics sub cats
        catElectronicsRv.setHasFixedSize(true)
        catElectronicsRv.layoutManager = GridLayoutManager(context, spanCount)
        electronicsSubCategoriesList = ArrayList()
        catElectronicsRv.adapter = electCategoryAdapter

        //phone and tablets sub cats
        catPhonesAndTabletsRv.setHasFixedSize(true)
        catPhonesAndTabletsRv.layoutManager = GridLayoutManager(context, spanCount)
        phonesAndTabletsSubCategoriesList = ArrayList()
        catPhonesAndTabletsRv.adapter = phonesAndTabletsCategoryAdapter

        //home and office sub cats
        catHomeAndOfficeRv.setHasFixedSize(true)
        catHomeAndOfficeRv.layoutManager = GridLayoutManager(context, spanCount)
        homeAndOfficeSubCategoriesList = ArrayList()
        catHomeAndOfficeRv.adapter = homeAndOfficeCategoryAdapter

        //computing sub cats
        catComputingRv.setHasFixedSize(true)
        catComputingRv.layoutManager = GridLayoutManager(context, spanCount)
        computingSubCategoriesList = ArrayList()
        catComputingRv.adapter = computingCategoryAdapter

        //furniture sub cats
        catFurnitureRv.setHasFixedSize(true)
        catFurnitureRv.layoutManager = GridLayoutManager(context, spanCount)
        furnitureSubCategoriesList = ArrayList()
        catFurnitureRv.adapter = furnitureCategoryAdapter


        /*
   * health care sub category recycler views initialising
   * them
   * */
        catHomeMedicineRv = binding.catHomeMedicineRv
        catPersonalCareRv = binding.catPersonalCareRv
        catBabyLoveRv = binding.catBabyLoveRv
        catSexualAndReproductiveHealthRv = binding.catSexualAndReproductiveHealthRv
        catMedicalServicesRv = binding.catMedicalServicesRv

        //home medicine sub cats
        catHomeMedicineRv.setHasFixedSize(true)
        catHomeMedicineRv.layoutManager = GridLayoutManager(context, spanCount)
        homeMedicineSubCategoriesList = ArrayList()
        catHomeMedicineRv.adapter = homeMedicineCategoryAdapter

        //personal care sub cats
        catPersonalCareRv.setHasFixedSize(true)
        catPersonalCareRv.layoutManager = GridLayoutManager(context, spanCount)
        personalCareSubCategoriesList = ArrayList()
        catPersonalCareRv.adapter = personalCareCategoryAdapter

        //baby love sub cats
        catBabyLoveRv.setHasFixedSize(true)
        catBabyLoveRv.layoutManager = GridLayoutManager(context, spanCount)
        babyLoveSubCategoriesList = ArrayList()
        catBabyLoveRv.adapter = babyLoveCategoryAdapter

        //Sexual  And  Reproductive Health sub cats
        catSexualAndReproductiveHealthRv.setHasFixedSize(true)
        catSexualAndReproductiveHealthRv.layoutManager = GridLayoutManager(context, spanCount)
        sexualAndReproductiveHealthSubCategoriesList = ArrayList()
        catSexualAndReproductiveHealthRv.adapter = sexualAndReproductiveHealthCategoryAdapter

        //Sexual  And  Reproductive Health sub cats
        catMedicalServicesRv.setHasFixedSize(true)
        catMedicalServicesRv.layoutManager = GridLayoutManager(context, spanCount)
        medicalServicesSubCategoriesList = ArrayList()
        catMedicalServicesRv.adapter = medicalServicesCategoryAdapter


        /*
  * education sub category recycler views initialising
  * them
  * */
        catScienceRv = binding.catScienceRv
        catArtRv = binding.catArtRv
        catPrimaryRv = binding.catPrimaryRv
        catEducationServicesRv = binding.catEducationServicesRv

        //science sub cats
        catScienceRv.setHasFixedSize(true)
        catScienceRv.layoutManager = GridLayoutManager(context, spanCount)
        scienceSubCategoriesList = ArrayList()
        catScienceRv.adapter = scienceCategoryAdapter

        //arts sub cats
        catArtRv.setHasFixedSize(true)
        catArtRv.layoutManager = GridLayoutManager(context, spanCount)
        artSubCategoriesList = ArrayList()
        catArtRv.adapter = artCategoryAdapter

        //primary sub cats
        catPrimaryRv.setHasFixedSize(true)
        catPrimaryRv.layoutManager = GridLayoutManager(context, spanCount)
        primarySubCategoriesList = ArrayList()
        catPrimaryRv.adapter = primaryCategoryAdapter

        //arts sub cats
        catEducationServicesRv.setHasFixedSize(true)
        catEducationServicesRv.layoutManager = GridLayoutManager(context, spanCount)
        educationServicesSubCategoriesList = ArrayList()
        catEducationServicesRv.adapter = educationServicesCategoryAdapter

        /*
 * food and drinks sub category recycler views initialising
 * them
 * */

        catFoodRv = binding.catFoodRv
        catDrinksRv = binding.catDrinksRv

        //food sub cats
        catFoodRv.setHasFixedSize(true)
        catFoodRv.layoutManager = GridLayoutManager(context, spanCount)
        foodSubCategoriesList = ArrayList()
        catFoodRv.adapter = foodCategoryAdapter

        //food sub cats
        catDrinksRv.setHasFixedSize(true)
        catDrinksRv.layoutManager = GridLayoutManager(context, spanCount)
        drinksSubCategoriesList = ArrayList()
        catDrinksRv.adapter = foodCategoryAdapter


        /*
  * construction category recycler views initialising
  * them
  * */
        catSafetyGearRv = binding.catSafetyGearRv
        catBuildingToolsRv = binding.catBuildingToolsRv
        catConstructionMaterialsRv = binding.catConstructionMaterialsRv
        catPlumbingMaterialsRv = binding.catPlumbingMaterialsRv
        catElectricalMaterialsRv = binding.catElectricalMaterialsRv
        catCarpentryToolsRv = binding.catCarpentryToolsRv
        catConstructionEquipmentsRv = binding.catConstructionEquipmentsRv
        catTechnicalServicesRv = binding.catTechnicalServicesRv

        //safety gear sub cats
        catSafetyGearRv.setHasFixedSize(true)
        catSafetyGearRv.layoutManager = GridLayoutManager(context, spanCount)
        safetyGearSubCategoriesList = ArrayList()
        catSafetyGearRv.adapter = safetyGearCategoryAdapter

        //building tools sub cats
        catBuildingToolsRv.setHasFixedSize(true)
        catBuildingToolsRv.layoutManager = GridLayoutManager(context, spanCount)
        buildingToolsSubCategoriesList = ArrayList()
        catBuildingToolsRv.adapter = buildingToolsCategoryAdapter

        //const mater sub cats
        catConstructionMaterialsRv.setHasFixedSize(true)
        catConstructionMaterialsRv.layoutManager = GridLayoutManager(context, spanCount)
        constructionMaterialsSubCategoriesList = ArrayList()
        catConstructionMaterialsRv.adapter = constructionMaterialsCategoryAdapter

        //plumb mater sub cats
        catPlumbingMaterialsRv.setHasFixedSize(true)
        catPlumbingMaterialsRv.layoutManager = GridLayoutManager(context, spanCount)
        plumbingMaterialsSubCategoriesList = ArrayList()
        catPlumbingMaterialsRv.adapter = plumbingMaterialsCategoryAdapter

        //electrical material sub cats
        catElectricalMaterialsRv.setHasFixedSize(true)
        catElectricalMaterialsRv.layoutManager = GridLayoutManager(context, spanCount)
        electricalMaterialsSubCategoriesList = ArrayList()
        catElectricalMaterialsRv.adapter = electricalMaterialsCategoryAdapter

        //carpentry tools sub cats
        catCarpentryToolsRv.setHasFixedSize(true)
        catCarpentryToolsRv.layoutManager = GridLayoutManager(context, spanCount)
        carpentryToolsSubCategoriesList = ArrayList()
        catCarpentryToolsRv.adapter = carpentryToolsCategoryAdapter

        //const equip tools sub cats
        catConstructionEquipmentsRv.setHasFixedSize(true)
        catConstructionEquipmentsRv.layoutManager = GridLayoutManager(context, spanCount)
        constructionEquipmentsSubCategoriesList = ArrayList()
        catConstructionEquipmentsRv.adapter = constructionEquipmentsCategoryAdapter

        //technical services tools sub cats
        catTechnicalServicesRv.setHasFixedSize(true)
        catTechnicalServicesRv.layoutManager = GridLayoutManager(context, spanCount)
        technicalServicesSubCategoriesList = ArrayList()
        catTechnicalServicesRv.adapter = technicalServicesCategoryAdapter


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

    private fun attachImagesToMainSubCategory(){

        val householdImage = binding.householdImage
        val householdTv = binding.tvHousehold

        val heathCareImage = binding.healthCareImage
        val healthCareTv = binding.healthCareTv

        val educationImage = binding.educationImage
        val educationTv = binding.educationTv

        val restaurantImage = binding.restaurantImage
        val restaurantTv = binding.restaurantTv

        val constructionImage = binding.constructionImage
        val constructionTv = binding.constructionTv


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

                            Picasso.get().load(catData.categoryImageUrl).into(householdImage)
                            householdTv.text = catData.categoryName

                        }
                    }
                }
            }

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

                            Picasso.get().load(catData.categoryImageUrl).into(heathCareImage)
                            healthCareTv.text = catData.categoryName

                        }
                    }
                }
            }

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

                            Picasso.get().load(catData.categoryImageUrl).into(educationImage)
                            educationTv.text = catData.categoryName

                        }
                    }
                }
            }

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

                            Picasso.get().load(catData.categoryImageUrl).into(restaurantImage)
                            restaurantTv.text = catData.categoryName


                        }
                    }
                }
            }
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
                            Picasso.get().load(catData.categoryImageUrl).into(constructionImage)
                            constructionTv.text = catData.categoryName

                        }
                    }
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun healthCare() {
        /*
       * health care sub cate recycler views attaching them with data
       * */

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_H_MEDIC)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                homeMedicineSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    homeMedicineCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_P_CARE)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                personalCareSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    personalCareCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_B_LOVE)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                babyLoveSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    babyLoveCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_SEX_AND_REPRO)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                sexualAndReproductiveHealthSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    sexualAndReproductiveHealthCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_MED_SERV)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                medicalServicesSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    medicalServicesCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun educationSubCats() {
        /*
        * education sub cate recycler views attaching them with data
        * */

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_SCIENCE)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                scienceSubCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    scienceCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_ARTS)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                artSubCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    artCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_PRIMARY)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                primarySubCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    primaryCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_EDUC_SERV)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                educationServicesSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    educationServicesCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun restaurantSubCats() {

        /*
        * restaurant sub cate recycler views attaching them with data
        * */

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_FOOD)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                foodSubCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    foodCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_DRINKS)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                drinksSubCategoriesList.add(product.document.toObject(CategoryModel::class.java))

                            }
                        }

                    }
                    drinksCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun constructionSubCat() {
        /*
      * construction sub cate recycler views attaching them with data
      * */

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_SAFETY_GEAR)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                safetyGearSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    safetyGearCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_BUILDING_TOOLS)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                buildingToolsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    buildingToolsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_CONST_MATERIAL)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                constructionMaterialsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    constructionMaterialsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_PLUMB_MAT)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                plumbingMaterialsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    plumbingMaterialsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_ELECT_MAT)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                electricalMaterialsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    electricalMaterialsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_CARP_TOOLS)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                carpentryToolsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    carpentryToolsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_CONST_EQUIP)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                constructionEquipmentsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    constructionEquipmentsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_CONST_SERVICES)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                technicalServicesSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    technicalServicesCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun household() {

        /*
        * health care sub cate recycler views attaching them with data
        * */

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_ELECT)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                electronicsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )
                            }
                        }

                    }
                    electCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_PT)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                phonesAndTabletsSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    phonesAndTabletsCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_HO)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                homeAndOfficeSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    homeAndOfficeCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_COMP)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                computingSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    computingCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        try {
            fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_ALL_SUB_CATS_FURN)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                furnitureSubCategoriesList.add(
                                    product.document.toObject(
                                        CategoryModel::class.java
                                    )
                                )

                            }
                        }

                    }
                    furnitureCategoryAdapter.notifyDataSetChanged()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }


    }


    override fun onElectronicCategoryItemClick(position: Int) {
        val catName = electCategoryAdapter.storeSubCatInfo(position)
        sharedViewModel.savingSubPdtDetails(catName)
        findNavController().navigate(R.id.action_allSubCategoriesFragment_to_allPdtFromHouseholdSubCatFragment)
    }

    override fun onPhoneAndTabletsCategoryItemClick(position: Int) {
        val catName = phonesAndTabletsCategoryAdapter.storePhoneAndTabletSubCatInfo(position)
        sharedViewModelp.savingPhoneAndTabletSubPdtDetails(catName)
        findNavController().navigate(R.id.action_allSubCategoriesFragment_to_allPdtFromHouseholdSubCatFragment)
    }
}