package com.ellipcom.ellipcom.ui.construction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentConstructionSubCategoriesBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.google.android.material.card.MaterialCardView


class ConstructionSubCategoriesFragment : Fragment() {

    private var _binding: FragmentConstructionSubCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var safetyGearCard: MaterialCardView
    private lateinit var safetyGearContent: MaterialCardView
    private lateinit var safetyGearLinearLayout: LinearLayout

    private lateinit var buildingToolsCard: MaterialCardView
    private lateinit var buildingToolsContent: MaterialCardView
    private lateinit var buildingToolsLinearLayout: LinearLayout

    private lateinit var constructionMaterialCardView: MaterialCardView
    private lateinit var constructionMaterialContent: MaterialCardView
    private lateinit var constructionMaterialLinearLayout: LinearLayout

    private lateinit var plumbingMaterialCardView: MaterialCardView
    private lateinit var plumbingMaterialContent: MaterialCardView
    private lateinit var plumbingMaterialLinearLayout: LinearLayout

    private lateinit var electricalMaterialCardView: MaterialCardView
    private lateinit var electricalMaterialContent: MaterialCardView
    private lateinit var electricalMaterialLinearLayout: LinearLayout

    private lateinit var carpentryToolsCardView: MaterialCardView
    private lateinit var carpentryToolsContent: MaterialCardView
    private lateinit var carpentryToolsLinearLayout: LinearLayout

    private lateinit var constructionEquipmentsCardView: MaterialCardView
    private lateinit var constructionEquipmentsContent: MaterialCardView
    private lateinit var constructionEquipmentsLinearLayout: LinearLayout

    private lateinit var technicalServiceCardView: MaterialCardView
    private lateinit var technicalServiceContent: MaterialCardView
    private lateinit var technicalServiceLinearLayout: LinearLayout

    private lateinit var constructionSubCatLinearBack: LinearLayout

    //safety gear
    private lateinit var safetyGearHelmet: TextView
    private lateinit var safetyGearEyeShield: TextView
    private lateinit var safetyGearReflectorJackets: TextView
    private lateinit var safetyGearBelts: TextView
    private lateinit var safetyGearSafetyBoots: TextView
    private lateinit var safetyGearGamBoots: TextView
    private lateinit var safetyGearOverall: TextView
    private lateinit var safetyGearEarBad: TextView

    //building tools
    private lateinit var buildingToolSpade: TextView
    private lateinit var buildingToolHoes: TextView
    private lateinit var buildingToolTrowels: TextView
    private lateinit var buildingToolWheelBarrows: TextView
    private lateinit var buildingToolBobs: TextView
    private lateinit var buildingToolLevels: TextView
    private lateinit var buildingToolSteelAndWood: TextView
    private lateinit var buildingToolFloats: TextView

    //construction materials
    private lateinit var constructionMaterialCement: TextView
    private lateinit var constructionMaterialLime: TextView
    private lateinit var constructionMaterialBlockAndBricks: TextView
    private lateinit var constructionMaterialWaterProofingMaterials: TextView
    private lateinit var constructionMaterialGlasses: TextView
    private lateinit var constructionMaterialWallCarpets: TextView

    //plumbing materials
    private lateinit var plumbingTiles: TextView
    private lateinit var plumbingSinksAndWaterBasins: TextView
    private lateinit var plumbingWaterHeaters: TextView
    private lateinit var plumbingShowers: TextView
    private lateinit var plumbingJunctionAndBends: TextView
    private lateinit var plumbingDrainagePipesAndConduits: TextView

    //electrical material
    private lateinit var electricalSockets: TextView
    private lateinit var electricalwires: TextView
    private lateinit var electricalBulbAndTubes: TextView
    private lateinit var electricalConduits: TextView

    //carpentry tools
    private lateinit var carpentryGrinder: TextView
    private lateinit var carpentryCuttingBlades: TextView
    private lateinit var carpentryDrillers: TextView

    //technical service
    private lateinit var technicalPlumber: TextView
    private lateinit var technicalElectrician: TextView
    private lateinit var technicalProjectManagers: TextView
    private lateinit var technicalSurveyors: TextView
    private lateinit var technicalCivilEngineer: TextView
    private lateinit var technicalDesignerAndArchitect: TextView

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    private var constructionSubCategory = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstructionSubCategoriesBinding.inflate(inflater, container, false)

        initializationOfViews()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initializationOfViews() {

        //safety gear
        safetyGearHelmet = binding.safetyGearHelmet
        safetyGearEyeShield = binding.safetyGearEyeShield
        safetyGearReflectorJackets = binding.safetyGearReflectorJackets
        safetyGearBelts = binding.safetyGearSafetyBelt
        safetyGearSafetyBoots = binding.safetyGearSafetyBoots
        safetyGearGamBoots = binding.safetyGearGamboots
        safetyGearOverall = binding.safetyGearOverall
        safetyGearEarBad = binding.safetyGearEarBad

        settingListenersForSafetyGear()

        //building tools
        buildingToolSpade = binding.buildingToolsSpades
        buildingToolHoes = binding.buildingToolsHoes
        buildingToolTrowels = binding.buildingToolsTrowels
        buildingToolWheelBarrows = binding.buildingToolsWheelBarrow
        buildingToolBobs = binding.buildingToolsBobs
        buildingToolLevels = binding.buildingToolsLevels
        buildingToolSteelAndWood = binding.buildingToolsSteelAndWoods
        buildingToolFloats = binding.buildingToolsFloats

        settingListenersForBuildingTools()

        //construction materials
        constructionMaterialCement = binding.constructionMaterialCement
        constructionMaterialLime = binding.constructionMaterialLime
        constructionMaterialBlockAndBricks = binding.constructionMaterialBlocksAndBricks
        constructionMaterialWaterProofingMaterials =
            binding.constructionMaterialWaterProofingMaterials
        constructionMaterialWallCarpets = binding.constructionMaterialWallCarpets
        constructionMaterialGlasses = binding.constructionMaterialGlasses

        settingListenersForConstructionMaterials()

        //plumbing materials
        plumbingTiles = binding.plumbingTiles
        plumbingSinksAndWaterBasins = binding.plumbingSinkAndWaterBasin
        plumbingShowers = binding.plumbingShowers
        plumbingJunctionAndBends = binding.plumbingJunctionAndBends
        plumbingDrainagePipesAndConduits = binding.plumbingDrainagePipesAndConduits
        plumbingWaterHeaters = binding.plumbingWaterHeaters
        settingListenersForPlumbingMaterials()

        //electrical materials
        electricalSockets = binding.electricalSockets
        electricalwires = binding.electricalWires
        electricalBulbAndTubes = binding.electricalBulbsAndTubes
        electricalConduits = binding.electricalConduits

        settingListenersForElectricalMaterials()

        //carpentry tools
        carpentryGrinder = binding.carpentryGrinders
        carpentryCuttingBlades = binding.carpentryCuttingBlades
        carpentryDrillers = binding.carpentryDrillers

        settingListenersForCarpentryTools()

        //technical services
        technicalPlumber = binding.technicalPlumbers
        technicalElectrician = binding.technicalElectricians
        technicalProjectManagers = binding.technicalProjectManager
        technicalSurveyors = binding.technicalSurveyor
        technicalCivilEngineer = binding.technicalCivilEngineer
        technicalDesignerAndArchitect = binding.technicalDesignerAndArchitect

        settingListenersForTechnicalServices()

        constructionSubCatLinearBack = binding.constructionSubCatLinearBack

        technicalServiceCardView = binding.technicalServiceCard
        technicalServiceLinearLayout = binding.linearTechnicalService
        technicalServiceContent = binding.cardTechnicalServiceContent

        constructionEquipmentsCardView = binding.constructionEquipmentCard
        constructionEquipmentsContent = binding.constructionEquipmentContent
        constructionEquipmentsLinearLayout = binding.linearConstructionEquipments

        carpentryToolsCardView = binding.carpentryCard
        carpentryToolsContent = binding.carpentryContent
        carpentryToolsLinearLayout = binding.linearCarpentry

        electricalMaterialCardView = binding.electricalMaterialCard
        electricalMaterialContent = binding.electricalContent
        electricalMaterialLinearLayout = binding.linearElectrical

        plumbingMaterialCardView = binding.plumbingMaterialCard
        plumbingMaterialContent = binding.plumbingMaterialContent
        plumbingMaterialLinearLayout = binding.plumbingMaterialLinear

        constructionMaterialCardView = binding.constructionMaterialCard
        constructionMaterialContent = binding.constructionMaterialContent
        constructionMaterialLinearLayout = binding.linearConstructionMaterial

        safetyGearCard = binding.safetyGearCard
        safetyGearContent = binding.safetyGearContent
        safetyGearLinearLayout = binding.linearSafetyGear

        buildingToolsCard = binding.buildingToolCard
        buildingToolsContent = binding.buildingToolContent
        buildingToolsLinearLayout = binding.linearBuildingTool

        settingUpListenersToViews()

    }

    private fun storeConstructionSubCatIdToSharedViewmodel(consViewId: String) {
        sharedViewModel.savingConstructionViewId(consViewId)
        findNavController().navigate(R.id.action_constructionSubCategoriesFragment_to_mainConstructionRecyclerviewFragment)
    }

    private fun settingListenersForTechnicalServices() {

        technicalPlumber.setOnClickListener {
            constructionSubCategory = "technicalPlumber"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        technicalElectrician.setOnClickListener {
            constructionSubCategory = "technicalElectrician"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        technicalProjectManagers.setOnClickListener {
            constructionSubCategory = "technicalProjectManagers"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        technicalSurveyors.setOnClickListener {
            constructionSubCategory = "technicalSurveyors"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        technicalCivilEngineer.setOnClickListener {
            constructionSubCategory = "technicalCivilEngineer"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        technicalDesignerAndArchitect.setOnClickListener {
            constructionSubCategory = "technicalDesignerAndArchitect"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }

    private fun settingListenersForCarpentryTools() {

        carpentryGrinder.setOnClickListener {
            constructionSubCategory = "carpentryGrinder"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        carpentryCuttingBlades.setOnClickListener {
            constructionSubCategory = "carpentryCuttingBlades"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        carpentryDrillers.setOnClickListener {
            constructionSubCategory = "carpentryDrillers"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }

    private fun settingListenersForElectricalMaterials() {

        electricalSockets.setOnClickListener {
            constructionSubCategory = "electricalSockets"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        electricalwires.setOnClickListener {
            constructionSubCategory = "electricalWires"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        electricalBulbAndTubes.setOnClickListener {
            constructionSubCategory = "electricalBulbAndTubes"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        electricalConduits.setOnClickListener {
            constructionSubCategory = "electricalConduits"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }

    private fun settingListenersForPlumbingMaterials() {

        plumbingTiles.setOnClickListener {
            constructionSubCategory = "plumbingTiles"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        plumbingSinksAndWaterBasins.setOnClickListener {
            constructionSubCategory = "plumbingSinksAndWaterBasins"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        plumbingShowers.setOnClickListener {
            constructionSubCategory = "plumbingShowers"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        plumbingJunctionAndBends.setOnClickListener {
            constructionSubCategory = "plumbingJunctionAndBends"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        plumbingDrainagePipesAndConduits.setOnClickListener {
            constructionSubCategory = "plumbingDrainagePipesAndConduits"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

        plumbingWaterHeaters.setOnClickListener {
            constructionSubCategory = "plumbingWaterHeaters"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }

    private fun settingListenersForConstructionMaterials() {

        constructionMaterialCement.setOnClickListener {
            constructionSubCategory = "constructionMaterialCement"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        constructionMaterialLime.setOnClickListener {
            constructionSubCategory = "constructionMaterialLime"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        constructionMaterialBlockAndBricks.setOnClickListener {
            constructionSubCategory = "constructionMaterialBlockAndBricks"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        constructionMaterialWaterProofingMaterials.setOnClickListener {
            constructionSubCategory = "constructionMaterialWaterProofingMaterials"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        constructionMaterialWallCarpets.setOnClickListener {
            constructionSubCategory = "constructionMaterialWallCarpets"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        constructionMaterialGlasses.setOnClickListener {
            constructionSubCategory = "constructionMaterialGlasses"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }

    private fun settingListenersForBuildingTools() {

        buildingToolSpade.setOnClickListener {
            constructionSubCategory = "buildingToolSpade"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolHoes.setOnClickListener {
            constructionSubCategory = "buildingToolHoes"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolTrowels.setOnClickListener {
            constructionSubCategory = "buildingToolTrowels"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolWheelBarrows.setOnClickListener {
            constructionSubCategory = "buildingToolWheelBarrows"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolBobs.setOnClickListener {
            constructionSubCategory = "buildingToolBobs"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolLevels.setOnClickListener {
            constructionSubCategory = "buildingToolLevels"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolSteelAndWood.setOnClickListener {
            constructionSubCategory = "buildingToolSteelAndWood"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        buildingToolFloats.setOnClickListener {
            constructionSubCategory = "buildingToolFloats"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }

    private fun settingListenersForSafetyGear() {

        safetyGearHelmet.setOnClickListener {
            constructionSubCategory = "safetyGearHelmet"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)

        }
        safetyGearEyeShield.setOnClickListener {
            constructionSubCategory = "safetyGearEyeShield"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        safetyGearReflectorJackets.setOnClickListener {
            constructionSubCategory = "safetyGearReflectorJackets"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        safetyGearBelts.setOnClickListener {
            constructionSubCategory = "safetyGearBelts"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        safetyGearSafetyBoots.setOnClickListener {
            constructionSubCategory = "safetyGearSafetyBoots"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        safetyGearGamBoots.setOnClickListener {
            constructionSubCategory = "safetyGearGamBoots"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        safetyGearOverall.setOnClickListener {
            constructionSubCategory = "safetyGearOverall"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }
        safetyGearEarBad.setOnClickListener {
            constructionSubCategory = "safetyGearEarBad"
            storeConstructionSubCatIdToSharedViewmodel(constructionSubCategory)
        }

    }


    private fun settingUpListenersToViews() {

        constructionSubCatLinearBack.setOnClickListener {
            findNavController().navigate(R.id.action_constructionSubCategoriesFragment_to_navigation_categories)
        }

        //safety gears
        safetyGearCard.setOnClickListener {
            safetyGearCard.visibility = View.GONE
            safetyGearContent.visibility = View.VISIBLE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE

        }
        safetyGearLinearLayout.setOnClickListener {
            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE


        }

        //building tools
        buildingToolsCard.setOnClickListener {
            buildingToolsCard.visibility = View.GONE
            buildingToolsContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE


        }
        buildingToolsLinearLayout.setOnClickListener {
            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE
        }

        //construction materials
        constructionMaterialCardView.setOnClickListener {
            constructionMaterialCardView.visibility = View.GONE
            constructionMaterialContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE

        }

        constructionMaterialLinearLayout.setOnClickListener {
            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE
        }

        //plumbing material
        plumbingMaterialCardView.setOnClickListener {
            plumbingMaterialCardView.visibility = View.GONE
            plumbingMaterialContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE

        }

        plumbingMaterialLinearLayout.setOnClickListener {
            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE
        }

        //electrical materials
        electricalMaterialCardView.setOnClickListener {
            electricalMaterialCardView.visibility = View.GONE
            electricalMaterialContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE

        }
        electricalMaterialLinearLayout.setOnClickListener {
            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE
        }

        //carpentry tools
        carpentryToolsCardView.setOnClickListener {
            carpentryToolsCardView.visibility = View.GONE
            carpentryToolsContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE

        }
        carpentryToolsLinearLayout.setOnClickListener {
            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE
        }

        //construction equipments
        constructionEquipmentsCardView.setOnClickListener {
            constructionEquipmentsCardView.visibility = View.GONE
            constructionEquipmentsContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE
        }
        constructionEquipmentsLinearLayout.setOnClickListener {
            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE
        }

        //technical service
        technicalServiceCardView.setOnClickListener {
            technicalServiceCardView.visibility = View.GONE
            technicalServiceContent.visibility = View.VISIBLE

            safetyGearCard.visibility = View.VISIBLE
            safetyGearContent.visibility = View.GONE

            buildingToolsCard.visibility = View.VISIBLE
            buildingToolsContent.visibility = View.GONE

            constructionMaterialCardView.visibility = View.VISIBLE
            constructionMaterialContent.visibility = View.GONE

            plumbingMaterialCardView.visibility = View.VISIBLE
            plumbingMaterialContent.visibility = View.GONE

            electricalMaterialCardView.visibility = View.VISIBLE
            electricalMaterialContent.visibility = View.GONE

            carpentryToolsCardView.visibility = View.VISIBLE
            carpentryToolsContent.visibility = View.GONE

            constructionEquipmentsCardView.visibility = View.VISIBLE
            constructionEquipmentsContent.visibility = View.GONE

        }
        technicalServiceLinearLayout.setOnClickListener {
            technicalServiceCardView.visibility = View.VISIBLE
            technicalServiceContent.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}