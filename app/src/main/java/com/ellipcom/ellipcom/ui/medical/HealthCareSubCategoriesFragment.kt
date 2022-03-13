package com.ellipcom.ellipcom.ui.medical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentHealthCareSubCategoriesBinding
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
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_medicalServicesFragment)
        }
        familyDoctor.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_medicalServicesFragment)
        }
        homeNurses.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_medicalServicesFragment)
        }
        schoolNurses.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_medicalServicesFragment)
        }

    }

    private fun settingSexualAndReproductiveHealth() {
        contraceptives.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_contraceptivesFragment)
        }
        sanitaryPad.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_reproductiveHealthFragment)
        }
        pregnancyTestStrip.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_reproductiveHealthFragment)
        }
        hivSelfTestKits.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_reproductiveHealthFragment)
        }
    }

    private fun settingBabyLove() {
        pampersAndDiapers.setOnClickListener {

            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_pampersAndDiapersFragment)
        }
        huggies.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_pampersAndDiapersFragment)
        }
        babySoap.setOnClickListener {

            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_pampersAndDiapersFragment)
        }
        humpersAndMamaKits.setOnClickListener {

            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_pampersAndDiapersFragment)
        }
        oilsAndBabyLotion.setOnClickListener {

            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_pampersAndDiapersFragment)
        }
    }

    private fun settingPersonalCare() {

        glovesAndMasks.setOnClickListener {

        }
        personalCareCotton.setOnClickListener {

        }
        deodorantsAndPerfume.setOnClickListener {

        }
        creamsAndLotion.setOnClickListener {

        }

    }

    private fun settingHomeMedicines() {

        herbalAndSyrups.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_herbalsAndSyrupsFragment)
        }
        antibiotics.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_antibioticsFragment)
        }
        vitaminAndSupplements.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_vitaminsAndFoodSupplementsFragment)
        }
        homeMedicinePainKillers.setOnClickListener {
            findNavController().navigate(R.id.action_healthCareSubCategoriesFragment_to_painKillersFragment)
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