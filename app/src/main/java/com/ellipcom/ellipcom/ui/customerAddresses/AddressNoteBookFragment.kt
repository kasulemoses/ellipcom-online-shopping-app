package com.ellipcom.ellipcom.ui.customerAddresses

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.AddressNoteBookFragmentBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CustomerAddressModel
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class AddressNoteBookFragment : Fragment(), OnProductClickListener {

    private var _binding: AddressNoteBookFragmentBinding? = null
    private val binding get() = _binding!!

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    //views
    private lateinit var addressNoteBookRecyclerview: RecyclerView
    private lateinit var noteBookAdapter: AddressNoteBookAdapter
    private lateinit var noteBookList: ArrayList<CustomerAddressModel>
    private lateinit var btnAddNewAddress: MaterialButton


    private lateinit var viewModel: AddressNoteBookViewModel

    //firebase
    private lateinit var fireDb: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = AddressNoteBookFragmentBinding.inflate(inflater, container, false)
        fireDb = FirebaseFirestore.getInstance()

        initialiseAddressNoteBookViews()
        return binding.root
    }

    private fun initialiseAddressNoteBookViews() {
        btnAddNewAddress = binding.btnAddNewAddress
        addressNoteBookRecyclerview = binding.addressNoteBookRecyclerView
        addressNoteBookRecyclerview.setHasFixedSize(true)
        addressNoteBookRecyclerview.layoutManager = LinearLayoutManager(context)

        noteBookList = ArrayList()
        noteBookAdapter = AddressNoteBookAdapter(noteBookList, this)
        addressNoteBookRecyclerview.adapter = noteBookAdapter

        btnAddNewAddress.setOnClickListener {
            findNavController().navigate(R.id.action_addressNoteBookFragment_to_newCustomerAddressFragment)
        }

        fetchAddressNoteBookDataFromDatabase()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchAddressNoteBookDataFromDatabase() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val userEmail = FirebaseAuth.getInstance().currentUser!!.email

        fireDb.collection("User Addresses")
            .document(userEmail.toString())
            .collection(userId)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Toast.makeText(context, "error occurred", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                if (value != null) {
                    for (product in value.documentChanges) {
                        if (product.type == DocumentChange.Type.ADDED) {

                            noteBookList.add(product.document.toObject(CustomerAddressModel::class.java))

                        }
                    }

                    noteBookAdapter.notifyDataSetChanged()

                }
            }
    }

    override fun onProductItemClick(position: Int) {
        val addressId = noteBookAdapter.saveAddressFromAdapter(position)
        sharedViewModel.saveCustomerAddressId(addressId)

    }


}