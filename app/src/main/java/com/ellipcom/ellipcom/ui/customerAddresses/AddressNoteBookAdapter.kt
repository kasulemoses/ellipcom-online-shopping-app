package com.ellipcom.ellipcom.ui.customerAddresses

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.databinding.AddressNoteBookListRecyclerViewBinding
import com.ellipcom.ellipcom.model.CustomerAddressModel

class AddressNoteBookAdapter(
    private val addressNoteBookList: ArrayList<CustomerAddressModel>,
    private val OnProductClickedListener: OnProductClickListener
) :
    RecyclerView.Adapter<AddressNoteBookAdapter.AddressNoteBookViewHolder>() {


    class AddressNoteBookViewHolder(val addressNoteBookListBinding: AddressNoteBookListRecyclerViewBinding) :
        RecyclerView.ViewHolder(addressNoteBookListBinding.root)

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): AddressNoteBookAdapter.AddressNoteBookViewHolder {
        return AddressNoteBookViewHolder(
            AddressNoteBookListRecyclerViewBinding.inflate(
                LayoutInflater.from(p0.context),
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(p0: AddressNoteBookAdapter.AddressNoteBookViewHolder, p1: Int) {


        p0.itemView.setOnClickListener {
            OnProductClickedListener.onProductItemClick(p1)
        }

        p0.addressNoteBookListBinding.addressListFirstName.text = addressNoteBookList[p1].firstName
        p0.addressNoteBookListBinding.addressListLastName.text = addressNoteBookList[p1].lastName
        p0.addressNoteBookListBinding.addressListAddress.text = addressNoteBookList[p1].address
        p0.addressNoteBookListBinding.addressListTown.text = addressNoteBookList[p1].customerTown
        p0.addressNoteBookListBinding.addressListRegion.text =
            addressNoteBookList[p1].customerRegion
        p0.addressNoteBookListBinding.addressListPhoneNumber.text =
            addressNoteBookList[p1].customerPhoneNumber
        p0.addressNoteBookListBinding.addressListAdditionalPhoneNumber.text =
            addressNoteBookList[p1].customerAdditionalPhoneNumber

        p0.addressNoteBookListBinding.editAddress.setOnClickListener {
            Toast.makeText(
                it.context,
                "you are editing ${addressNoteBookList[p1].customerAddressId}",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    override fun getItemCount(): Int {
        return addressNoteBookList.size
    }

    fun saveAddressFromAdapter(position: Int): String {
        val addressID = addressNoteBookList[position].customerAddressId.toString()
        return addressID
    }
}