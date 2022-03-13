package com.ellipcom.ellipcom.ui.customerAddresses

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.NewCustomerAddressFragmentBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CustomerAddressModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class NewCustomerAddressFragment : Fragment() {

    //binding
    private var _binding: NewCustomerAddressFragmentBinding? = null
    private val binding get() = _binding!!

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    //views
    private lateinit var regionTextField: TextInputLayout
    private lateinit var townTextField: TextInputLayout
    private lateinit var mobilePhoneTextField: TextInputLayout
    private lateinit var mobilePhoneTextField2: TextInputLayout
    private lateinit var textEditFirstName: TextInputEditText
    private lateinit var textEditLastName: TextInputEditText
    private lateinit var textEditAddress: TextInputEditText
    private lateinit var textEditPhoneNumber: TextInputEditText
    private lateinit var textEditAdditionalPhoneNumber: TextInputEditText
    private lateinit var textEditRegion: AutoCompleteTextView
    private lateinit var textEditTown: AutoCompleteTextView
    private lateinit var countryPhoneCode: AutoCompleteTextView
    private lateinit var countryPhoneCode2: AutoCompleteTextView
    private lateinit var btnSaveAddress: MaterialButton
    private lateinit var addressProgressBar: ProgressBar

    //firebase
    private lateinit var fireDb: FirebaseFirestore


    private lateinit var viewModel: NewCustomerAddressViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewCustomerAddressFragmentBinding.inflate(inflater, container, false)

        initAddressViews()



        return binding.root
    }


    private fun initAddressViews() {

        fireDb = FirebaseFirestore.getInstance()

        regionTextField = binding.regionTextField
        townTextField = binding.townTextField
        mobilePhoneTextField = binding.mobilePhoneTextField
        mobilePhoneTextField2 = binding.mobilePhoneTextField2
        textEditRegion = binding.autoAddressRegion
        btnSaveAddress = binding.btnSaveAddress
        textEditFirstName = binding.textEditFirstName
        textEditLastName = binding.textEditLastName
        textEditAddress = binding.textEditAddress
        textEditTown = binding.autoAddressTown
        countryPhoneCode = binding.addressCode
        countryPhoneCode2 = binding.addressCode2
        textEditAdditionalPhoneNumber = binding.textEditAdditionalPhoneNumber
        textEditPhoneNumber = binding.textEditPhoneNumber
        addressProgressBar = binding.progressbarAddress


        val regionItems =
            listOf("central region", "western region", "eastern  region", "northern region")
        val regionAdapter =
            ArrayAdapter(requireContext(), R.layout.address_region_list_items, regionItems)
        (regionTextField.editText as? AutoCompleteTextView)?.setAdapter(regionAdapter)

        val townItems = listOf("Nakawa", "Rubaga", "Makindye", "kawempe")
        val townAdapter =
            ArrayAdapter(requireContext(), R.layout.address_region_list_items, townItems)
        (townTextField.editText as? AutoCompleteTextView)?.setAdapter(townAdapter)

        val mobilePhoneCodeItems = listOf("+256")
        val mobilePhoneAdapter =
            ArrayAdapter(requireContext(), R.layout.address_region_list_items, mobilePhoneCodeItems)
        (mobilePhoneTextField.editText as? AutoCompleteTextView)?.setAdapter(mobilePhoneAdapter)

        val mobilePhoneCodeItems2 = listOf("+256")
        val mobilePhoneAdapter2 = ArrayAdapter(
            requireContext(),
            R.layout.address_region_list_items,
            mobilePhoneCodeItems2
        )
        (mobilePhoneTextField2.editText as? AutoCompleteTextView)?.setAdapter(mobilePhoneAdapter2)



        btnSaveAddress.setOnClickListener {


            registerAddress()

        }


    }

    @SuppressLint("SimpleDateFormat")
    private fun registerAddress() {
        if (textEditFirstName.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "enter first name", Toast.LENGTH_SHORT).show()

        } else if (textEditLastName.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "enter last name", Toast.LENGTH_SHORT).show()
        } else if (textEditAddress.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "enter address", Toast.LENGTH_SHORT).show()
        } else if (textEditRegion.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "choose region", Toast.LENGTH_SHORT).show()
        } else if (textEditTown.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "choose town", Toast.LENGTH_SHORT).show()
        } else if (countryPhoneCode.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "choose country mobile code", Toast.LENGTH_SHORT).show()
        } else if (textEditPhoneNumber.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "enter enter phone number", Toast.LENGTH_SHORT).show()
        } else if (countryPhoneCode2.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "choose country mobile code 2", Toast.LENGTH_SHORT).show()
        } else if (textEditAdditionalPhoneNumber.text.toString().trim().isEmpty()) {

            Toast.makeText(context, "enter additional phone number", Toast.LENGTH_SHORT).show()
        } else {

            addressProgressBar.visibility = View.VISIBLE
            btnSaveAddress.visibility = View.GONE

            val phoneNumber =
                countryPhoneCode.text.toString() + " " + textEditPhoneNumber.text.toString()
            val additionalPhoneNumber =
                countryPhoneCode2.text.toString() + " " + textEditAdditionalPhoneNumber.text.toString()

            val addressId = System.currentTimeMillis().toString()

            val savedCurrentTime = SimpleDateFormat("yyy-MM-dd HH:mm:ss a")
            val addressTimeEntered = savedCurrentTime.format(Date())

            registerNewCustomerAddress(
                textEditFirstName.text.toString(),
                textEditLastName.text.toString(),
                textEditAddress.text.toString(),
                textEditRegion.text.toString(),
                textEditTown.text.toString(),
                phoneNumber,
                additionalPhoneNumber,
                addressId,
                addressTimeEntered
            )


        }

    }

    private fun registerNewCustomerAddress(
        firstName: String,
        lastName: String,
        address: String,
        region: String,
        town: String,
        phoneNumber: String,
        additionalPhone: String,
        addressId: String,
        timeCreated: String
    ) {

        val customerAddress = CustomerAddressModel(
            firstName,
            lastName,
            address,
            region,
            town,
            phoneNumber,
            additionalPhone,
            addressId,
            timeCreated
        )

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val userEmail = FirebaseAuth.getInstance().currentUser!!.email


        fireDb.collection("User Addresses")
            .document(userEmail.toString())
            .collection(userId)
            .document(addressId)
            .set(customerAddress)
            .addOnSuccessListener {
                findNavController().navigate(R.id.action_newCustomerAddressFragment_to_addressNoteBookFragment)
                Toast.makeText(context, "address savedd successful", Toast.LENGTH_SHORT).show()
                addressProgressBar.visibility = View.GONE
                btnSaveAddress.visibility = View.VISIBLE


            }
            .addOnFailureListener {
                addressProgressBar.visibility = View.GONE
                btnSaveAddress.visibility = View.VISIBLE
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }


    }


}