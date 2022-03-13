package com.ellipcom.ellipcom.ui.comfirmOrder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ellipcom.ellipcom.databinding.ConfirmOrderFragmentBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CustomerAddressModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ConfirmOrderFragment : Fragment() {

    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    private var _binding: ConfirmOrderFragmentBinding? = null
    private val binding get() = _binding!!

    //view
    private lateinit var confirmTotalPayment: TextView
    private lateinit var confirmedPaymentMethod: TextView
    private lateinit var confirmedShippingMethod: TextView

    //views about the address
    private lateinit var confirmedAddressFirstName: TextView
    private lateinit var confirmedAddressLastName: TextView
    private lateinit var confirmedAddress: TextView
    private lateinit var confirmedRegion: TextView
    private lateinit var confirmedTown: TextView
    private lateinit var confirmedMobilePhone: TextView
    private lateinit var confirmedAdditionalPhone: TextView

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    //private lateinit var viewModel: ConfirmOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ConfirmOrderFragmentBinding.inflate(inflater, container, false)

        fireDb = FirebaseFirestore.getInstance()
        initializeViews()

        //observing the total money to be paid by the customer
        sharedViewModel.finalTotalMoney.observe(viewLifecycleOwner, {
            confirmTotalPayment.text = it
        })

        //observing the confirmed payment method for the customer
        sharedViewModel.paymentMethod.observe(viewLifecycleOwner, {
            confirmedPaymentMethod.text = it
        })

        //observing the confirmed shipping method for the customer
        sharedViewModel.shippingMethod.observe(viewLifecycleOwner, {
            confirmedShippingMethod.text = it
        })

        /*
        * Observing the user selected address id using the shared view model and
        * retrieving it to the confirm order page
        * */
        sharedViewModel.addressId.observe(viewLifecycleOwner, {
            updateAddressInConfirmOrder(it)
        })



        return binding.root
    }

    private fun initializeViews() {
        confirmTotalPayment = binding.confirmTotalPayment
        confirmedPaymentMethod = binding.confirmPaymentMethod
        confirmedShippingMethod = binding.confirmShippingMethod

        //view about address
        confirmedAddressFirstName = binding.confirmedAddressFirstName
        confirmedAddressLastName = binding.confirmedAddressLastName
        confirmedRegion = binding.confirmedAddressRegion
        confirmedTown = binding.confirmedAddressTown
        confirmedAddress = binding.confirmedAddressAddress
        confirmedMobilePhone = binding.confirmedAddressPhoneNumber
        confirmedAdditionalPhone = binding.confirmedAddressAdditionalPhoneNumber
    }


    private fun updateAddressInConfirmOrder(addressId: String?) {

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val userEmail = FirebaseAuth.getInstance().currentUser!!.email

        fireDb.collection("User Addresses")
            .document(userEmail.toString())
            .collection(userId)
            .whereEqualTo("customerAddressId", addressId)
            .get()
            .addOnSuccessListener {
                for (document in it.documents) {

                    val confirmedAddressData = document.toObject(CustomerAddressModel::class.java)

                    if (confirmedAddressData != null) {

                        confirmedAddressFirstName.text = confirmedAddressData.firstName
                        confirmedAddressLastName.text = confirmedAddressData.lastName
                        confirmedRegion.text = confirmedAddressData.customerRegion
                        confirmedTown.text = confirmedAddressData.customerTown
                        confirmedMobilePhone.text = confirmedAddressData.customerPhoneNumber
                        confirmedAdditionalPhone.text =
                            confirmedAddressData.customerAdditionalPhoneNumber
                        confirmedAddress.text = confirmedAddressData.address

                    }
                }
            }
            .addOnFailureListener {

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}