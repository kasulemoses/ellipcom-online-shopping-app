package com.ellipcom.ellipcom.ui.medical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentHealthCareSubCategoriesBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.google.android.material.card.MaterialCardView

class HealthCareSubCategoriesFragment : Fragment() {

    //view binding
    private var _binding: FragmentHealthCareSubCategoriesBinding? = null
    private val binding get() = _binding!!

    //views
    private lateinit var homeMedicineCardView: MaterialCardView
    private lateinit var homeMedicineContent: MaterialCardView
    private lateinit var homeMedicineLinearLayout: LinearLayout

    private lateinit var personalCareCardView: MaterialCardView
    private lateinit var personalCareContent: MaterialCardView
    private lateinit var personalCareLinearLayout: LinearLayout

    private lateinit var babyLoveCardView: MaterialCardView
    private lateinit var babyLoveContent: MaterialCardView
    private lateinit var babyLoveLinearLayout: LinearLayout

    private lateinit var sexualAndReproductiveHealthCardView: MaterialCardView
    private lateinit var sexualAndReproductiveHealthContent: MaterialCardView
    private lateinit var sexualAndReproductiveHealthLinearLayout: LinearLayout

    private lateinit var medicalServicesCardView: MaterialCardView
    private lateinit var medicalServicesContent: MaterialCardView
    private lateinit var medicalServicesLinearLayout: LinearLayout

    private lateinit var healthCareSubCatLinearBack: LinearLayout

    //home medicine
    private lateinit var herbalAndSyrups: TextView
    private lateinit var antibiotics: TextView
    private lateinit var vitaminAndSupplements: TextView
    private lateinit var homeMedicinePainKillers: TextView

    //personal care
    private lateinit var glovesAndMasks: TextView
    private lateinit var personalCareCotton: TextView
    private lateinit var deodorantsAndPerfume: TextView
    private lateinit var creamsAndLotion: TextView

    //baby love
    private lateinit var pampersAndDiapers: TextView
    private lateinit var huggies: TextView
    private lateinit var babySoap: TextView
    private lateinit var humpersAndMamaKits: TextView
    private lateinit var oilsAndBabyLotion: TextView

    //sexual and reproductive health
    private lateinit var contraceptives: TextView
    private lateinit var sanitaryPad: TextView
    private lateinit var pregnancyTestStrip: TextView
    private lateinit var hivSelfTestKits: TextView

    //medical services
    private lateinit var personalDoctor: TextView
    private lateinit var familyDoctor: TextView
    private lateinit var homeNurses: TextView
    private lateinit var schoolNurses: TextView

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    private var medicalSubCat = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHealthCareSubCategoriesBinding.inflate(inflater, container, false)





        initViews()
        // Inflate the layout for this fragment
        return binding.root
    }


    private fun initViews() {

        //home medicine
        herbalAndSyrups = binding.herbalAndSyrups
        antibiotics = binding.antibiotics
        vitaminAndSupplements = binding.vitaminAndFoodSuppliments
        homeMedicinePainKillers = binding.painKillers

        settingHomeMedicines()

        //personal care
        glovesAndMasks = binding.glovesAndMasks
        personalCareCotton = binding.personalCareCotton
        deodorantsAndPerfume = binding.deodorantsAndPerfumes
        creamsAndLotion = binding.creamsAndLotions

        settingPersonalCare()

        //baby love
        pampersAndDiapers = binding.pampersAndDiapers
        huggies = binding.huggies
        babySoap = binding.babySoap
        humpersAndMamaKits = binding.humpersAndMamaKits
        oilsAndBabyLotion = binding.oilsAndBabyLotion
        settingBabyLove()

        //sexual and reproductive health]
        contraceptives = binding.contraceptives
        sanitaryPad = binding.sanitaryPads
        pregnancyTestStrip = binding.pregnancyTestStrips
        hivSelfTestKits = binding.hivSelfTestKits
        settingSexualAndReproductiveHealth()

        //medical services
        personalDoctor = binding.personalDoctor
        familyDoctor = binding.familyDoctor
        homeNurses = binding.homeNurses
        schoolNurses = binding.schoolNurses
        settingMedicalServices()

        healthCareSubCatLinearBack = binding.healthCareSubCatLinearBack

        homeMedicineCardView = binding.homeMedicineCard
        homeMedicineContent = binding.homeMedicineContent
        homeMedicineLinearLayout = binding.linearHomeMedicine

        personalCareCardView = binding.personalCareCard
        personalCareContent = binding.personalCareContent
        personalCareLinearLayout = binding.linearPersonalCare

        babyLoveCardView = binding.babyLoveCard
        babyLoveContent = binding.babyLoveContent
        babyLoveLinearLayout = binding.linearBabyLove

        sexualAndReproductiveHealthCardView = binding.sexualAndReproductiveHealthCard
        sexualAndReproductiveHealthContent = binding.sexualAndReproductiveHealthContent
        sexualAndReproductiveHealthLinearLayout = binding.sexualAndReproductiveHealthLinear

        medicalServicesCardView = binding.medicineServicesCard
        medicalServicesContent = binding.medicalServicesContent
        medicalServicesLinearLayout = binding.linearMedicalServices


        settingActionsToViews()
    }

    private fun settingMedicalServices() {

        personalDoctor.setOnClickListener {
            medicalSubCat = "personalDoctor"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        familyDoctor.setOnClickListener {
            medicalSubCat = "familyDoctor"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        homeNurses.setOnClickListener {
            medicalSubCat = "homeNurses"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        schoolNurses.setOnClickListener {
            medicalSubCat = "schoolNurses"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }

    }

    private fun settingSexualAndReproductiveHealth() {
        contraceptives.setOnClickListener {
            medicalSubCat = "sexualAndReproductiveHealth_contraceptives"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        sanitaryPad.setOnClickListener {
            medicalSubCat = "sexualAndReproductiveHealth_sanitaryPad"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        pregnancyTestStrip.setOnClickListener {
            medicalSubCat = "sexualAndReproductiveHealth_pregnancyTestStrip"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        hivSelfTestKits.setOnClickListener {
            medicalSubCat = "sexualAndReproductiveHealth_hivSelfTestKits"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
    }

    private fun storingMedicalSubCatToSharedViewModel(medicalSubCat: String) {
        sharedViewModel.savingMedicalSubCatViewId(medicalSubCat)
        findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_mainMedicalRecyclerViewForSubCatFragment)
    }

    private fun settingBabyLove() {
        pampersAndDiapers.setOnClickListener {
            medicalSubCat = "babyLove_pampersAndDiapers"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)

        }
        huggies.setOnClickListener {
            medicalSubCat = "babyLove_huggies"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)

        }
        babySoap.setOnClickListener {
            medicalSubCat = "babyLove_babySoap"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)


        }
        humpersAndMamaKits.setOnClickListener {
            medicalSubCat = "babyLove_humpersAndMamaKits"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)

        }
        oilsAndBabyLotion.setOnClickListener {

            medicalSubCat = "babyLove_oilsAndBabyLotion"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)

        }
    }

    private fun settingPersonalCare() {

        glovesAndMasks.setOnClickListener {
            medicalSubCat = "personalCare_glovesAndMasks"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        personalCareCotton.setOnClickListener {
            medicalSubCat = "personalCare_Cotton"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        deodorantsAndPerfume.setOnClickListener {
            medicalSubCat = "personalCare_deodorantsAndPerfume"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        creamsAndLotion.setOnClickListener {
            medicalSubCat = "personalCare_creamsAndLotion"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }

    }

    private fun settingHomeMedicines() {

        herbalAndSyrups.setOnClickListener {
            medicalSubCat = "homeMedicine_herbalAndSyrups"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        antibiotics.setOnClickListener {
            medicalSubCat = "homeMedicine_antibiotics"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        vitaminAndSupplements.setOnClickListener {
            medicalSubCat = "homeMedicine_vitaminAndSupplements"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }
        homeMedicinePainKillers.setOnClickListener {
            medicalSubCat = "homeMedicine_homeMedicinePainKillers"
            storingMedicalSubCatToSharedViewModel(medicalSubCat)
        }

    }

    private fun settingActionsToViews() {

        healthCareSubCatLinearBack.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_navigation_categories)
        }

        //home medicine
        homeMedicineCardView.setOnClickListener {
            homeMedicineCardView.visibility = View.GONE
            homeMedicineContent.visibility = View.VISIBLE

            personalCareCardView.visibility = View.VISIBLE
            personalCareContent.visibility = View.GONE

            babyLoveCardView.visibility = View.VISIBLE
            babyLoveContent.visibility = View.GONE

            sexualAndReproductiveHealthCardView.visibility = View.VISIBLE
            sexualAndReproductiveHealthContent.visibility = View.GONE

            medicalServicesCardView.visibility = View.VISIBLE
            medicalServicesContent.visibility = View.GONE

        }

        homeMedicineLinearLayout.setOnClickListener {
            homeMedicineCardView.visibility = View.VISIBLE
            homeMedicineContent.visibility = View.GONE
        }

        //personal care
        personalCareCardView.setOnClickListener {
            personalCareCardView.visibility = View.GONE
            personalCareContent.visibility = View.VISIBLE

            homeMedicineCardView.visibility = View.VISIBLE
            homeMedicineContent.visibility = View.GONE

            babyLoveCardView.visibility = View.VISIBLE
            babyLoveContent.visibility = View.GONE

            sexualAndReproductiveHealthCardView.visibility = View.VISIBLE
            sexualAndReproductiveHealthContent.visibility = View.GONE

            medicalServicesCardView.visibility = View.VISIBLE
            medicalServicesContent.visibility = View.GONE

        }

        personalCareLinearLayout.setOnClickListener {
            personalCareCardView.visibility = View.VISIBLE
            personalCareContent.visibility = View.GONE
        }

        //baby love
        babyLoveCardView.setOnClickListener {
            babyLoveCardView.visibility = View.GONE
            babyLoveContent.visibility = View.VISIBLE

            homeMedicineCardView.visibility = View.VISIBLE
            homeMedicineContent.visibility = View.GONE

            personalCareCardView.visibility = View.VISIBLE
            personalCareContent.visibility = View.GONE

            sexualAndReproductiveHealthCardView.visibility = View.VISIBLE
            sexualAndReproductiveHealthContent.visibility = View.GONE

            medicalServicesCardView.visibility = View.VISIBLE
            medicalServicesContent.visibility = View.GONE

        }

        babyLoveLinearLayout.setOnClickListener {
            babyLoveCardView.visibility = View.VISIBLE
            babyLoveContent.visibility = View.GONE
        }

        //sexual and reproductive health
        sexualAndReproductiveHealthCardView.setOnClickListener {
            sexualAndReproductiveHealthCardView.visibility = View.GONE
            sexualAndReproductiveHealthContent.visibility = View.VISIBLE

            homeMedicineCardView.visibility = View.VISIBLE
            homeMedicineContent.visibility = View.GONE

            personalCareCardView.visibility = View.VISIBLE
            personalCareContent.visibility = View.GONE

            babyLoveCardView.visibility = View.VISIBLE
            babyLoveContent.visibility = View.GONE

            medicalServicesCardView.visibility = View.VISIBLE
            medicalServicesContent.visibility = View.GONE


        }

        sexualAndReproductiveHealthLinearLayout.setOnClickListener {
            sexualAndReproductiveHealthCardView.visibility = View.VISIBLE
            sexualAndReproductiveHealthContent.visibility = View.GONE
        }

        //medical services
        medicalServicesCardView.setOnClickListener {
            medicalServicesCardView.visibility = View.GONE
            medicalServicesContent.visibility = View.VISIBLE

            homeMedicineCardView.visibility = View.VISIBLE
            homeMedicineContent.visibility = View.GONE

            personalCareCardView.visibility = View.VISIBLE
            personalCareContent.visibility = View.GONE

            babyLoveCardView.visibility = View.VISIBLE
            babyLoveContent.visibility = View.GONE

            sexualAndReproductiveHealthCardView.visibility = View.VISIBLE
            sexualAndReproductiveHealthContent.visibility = View.GONE
        }

        medicalServicesLinearLayout.setOnClickListener {
            medicalServicesCardView.visibility = View.VISIBLE
            medicalServicesContent.visibility = View.GONE
        }
    }


}