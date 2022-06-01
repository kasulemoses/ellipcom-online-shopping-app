package com.ellipcom.ellipcom.ui.delivery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.DeliveryFragmentBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CustomerAddressModel
import com.ellipcom.ellipcom.model.DeliveryModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DeliveryFragment : Fragment() {

    //view binding
    private var _binding: DeliveryFragmentBinding? = null
    private val binding get() = _binding!!

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    //views
    private lateinit var deliveryFirstName: TextView
    private lateinit var deliveryLastName: TextView
    private lateinit var deliveryAddress: TextView
    private lateinit var deliveryRegion: TextView
    private lateinit var deliveryTown: TextView
    private lateinit var deliveryPhoneNumber: TextView
    private lateinit var deliveryAdditionalPhoneNumber: TextView
    private lateinit var cardChangeAddress: MaterialCardView


    //shipping views
    private lateinit var radioBtnStandardShipping: MaterialRadioButton
    private lateinit var tvStandardShipDescription: TextView
    private lateinit var radioBtnCustomerPickUp: MaterialRadioButton
    private lateinit var radioGroupShipping: RadioGroup
    private lateinit var tvCustomerPickUpDescription: TextView

    //payment views
    private lateinit var radioBtnMobileMoney: MaterialRadioButton
    private lateinit var tvMobileMoneyDescription: TextView
    private lateinit var radioBtnPayOnDelivery: MaterialRadioButton
    private lateinit var radioGroupPayment: RadioGroup
    private lateinit var tvPayOnDeliveryDescription: TextView

    //totals
    private lateinit var subTotalPayment: TextView
    private lateinit var deliveryFees: TextView
    private lateinit var grandTotalToBePaid: TextView

    private lateinit var btnProceedCheckOut: MaterialButton


    private lateinit var viewModel: DeliveryViewModel

    //firebase
    private lateinit var fireDB: FirebaseFirestore

    //safe arg
    //val addressId: DeliveryFragmentArgs by navArgs()
    //val grandTotal: DeliveryFragmentArgs by navArgs()

    var addressIdReceived = ""
    var radioButtonShippingStatus = ""
    var radioButtonPaymentStatus = ""

    //var productGrandTotal = ""
    var totalMoney = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DeliveryFragmentBinding.inflate(inflater, container, false)

        //addressIdReceived = addressId.deliveryAdressId

        //productGrandTotal = grandTotal.deliveryAdressId


        //shared view model
        sharedViewModel.productGrandTotal.observe(viewLifecycleOwner) {
            computingTotalMoney(it)
        }


        initialisingDeliveryViews()


        return binding.root
    }

    private fun initialisingDeliveryViews() {

        fireDB = FirebaseFirestore.getInstance()

        //shipping views
        radioBtnStandardShipping = binding.RdbtnStandardShipping
        radioBtnCustomerPickUp = binding.RdbtnPickUp
        tvStandardShipDescription = binding.stdShippingDescription
        tvCustomerPickUpDescription = binding.RdbtnPickUp
        radioGroupShipping = binding.radioGroupShipping

        //payment views
        radioBtnMobileMoney = binding.RdbtnMobileMoney
        tvMobileMoneyDescription = binding.mobileMoneyDescription
        radioBtnPayOnDelivery = binding.RdbtnPayOnDelivery
        radioGroupPayment = binding.radioGroupPayment
        tvPayOnDeliveryDescription = binding.payOnDeliveryDescription

        //totals
        subTotalPayment = binding.paymentSubTotal
        deliveryFees = binding.deliveryFees
        grandTotalToBePaid = binding.productTotalPayment

        btnProceedCheckOut = binding.btnProceedCheckOut


        deliveryFirstName = binding.deliveryAddressFirstName
        deliveryLastName = binding.deliveryAddressLastName
        deliveryAddress = binding.deliveryAddressAddress
        deliveryRegion = binding.deliveryAddressRegion
        deliveryTown = binding.deliveryAddressTown
        deliveryPhoneNumber = binding.deliveryAddressPhoneNumber
        deliveryAdditionalPhoneNumber = binding.deliveryAddressAdditionalPhoneNumber

        cardChangeAddress = binding.changeAddress

        btnProceedCheckOut.setOnClickListener {
            deliveryInformation()
        }


        //want to change the address using the change address card view
        cardChangeAddress.setOnClickListener {
            findNavController().navigate(R.id.action_deliveryFragment_to_addressNoteBookFragment)
        }

        //address shared view model
        sharedViewModel.addressId.observe(viewLifecycleOwner, {
            bindingAddressDataToViews(it)
            addressIdReceived = it
        })

        shippingMethod()
        paymentMethod()

    }


    @SuppressLint("SetTextI18n")
    private fun computingTotalMoney(_pdtGrandTotal: String) {
        //setting the received value to the corresponding view
        subTotalPayment.text = "UGX: $_pdtGrandTotal"

        val deliveryMoney = 5000.0
        totalMoney = (deliveryMoney + _pdtGrandTotal.toFloat())

        sharedViewModel.saveTotalOverallAmount(totalMoney.toString())

        grandTotalToBePaid.text = "UGX: $totalMoney"

        deliveryFees.text = "UGX: $deliveryMoney"
    }


    private fun shippingMethod() {
        radioBtnStandardShipping.isChecked = true
        radioButtonShippingStatus = "Standard shipping"

        radioGroupShipping.setOnCheckedChangeListener { radioGroup, i ->
            if (radioBtnStandardShipping.isChecked) {
                radioButtonShippingStatus = "Standard shipping"

            } else if (radioBtnCustomerPickUp.isChecked) {
                radioButtonShippingStatus = "Customer pick up"

            } else {
                radioButtonShippingStatus = ""
            }
        }
    }


    private fun paymentMethod() {
        radioBtnPayOnDelivery.isChecked = true
        radioButtonPaymentStatus = "Pay On Delivery"
        radioGroupPayment.setOnCheckedChangeListener { radioGroup, i ->
            if (radioBtnMobileMoney.isChecked) {
                radioButtonPaymentStatus = "Mobile Money"

                //to be implemented later with its api

            } else if (radioBtnPayOnDelivery.isChecked) {
                radioButtonPaymentStatus = "Pay On Delivery"

            } else {
                radioButtonPaymentStatus = ""
            }
        }
    }


    private fun deliveryInformation() {

        if (radioButtonPaymentStatus == "") {
            Toast.makeText(context, "select payment method", Toast.LENGTH_SHORT).show()
        } else if (radioButtonShippingStatus == "") {
            Toast.makeText(context, "select shipping method", Toast.LENGTH_SHORT).show()
        } else {
            val deliveryModel = DeliveryModel(
                addressIdReceived,
                radioButtonPaymentStatus,
                radioButtonShippingStatus,
                totalMoney.toString()
            )

            //saving the payment method to the shared view model
            sharedViewModel.saveCustomerPaymentMethod(radioButtonPaymentStatus)

            //saving the shipping method to the shared view model
            sharedViewModel.saveCustomerShippingMethod(radioButtonShippingStatus)


            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            val userEmail = FirebaseAuth.getInstance().currentUser!!.email


            fireDB.collection("delivery")
                .document(userEmail.toString())
                .collection(userId)
                .document()
                .set(deliveryModel)
                .addOnSuccessListener {
                    findNavController().navigate(R.id.action_deliveryFragment_to_confirmOrderFragment)
                }
                .addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun bindingAddressDataToViews(addressIdFromViewModel: String) {

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val userEmail = FirebaseAuth.getInstance().currentUser!!.email

        fireDB.collection("User Addresses")
            .document(userEmail.toString())
            .collection(userId)
            .whereEqualTo("customerAddressId", addressIdFromViewModel)
            .get()
            .addOnSuccessListener {

                for (document in it.documents) {

                    val data = document.toObject(CustomerAddressModel::class.java)

                    if (data != null) {
                        deliveryFirstName.text = data.firstName
                        deliveryLastName.text = data.lastName
                        deliveryAddress.text = data.address
                        deliveryRegion.text = data.customerRegion
                        deliveryTown.text = data.customerTown
                        deliveryPhoneNumber.text = data.customerPhoneNumber
                        deliveryAdditionalPhoneNumber.text = data.customerAdditionalPhoneNumber

                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(context, "failed ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


}