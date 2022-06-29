package com.ellipcom.ellipcom.ui.education

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
import com.ellipcom.ellipcom.databinding.FragmentEducationSubCategoryBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.google.android.material.card.MaterialCardView

class EducationSubCategoryFragment : Fragment() {

    //view binding
    private var _binding: FragmentEducationSubCategoryBinding? = null
    private val binding get() = _binding!!

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    //views
    private lateinit var cardScience: MaterialCardView
    private lateinit var cardScienceContent: MaterialCardView
    private lateinit var linearScience: LinearLayout

    private lateinit var cardArts: MaterialCardView
    private lateinit var cardArtsContent: MaterialCardView
    private lateinit var linearArts: LinearLayout

    private lateinit var cardPrimary: MaterialCardView
    private lateinit var cardPrimaryContent: MaterialCardView
    private lateinit var linearPrimary: LinearLayout

    private lateinit var cardService: MaterialCardView
    private lateinit var cardServiceContent: MaterialCardView
    private lateinit var linearService: LinearLayout

    private lateinit var educationSubCatLinearBack: LinearLayout


    //Science views
    private lateinit var physics: TextView
    private lateinit var chemistry: TextView
    private lateinit var mathematics: TextView
    private lateinit var biology: TextView
    private lateinit var agriculture: TextView
    private lateinit var technicalDrawing: TextView
    private lateinit var foodAndNutrition: TextView
    private lateinit var informationTechnology: TextView

    //art view
    private lateinit var history: TextView
    private lateinit var geography: TextView
    private lateinit var art: TextView
    private lateinit var economics: TextView
    private lateinit var entrepreneurship: TextView
    private lateinit var commerce: TextView
    private lateinit var CRE: TextView
    private lateinit var IRE: TextView
    private lateinit var languages: TextView
    private lateinit var literature: TextView

    //primary views
    private lateinit var primarySST: TextView
    private lateinit var primaryScience: TextView
    private lateinit var primaryMath: TextView
    private lateinit var primaryEnglish: TextView
    private lateinit var primaryLiteracy: TextView
    private lateinit var primaryReadAndWrite: TextView
    private lateinit var primaryDrawing: TextView

    // education services
    private lateinit var onlineTutoring: TextView
    private lateinit var homeTutoring: TextView
    private lateinit var facilitation: TextView

    private var educationViewId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEducationSubCategoryBinding.inflate(inflater, container, false)


        initializingViews()

        return binding.root
    }

    private fun initializingViews() {

        //science
        physics = binding.sciencePhysics
        chemistry = binding.scienceChemistry
        mathematics = binding.scienceMathematics
        biology = binding.scienceBiology
        agriculture = binding.scienceAgriculture
        technicalDrawing = binding.scienceTechnicalDrawing
        foodAndNutrition = binding.scienceFoodAndNutrition
        informationTechnology = binding.scienceIT

        clickListenerForScience()

        //arts
        history = binding.artsHistory
        geography = binding.artsGeography
        art = binding.artsArt
        economics = binding.artsEconomics
        entrepreneurship = binding.artsEntrepreneurship
        commerce = binding.artsCommerce
        CRE = binding.artsCre
        IRE = binding.artsIre
        languages = binding.artsLanguages
        literature = binding.artsLiterature

        clickListenersForArts()

        //primary
        primarySST = binding.primarySst
        primaryScience = binding.primaryScience
        primaryMath = binding.primaryMath
        primaryLiteracy = binding.primaryLiteracy
        primaryReadAndWrite = binding.primaryReadAndWrite
        primaryDrawing = binding.primaryDrawing
        primaryEnglish = binding.primaryEnglish

        clickListenersForPrimary()

        //education services
        onlineTutoring = binding.onlineTutoring
        homeTutoring = binding.homeTutoring
        facilitation = binding.facilitation

        clickListenersForEducationServices()

        educationSubCatLinearBack = binding.educationSubCatLinearBack

        cardScience = binding.cardEducation
        cardScienceContent = binding.scienceCardContent
        linearScience = binding.linearScience

        cardArts = binding.cardArts
        cardArtsContent = binding.cardArtsContent
        linearArts = binding.linearArts

        cardPrimary = binding.cardPrimary
        cardPrimaryContent = binding.cardPrimaryContent
        linearPrimary = binding.linearPrimary

        cardService = binding.cardEducationServices
        cardServiceContent = binding.cardServiceContent
        linearService = binding.linearService


        educationSubCatLinearBack.setOnClickListener {

        }
        //science
        cardScience.setOnClickListener {
            cardScienceContent.visibility = View.VISIBLE
            cardScience.visibility = View.GONE

            cardArts.visibility = View.VISIBLE
            cardArtsContent.visibility = View.GONE

            cardPrimary.visibility = View.VISIBLE
            cardPrimaryContent.visibility = View.GONE

            cardService.visibility = View.VISIBLE
            cardServiceContent.visibility = View.GONE
        }

        linearScience.setOnClickListener {
            cardScienceContent.visibility = View.GONE
            cardScience.visibility = View.VISIBLE
        }

        //arts
        cardArts.setOnClickListener {
            cardArts.visibility = View.GONE
            cardArtsContent.visibility = View.VISIBLE


            cardScienceContent.visibility = View.GONE
            cardScience.visibility = View.VISIBLE

            cardPrimary.visibility = View.VISIBLE
            cardPrimaryContent.visibility = View.GONE

            cardService.visibility = View.VISIBLE
            cardServiceContent.visibility = View.GONE

        }
        linearArts.setOnClickListener {
            cardArts.visibility = View.VISIBLE
            cardArtsContent.visibility = View.GONE
        }

        //primary
        cardPrimary.setOnClickListener {
            cardPrimary.visibility = View.GONE
            cardPrimaryContent.visibility = View.VISIBLE

            cardArts.visibility = View.VISIBLE
            cardArtsContent.visibility = View.GONE

            cardScienceContent.visibility = View.GONE
            cardScience.visibility = View.VISIBLE

            cardService.visibility = View.VISIBLE
            cardServiceContent.visibility = View.GONE
        }

        linearPrimary.setOnClickListener {
            cardPrimary.visibility = View.VISIBLE
            cardPrimaryContent.visibility = View.GONE

        }

        //services
        cardService.setOnClickListener {
            cardService.visibility = View.GONE
            cardServiceContent.visibility = View.VISIBLE

            cardArts.visibility = View.VISIBLE
            cardArtsContent.visibility = View.GONE

            cardScienceContent.visibility = View.GONE
            cardScience.visibility = View.VISIBLE

            cardPrimary.visibility = View.VISIBLE
            cardPrimaryContent.visibility = View.GONE
        }

        linearService.setOnClickListener {
            cardService.visibility = View.VISIBLE
            cardServiceContent.visibility = View.GONE

        }
    }

    private fun clickListenersForEducationServices() {

        onlineTutoring.setOnClickListener {
            educationViewId = "onlineTutoring"
            storingEducationSubcategoryViewId(educationViewId)
        }
        homeTutoring.setOnClickListener {
            educationViewId = "homeTutoring"
            storingEducationSubcategoryViewId(educationViewId)
        }
        facilitation.setOnClickListener {
            educationViewId = "facilitation"
            storingEducationSubcategoryViewId(educationViewId)
        }
    }

    private fun clickListenersForPrimary() {
        primarySST.setOnClickListener {
            educationViewId = "primary_sst"
            storingEducationSubcategoryViewId(educationViewId)
        }
        primaryScience.setOnClickListener {
            educationViewId = "primary_science"
            storingEducationSubcategoryViewId(educationViewId)
        }
        primaryMath.setOnClickListener {
            educationViewId = "primary_math"
            storingEducationSubcategoryViewId(educationViewId)
        }
        primaryLiteracy.setOnClickListener {
            educationViewId = "primary_literacy"
            storingEducationSubcategoryViewId(educationViewId)
        }
        primaryReadAndWrite.setOnClickListener {
            educationViewId = "primary_readAndWrite"
            storingEducationSubcategoryViewId(educationViewId)
        }
        primaryDrawing.setOnClickListener {
            educationViewId = "primary_drawing"
            storingEducationSubcategoryViewId(educationViewId)
        }
        primaryEnglish.setOnClickListener {
            educationViewId = "primary_english"
            storingEducationSubcategoryViewId(educationViewId)
        }
    }

    private fun storingEducationSubcategoryViewId(educationViewId: String) {
        sharedViewModel.savingEducationViewId(educationViewId)
        //findNavController().navigate(R.id.action_educationSubCategoryFragment_to_mainEducationRecyclerviewFragment)
    }

    private fun clickListenersForArts() {
        history.setOnClickListener {
            educationViewId = "history"
            storingEducationSubcategoryViewId(educationViewId)
        }
        geography.setOnClickListener {
            educationViewId = "geography"
            storingEducationSubcategoryViewId(educationViewId)
        }
        art.setOnClickListener {
            educationViewId = "art"
            storingEducationSubcategoryViewId(educationViewId)
        }
        economics.setOnClickListener {
            educationViewId = "economics"
            storingEducationSubcategoryViewId(educationViewId)
        }
        entrepreneurship.setOnClickListener {
            educationViewId = "entrepreneurship"
            storingEducationSubcategoryViewId(educationViewId)
        }
        commerce.setOnClickListener {
            educationViewId = "commerce"
            storingEducationSubcategoryViewId(educationViewId)

        }
        CRE.setOnClickListener {
            educationViewId = "christianReligiousEducation"
            storingEducationSubcategoryViewId(educationViewId)
        }
        IRE.setOnClickListener {
            educationViewId = "islamicReligiousEducation"
            storingEducationSubcategoryViewId(educationViewId)
        }
        languages.setOnClickListener {
            educationViewId = "subjects_languages"
            storingEducationSubcategoryViewId(educationViewId)
        }
        literature.setOnClickListener {
            educationViewId = "literature"
            storingEducationSubcategoryViewId(educationViewId)
        }
    }

    private fun clickListenerForScience() {
        physics.setOnClickListener {
            educationViewId = "physics"
            storingEducationSubcategoryViewId(educationViewId)
        }
        chemistry.setOnClickListener {
            educationViewId = "chemistry"
            storingEducationSubcategoryViewId(educationViewId)
        }
        mathematics.setOnClickListener {
            educationViewId = "mathematics"
            storingEducationSubcategoryViewId(educationViewId)
        }
        biology.setOnClickListener {
            educationViewId = "biology"
            storingEducationSubcategoryViewId(educationViewId)
        }
        agriculture.setOnClickListener {
            educationViewId = "agriculture"
            storingEducationSubcategoryViewId(educationViewId)
        }
        technicalDrawing.setOnClickListener {
            educationViewId = "technicalDrawing"
            storingEducationSubcategoryViewId(educationViewId)

        }
        foodAndNutrition.setOnClickListener {
            educationViewId = "foodAndNutrition"
            storingEducationSubcategoryViewId(educationViewId)

        }
        informationTechnology.setOnClickListener {
            educationViewId = "informationTechnology"
            storingEducationSubcategoryViewId(educationViewId)
        }
    }


}