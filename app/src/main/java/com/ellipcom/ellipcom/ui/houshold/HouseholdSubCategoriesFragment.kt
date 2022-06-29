package com.ellipcom.ellipcom.ui.houshold

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
import com.ellipcom.ellipcom.databinding.FragmentHouseholdSubCategoriesBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
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

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    private var householdSubCat = ""

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
            householdSubCat = "furniture_beds"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        furnitureChairs.setOnClickListener {
            householdSubCat = "furniture_chairs"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        furnitureDoors.setOnClickListener {
            householdSubCat = "furniture_doors"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        furnitureSideboards.setOnClickListener {
            householdSubCat = "furniture_sideboards"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        furnitureSofas.setOnClickListener {
            householdSubCat = "furniture_sofas"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        furnitureTables.setOnClickListener {
            householdSubCat = "furniture_tables"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
    }

    private fun storingHouseholdSubCatToSharedViewModel(householdSubCat: String) {
        sharedViewModel.savingHouseholdSubCatViewId(householdSubCat)
       // findNavController().navigate(R.id.action_householdSubCategoriesFragment_to_mainHouseholdRecyclerViewSubCatFragment)
    }

    private fun clickListenersForHomeAndOffices() {
        refrigerator.setOnClickListener {
            householdSubCat = "homeAndOffice_refrigerator"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        cookers.setOnClickListener {
            householdSubCat = "homeAndOffice_cookers"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        microwaves.setOnClickListener {
            householdSubCat = "homeAndOffice_microwaves"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        carpets.setOnClickListener {
            householdSubCat = "homeAndOffice_carpets"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)

        }
        vacuumCleaners.setOnClickListener {
            householdSubCat = "homeAndOffice_vacuumCleaners"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        washingMachine.setOnClickListener {
            householdSubCat = "homeAndOffice_washingMachines"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electricExtensions.setOnClickListener {
            householdSubCat = "homeAndOffice_electricExtensions"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electricSmallAppliances.setOnClickListener {
            householdSubCat = "homeAndOffice_smallAppliances"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        homeUtensils.setOnClickListener {
            householdSubCat = "homeAndOffice_utensils"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
    }




    private fun clickListenerForComputing() {
        desktopComputer.setOnClickListener {
            householdSubCat = "computing_desktopComputer"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)

        }
        printersAndScanner.setOnClickListener {
            householdSubCat = "computing_printerAndScanners"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        laptops.setOnClickListener {
            householdSubCat = "computing_laptops"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        usbFlashDrives.setOnClickListener {
            householdSubCat = "computing_usbFlashDrives"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        computerMonitor.setOnClickListener {
            householdSubCat = "computing_computerMonitors"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        computerAccessories.setOnClickListener {
            householdSubCat = "computing_accessories"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        computerCpu.setOnClickListener {
            householdSubCat = "computing_cpu"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        computerLaptopBags.setOnClickListener {
            householdSubCat = "computing_laptopBags"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        computerExternalAndInternalHardDrives.setOnClickListener {
            householdSubCat = "computing_externalAndInternalHardDrive"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)

        }

    }


    private fun clickListenerForPhonesAndTablets() {
        smartPhone.setOnClickListener {
            householdSubCat = "phonesAndTablets_smartPhones"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        featuredPhones.setOnClickListener {
            householdSubCat = "phonesAndTablets_featuredPhones"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        tablets.setOnClickListener {
            householdSubCat = "phonesAndTablets_tablets"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        phoneLandLine.setOnClickListener {
            householdSubCat = "phonesAndTablets_phoneAndLandLines"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        phoneChargers.setOnClickListener {
            householdSubCat = "phonesAndTablets_phoneChargers"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        phoneDataAndConnectivity.setOnClickListener {
            householdSubCat = "phonesAndTablets_dataAndConnectivity"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)

        }
        phoneHeadsets.setOnClickListener {
            householdSubCat = "phonesAndTablets_Headsets"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        phoneMemoryCards.setOnClickListener {
            householdSubCat = "phonesAndTablets_memoryCards"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        phoneLaptopBags.setOnClickListener {
            householdSubCat = "phonesAndTablets_laptopBags"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
    }



    private fun clickListenersForElectronics() {
        electronicTelevision.setOnClickListener {
            householdSubCat = "electronics_television"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electronicDvdPlayer.setOnClickListener {
            householdSubCat = "electronics_dvdPlayer"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electronicHomeTheatres.setOnClickListener {
            householdSubCat = "electronics_homeTheatres"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electronicSpeakers.setOnClickListener {
            householdSubCat = "electronics_speakers"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)

        }
        electronicPortableAudio.setOnClickListener {
            householdSubCat = "electronics_portableAudio"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electronicDigitalCamera.setOnClickListener {
            householdSubCat = "electronics_digitalCamera"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
        electronicProjectors.setOnClickListener {
            householdSubCat = "electronics_projectors"
            storingHouseholdSubCatToSharedViewModel(householdSubCat)
        }
    }


}