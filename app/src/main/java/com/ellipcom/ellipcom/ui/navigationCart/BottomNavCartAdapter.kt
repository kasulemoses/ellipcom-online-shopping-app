package com.ellipcom.ellipcom.ui.navigationCart

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.databinding.ProductCartRecyclerItemsListBinding
import com.ellipcom.ellipcom.model.ProductCartModel
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class BottomNavCartAdapter(private val cartList: ArrayList<ProductCartModel>) :
    RecyclerView.Adapter<BottomNavCartAdapter.BottomNavViewHolder>() {


    class BottomNavViewHolder(val itemsListBinding: ProductCartRecyclerItemsListBinding) :
        RecyclerView.ViewHolder(itemsListBinding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BottomNavViewHolder {
        return BottomNavViewHolder(
            ProductCartRecyclerItemsListBinding.inflate(
                LayoutInflater.from(
                    p0.context
                ), p0, false
            )
        )
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(p0: BottomNavViewHolder, p1: Int) {


        p0.itemsListBinding.cartProductName.text = cartList[p1].cartProductName
        p0.itemsListBinding.cartProductPrice.text = cartList[p1].cartProductPrice
        p0.itemsListBinding.cartProductRegularPrice.text = cartList[p1].cartProductRegularPrice
        p0.itemsListBinding.pdtQuantity.text = cartList[p1].cartProductQuantity

        // p0.itemsListBinding.totalPdtPrice.text = cartList[p1].totalCartProductPrice


        Picasso.get().load(cartList[p1].imageUrl).into(p0.itemsListBinding.cartProductImage)

        p0.itemsListBinding.linearDeleteCartProduct.setOnClickListener {
            /*
            * alert dialog for removing the product from
            * the cart list added by the user
            * */
            val alertDialog = AlertDialog.Builder(it.context)
            alertDialog.setTitle("Remove")
            alertDialog.setMessage("Are you sure to remove ${cartList[p1].cartProductName} from the cart")
            alertDialog.setPositiveButton("Remove") { dialogInterface: DialogInterface, i: Int ->

                /*
                * deleting the product from the cart list by the user
                * and deleted from the database
                * */
                val fireDb = FirebaseFirestore.getInstance()
                fireDb.collection(EllipcomAppConstants.ELLIPCOM_APP_CART)
                    .document(EllipcomAppConstants.ELLIPCOM_APP_SUB_CART)
                    .collection(EllipcomAppConstants.ELLIPCOM_APP_PRODUCTS)
                    .document(cartList[p1].cartProductId.toString())
                    .delete()
                    .addOnSuccessListener {

                        Snackbar.make(
                            p0.itemsListBinding.linearDeleteCartProduct,
                            "${cartList[p1].cartProductName} is removed",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    .addOnFailureListener {
                        Snackbar.make(
                            p0.itemsListBinding.linearDeleteCartProduct,
                            "${it.message}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
            }
            alertDialog.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }
            alertDialog.create()
            alertDialog.show()
        }
    }


    override fun getItemCount(): Int {
        return cartList.size
    }
}