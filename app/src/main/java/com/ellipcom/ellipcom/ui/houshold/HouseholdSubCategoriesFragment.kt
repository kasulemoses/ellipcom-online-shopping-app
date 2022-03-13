package com.ellipcom.ellipcom.ui.houshold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentHouseholdSubCategoriesBinding
import com.google.android.material.card.MaterialCardView


class HouseholdSubCategoriesFragment : Fragment() {

    private var _binding: FragmentHouseholdSubCategoriesBinding? = null
    private val binding get() = _binding!!

    //views
    private lateinit var electronicsCard1: MaterialCardView
    private lateinit var electronicsCardContent: MaterialCardView
    private lateinit var phoneAndTabletCard1: MaterialCardView
    private lateinit var phoneAndTabletCardContent: MaterialCardView
    private lateinit var electronicsLinear1: LinearLayout
    private lateinit var phoneAndTabletLinear1: LinearLayout

    private lateinit var cardComputing1: MaterialCardView
    private lateinit var cardComputingContent: MaterialCardView
    private lateinit var linearComputing1: LinearLayout

    private lateinit var cardHomeAndOffice1: MaterialCardView
    private lateinit var cardHomeAndOfficeContent: MaterialCardView
    private lateinit var linearHomeAndOffice1: LinearLayout

    private lateinit var cardFurniture1: MaterialCardView
    private lateinit var cardFurnitureContent: MaterialCardView
    private lateinit var linearFurniture1: LinearLayout

    private lateinit var householdSubCatLinearBack: LinearLayout

    //electronics views
    private lateinit var electronicTelevision: TextView
    private lateinit var electronicDvdPlayer: TextView
    private lateinit var electronicHomeTheatres: TextView
    private lateinit var electronicSpeakers: TextView
    private lateinit var electronicPortableAudio: TextView
    private lateinit var electronicDigitalCamera: TextView
    private lateinit var electronicProjectors: TextView

    //phone and tablets
    private lateinit var smartPhone: TextView
    private lateinit var featuredPhones: TextView
    private lateinit var tablets: TextView
    private lateinit var phoneLandLine: TextView
    private lateinit var phoneChargers: TextView
    private lateinit var phoneDataAndConnectivity: TextView
    private lateinit var phoneHeadsets: TextView
    private lateinit var phoneMemoryCards: TextView
    private lateinit var phoneLaptopBags: TextView

    //computing views
    private lateinit var desktopComputer: TextView
    private lateinit var printersAndScanner: TextView
    private lateinit var laptops: TextView
    private lateinit var usbFlashDrives: TextView
    private lateinit var computerMonitor: TextView
    private lateinit var computerAccessories: TextView
    private lateinit var computerCpu: TextView
    private lateinit var computerLaptopBags: TextView
    private lateinit var computerExternalAndInternalHardDrives: TextView

    //home and office views
    private lateinit var refrigerator: TextView
    private lateinit var cookers: TextView
    private lateinit var microwaves: TextView
    private lateinit var carpets: TextView
    private lateinit var vacuumCleaners: TextView
    private lateinit var washingMachine: TextView
    private lateinit var electricExtensions: TextView
    private lateinit var electricSmallAppliances: TextView
    private lateinit var homeUtensils: TextView

    //furniture views
    private lateinit var furnitureSofas: TextView
    private lateinit var furnitureBeds: TextView
    private lateinit var furnitureTables: TextView
    private lateinit var furnitureChairs: TextView
    private lateinit var furnitureSideboards: TextView
    private lateinit var furnitureDoors: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHouseholdSubCategoriesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        initializingViews()
        return binding.root
    }

    private fun initializingViews() {
        //electronic views
        electronicTelevision = binding.electronicTelevision
        electronicDvdPlayer = binding.electronicDvdPlayers
        electronicHomeTheatres = binding.electronicHomeTheatre
        electronicSpeakers = binding.electronicSpeakers
        electronicPortableAudio = binding.electronicPortableAudio
        electronicDigitalCamera = binding.electronicDigitalCamera
        electronicProjectors = binding.electronicProjector

        clickListenersForElectronics()

        //phone and tablets
        smartPhone = binding.phoneSmartPhone
        featuredPhones = binding.phoneFeaturedPhones
        tablets = binding.tablets
        phoneLandLine = binding.phonesLandLines
        phoneChargers = binding.phoneChargers
        phoneDataAndConnectivity = binding.phoneDataAndConnectivity
        phoneHeadsets = binding.phoneHeadsets
        phoneMemoryCards = binding.phoneMemoryCards
        phoneLaptopBags = binding.phoneLaptopBags

        clickListenerForPhonesAndTablets()

        //computing views
        desktopComputer = binding.computingDesktopComputer
        printersAndScanner = binding.computingPrinterAndScanners
        laptops = binding.computingLaptops
        usbFlashDrives = binding.computingUsbFlashDrives
        computerMonitor = binding.computingMonitors
        computerAccessories = binding.computingComputerAccessories
        computerCpu = binding.computingCpu
        computerLaptopBags = binding.computingLaptopsBags
        computerExternalAndInternalHardDrives = binding.computingExternalAndInternalHardDrive

        clickListenerForComputing()

        //home and office
        refrigerator = binding.homeAndOfficeRefrigerators
        cookers = binding.homeAndOfficeCookers
        microwaves = binding.homeAndOfficeMicrowave
        carpets = binding.homeAndOfficeCarpets
        vacuumCleaners = binding.homeAndOfficeVacuumCleaners
        washingMachine = binding.homeAndOfficeWashingMachine
        electricExtensions = binding.homeAndOfficeExtension
        electricSmallAppliances = binding.homeAndOfficeSmallAppliances
        homeUtensils = binding.homeAndOfficeUtensil

        clickListenersForHomeAndOffices()

        //furniture
        furnitureBeds = binding.furnitureBeds
        furnitureChairs = binding.furnitureChairs
        furnitureDoors = binding.furnitureDoor
        furnitureSideboards = binding.furnitureSideboard
        furnitureSofas = binding.furnitureSofas
        furnitureTables = binding.furnitureTables

        clickListenerForFurniture()


        electronicsCard1 = binding.cardElectronic1
        electronicsLinear1 = binding.cardElectronicsLinear1
        electronicsCardContent = binding.electronicsCardContent

        cardFurniture1 = binding.cardFurniture1
        cardFurnitureContent = binding.cardFurnitureContent
        linearFurniture1 = binding.linearFurniture1

        phoneAndTabletCard1 = binding.cardPhoneAndTablet1
        phoneAndTabletCardContent = binding.phoneAndTabletCardContent
        phoneAndTabletLinear1 = binding.cardPhoneAndTabletLinear1


        cardComputing1 = binding.cardComputing1
        cardComputingContent = binding.cardComputingContent
        linearComputing1 = binding.linearComputing1


        cardHomeAndOffice1 = binding.cardHomeAndOffice1
        cardHomeAndOfficeContent = binding.cardHomeAndOfficeContent
        linearHomeAndOffice1 = binding.linearHomeAndOffice1

        householdSubCatLinearBack = binding.householdSubCatLinearBack

        householdSubCatLinearBack.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_navigation_categories)
        }


        //furniture
        cardFurniture1.setOnClickListener {
            cardFurniture1.visibility = View.GONE
            cardFurnitureContent.visibility = View.VISIBLE

            phoneAndTabletCardContent.visibility = View.GONE
            phoneAndTabletCard1.visibility = View.VISIBLE

            electronicsCardContent.visibility = View.GONE
            electronicsCard1.visibility = View.VISIBLE

            cardComputingContent.visibility = View.GONE
            cardComputing1.visibility = View.VISIBLE

            cardHomeAndOfficeContent.visibility = View.GONE
            cardHomeAndOffice1.visibility = View.VISIBLE
        }

        linearFurniture1.setOnClickListener {
            cardFurniture1.visibility = View.VISIBLE
            cardFurnitureContent.visibility = View.GONE
        }

        //home and offices
        cardHomeAndOffice1.setOnClickListener {
            cardHomeAndOffice1.visibility = View.GONE
            cardHomeAndOfficeContent.visibility = View.VISIBLE

            phoneAndTabletCardContent.visibility = View.GONE
            phoneAndTabletCard1.visibility = View.VISIBLE

            electronicsCardContent.visibility = View.GONE
            electronicsCard1.visibility = View.VISIBLE

            cardComputingContent.visibility = View.GONE
            cardComputing1.visibility = View.VISIBLE

            cardFurnitureContent.visibility = View.GONE
            cardFurniture1.visibility = View.VISIBLE
        }

        linearHomeAndOffice1.setOnClickListener {
            cardHomeAndOffice1.visibility = View.VISIBLE
            cardHomeAndOfficeContent.visibility = View.GONE
        }

        //computing category
        cardComputing1.setOnClickListener {
            cardComputing1.visibility = View.GONE
            cardComputingContent.visibility = View.VISIBLE

            phoneAndTabletCardContent.visibility = View.GONE
            phoneAndTabletCard1.visibility = View.VISIBLE

            electronicsCardContent.visibility = View.GONE
            electronicsCard1.visibility = View.VISIBLE

            cardHomeAndOfficeContent.visibility = View.GONE
            cardHomeAndOffice1.visibility = View.VISIBLE

            cardFurnitureContent.visibility = View.GONE
            cardFurniture1.visibility = View.VISIBLE

        }

        linearComputing1.setOnClickListener {
            cardComputing1.visibility = View.VISIBLE
            cardComputingContent.visibility = View.GONE
        }
        //electronics category
        electronicsCard1.setOnClickListener {

            electronicsCard1.visibility = View.GONE
            electronicsCardContent.visibility = View.VISIBLE

            phoneAndTabletCardContent.visibility = View.GONE
            phoneAndTabletCard1.visibility = View.VISIBLE

            cardComputingContent.visibility = View.GONE
            cardComputing1.visibility = View.VISIBLE

            cardHomeAndOfficeContent.visibility = View.GONE
            cardHomeAndOffice1.visibility = View.VISIBLE

            cardFurnitureContent.visibility = View.GONE
            cardFurniture1.visibility = View.VISIBLE


        }
        electronicsLinear1.setOnClickListener {
            electronicsCard1.visibility = View.VISIBLE
            electronicsCardContent.visibility = View.GONE
        }

        //phone and tablets category
        phoneAndTabletCard1.setOnClickListener {
            phoneAndTabletCard1.visibility = View.GONE
            phoneAndTabletCardContent.visibility = View.VISIBLE

            electronicsCardContent.visibility = View.GONE
            electronicsCard1.visibility = View.VISIBLE

            cardComputingContent.visibility = View.GONE
            cardComputing1.visibility = View.VISIBLE

            cardHomeAndOfficeContent.visibility = View.GONE
            cardHomeAndOffice1.visibility = View.VISIBLE

            cardFurnitureContent.visibility = View.GONE
            cardFurniture1.visibility = View.VISIBLE

        }

        phoneAndTabletLinear1.setOnClickListener {
            phoneAndTabletCard1.visibility = View.VISIBLE
            phoneAndTabletCardContent.visibility = View.GONE
        }
    }

    private fun clickListenerForFurniture() {
        furnitureBeds.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_bedsFragment)
        }
        furnitureChairs.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_chairsFragment)
        }
        furnitureDoors.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_doorsFragment)
        }
        furnitureSideboards.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_sideboardsFragment)
        }
        furnitureSofas.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_sofasFragment)
        }
        furnitureTables.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_tablesFragment)
        }
    }

    private fun clickListenersForHomeAndOffices() {
        refrigerator.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_refrigeratorsFragment)
        }
        cookers.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_cookersFragment)
        }
        microwaves.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_microwavesFragment)
        }
        carpets.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_smallElectricAppliancesFragment)
        }
        vacuumCleaners.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_smallElectricAppliancesFragment)
        }
        washingMachine.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_smallElectricAppliancesFragment)
        }
        electricExtensions.setOnClickListener {
            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_smallElectricAppliancesFragment)
        }
        electricSmallAppliances.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_smallElectricAppliancesFragment)
        }
        homeUtensils.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_utensilsFragment)
        }
    }




    private fun clickListenerForComputing() {
        desktopComputer.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_monitorsAndCpuFragment)
        }
        printersAndScanner.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_printersAndScannersFragment)
        }
        laptops.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_laptopsFragment)
        }
        usbFlashDrives.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_computerAccessoriesFragment)
        }
        computerMonitor.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_monitorsAndCpuFragment)
        }
        computerAccessories.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_computerAccessoriesFragment)
        }
        computerCpu.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_monitorsAndCpuFragment)
        }
        computerLaptopBags.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_computerAccessoriesFragment)
        }
        computerExternalAndInternalHardDrives.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_computerAccessoriesFragment)
        }

    }



    private fun clickListenerForPhonesAndTablets() {
        smartPhone.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_smartPhonesFragment)
        }
        featuredPhones.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_featuredPhonesFragment)
        }
        tablets.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_tabletsFragment)
        }
        phoneLandLine.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_phoneChargersFragment)
        }
        phoneChargers.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_phoneChargersFragment)
        }
        phoneDataAndConnectivity.setOnClickListener {

            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_phoneChargersFragment)
        }
        phoneHeadsets.setOnClickListener {
            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_phoneChargersFragment)
        }
        phoneMemoryCards.setOnClickListener {
            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_phoneChargersFragment)
        }
        phoneLaptopBags.setOnClickListener {
            //to be worked on later
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_phoneChargersFragment)
        }
    }



    private fun clickListenersForElectronics() {
        electronicTelevision.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_televisionFragment)
        }
        electronicDvdPlayer.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_homeTheatresFragment)
        }
        electronicHomeTheatres.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_homeTheatresFragment)
        }
        electronicSpeakers.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_homeTheatresFragment)

        }
        electronicPortableAudio.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_portableAudioFragment2)
        }
        electronicDigitalCamera.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_projectorsFragment)
        }
        electronicProjectors.setOnClickListener {
            findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_projectorsFragment)
        }
    }


}