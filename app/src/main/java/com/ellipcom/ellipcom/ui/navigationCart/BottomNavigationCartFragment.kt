package com.ellipcom.ellipcom.ui.navigationCart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentBottomNavigationCartBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.ProductCartModel
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore


class BottomNavigationCartFragment : Fragment() {

//    private lateinit var bottomNavigationCartViewModel: BottomNavigationCartViewModel


    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()

    private var _binding: FragmentBottomNavigationCartBinding? = null

    //views
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var cartAdapter: BottomNavCartAdapter

    private lateinit var cartItemList: ArrayList<ProductCartModel>

    private lateinit var productGrandPrice: TextView
    private lateinit var cartTotalProducts: TextView
    private lateinit var btnCompleteOrder: MaterialButton

    private lateinit var swipeToRefreshCartList:SwipeRefreshLayout

    private var grandTotal = 0.0
    private var totalProductsOncart = 0.0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //firebase
    private lateinit var fireDB: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        bottomNavigationCartViewModel =
//            ViewModelProvider(this).get(BottomNavigationCartViewModel::class.java)

        _binding = FragmentBottomNavigationCartBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {


        fireDB = FirebaseFirestore.getInstance()
        addProductsToCartRecyclerView()

        productGrandPrice = binding.productPriceGrandTotal
        btnCompleteOrder = binding.btnCompleteOrder
        cartTotalProducts = binding.totalProductsOnCart
        swipeToRefreshCartList = binding.swipeRefresh

        val user = FirebaseAuth.getInstance().currentUser

        btnCompleteOrder.setOnClickListener {

            if (grandTotal != 0.0) {
                if (user != null) {
                    //temporary
                    findNavController().navigate(R.id.action_navigation_cart_to_newCustomerAddressFragment)

                    // checkCustomerAddress()
                } else {
                    Toast.makeText(context, "register first", Toast.LENGTH_SHORT).show()
//                    findNavController().navigate(R.id.action_navigation_cart_to_registrationFragment)
                }

            } else {
                Toast.makeText(context, "cart is empty", Toast.LENGTH_SHORT).show()
            }
        }




    }

    private fun checkCustomerAddress() {

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val userEmail = FirebaseAuth.getInstance().currentUser!!.email


        fireDB.collection("User Addresses")
            .document(userEmail.toString())
            .collection(userId)
            .get()
            .addOnSuccessListener {
                if (it.documents.isEmpty()) {

                    findNavController().navigate(R.id.action_navigation_cart_to_newCustomerAddressFragment)

                } else {
//                    val action =
//                        BottomNavigationCartFragmentDirections.actionNavigationCartToDeliveryFragment(
//                            grandTotal.toString()
//                        )
//                    findNavController().navigate(action)
                }
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addProductsToCartRecyclerView() {

        //recycler views
        cartRecyclerView = binding.cartRecyclerView
        cartRecyclerView.setHasFixedSize(true)
        cartRecyclerView.layoutManager = LinearLayoutManager(context)
        cartItemList = ArrayList()
        cartAdapter = BottomNavCartAdapter(cartItemList)
        cartRecyclerView.adapter = cartAdapter

        fireDB.collection(EllipcomAppConstants.ELLIPCOM_APP_CART)
            .document(EllipcomAppConstants.ELLIPCOM_APP_SUB_CART)
            .collection(EllipcomAppConstants.ELLIPCOM_APP_PRODUCTS)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Toast.makeText(context, "error occurred", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                if (value != null) {
                    for (product in value.documentChanges) {
                        if (product.type == DocumentChange.Type.ADDED) {

                            val data = product.document.toObject(ProductCartModel::class.java)

                            cartItemList.add(data)

                            /*
                            * setting the grand total for the price
                            * */
                            grandTotal += data.totalCartProductPrice!!.toFloat()

                            //storing the grand total in the shared view model
                            sharedViewModel.productGrandTotal(grandTotal.toString())

                            totalProductsOncart += data.cartProductQuantity!!.toInt()

                            //hash for the cart number of products
                            val cartTotal = hashMapOf("cartTotalNumber" to totalProductsOncart)


                            //keeping the total number of products in the cart to the database
                            fireDB.collection("cart product")
                                .document("cart number")
                                .set(cartTotal)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        context,
                                        "$totalProductsOncart is added",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                                }

                        }


                    }
                    productGrandPrice.text = grandTotal.toString()
                    cartTotalProducts.text = totalProductsOncart.toString()

                    cartAdapter.notifyDataSetChanged()

                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}