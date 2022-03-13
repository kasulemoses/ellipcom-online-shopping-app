package com.ellipcom.ellipcom.ui.productDetailFragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.DetailProductFragmentBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.ProductCartModel
import com.ellipcom.ellipcom.model.ProductInformationModel
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class DetailProductFragment : Fragment() {


    private var _binding: DetailProductFragmentBinding? = null
    private val binding get() = _binding!!


    //views
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productShortDescription: TextView
    private lateinit var productFullDescription: TextView
    private lateinit var productStatus: TextView
    private lateinit var productDeliveryType: TextView
    private lateinit var productImageView: ImageView
    private lateinit var detailImageUnlike: ImageView
    private lateinit var detailImageLike: ImageView
    private lateinit var detailImageShare: ImageView
    private lateinit var detailBtnAddToCart: MaterialButton
    private lateinit var detailBtnBuyNow: MaterialButton
    private lateinit var btnSubtractOne: ImageButton
    private lateinit var btnAddOne: ImageButton
    private lateinit var tvIncrementOrDecrement: TextView


    //firebase
    private lateinit var firestoreDb: FirebaseFirestore

    //safe args
    //val productIdReceived: DetailProductFragmentArgs by navArgs()

    //private lateinit var viewModel: DetailProductViewModel

    private var imageUrl: String = ""
    private var cartProductQuantity: String = ""
    private var totalCarProductPrice = 0.0
    private var counter = 1

    //shared view model
    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailProductFragmentBinding.inflate(inflater, container, false)

        firestoreDb = FirebaseFirestore.getInstance()

        sharedViewModel.productId.observe(viewLifecycleOwner, {
            getDataFromDb(it)

        })


        //pdtId = productIdReceived.productDetails

        bindingDataToViews()


        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun bindingDataToViews() {
        productImageView = binding.productImage
        productName = binding.detailProductName
        productPrice = binding.detailProductPrice
        productShortDescription = binding.detailProductShortDescription
        productFullDescription = binding.detailProductFullDescription
        productStatus = binding.detailProductStatus
        productDeliveryType = binding.detailProductDeliveryType
        detailImageLike = binding.detailImageLike
        detailImageUnlike = binding.detailImageUnlike
        detailImageShare = binding.detailImageShare
        detailBtnAddToCart = binding.detailBtnAddToCart
        detailBtnBuyNow = binding.detailBtnBuyNow

        btnAddOne = binding.btnAdOne
        btnSubtractOne = binding.btnSubtractOne
        tvIncrementOrDecrement = binding.tvIncrementOrDecrement

        btnSubtractOne.setOnClickListener {
            if (counter == 0) {
                counter = 0
                tvIncrementOrDecrement.text = counter.toString()
            } else if (counter <= 1) {
                counter = 1
                tvIncrementOrDecrement.text = counter.toString()
                Snackbar.make(btnSubtractOne, "cart is empty", Snackbar.LENGTH_SHORT).show()

            } else {
                counter--
                tvIncrementOrDecrement.text = counter.toString()
            }

        }

        btnAddOne.setOnClickListener {
            counter++
            tvIncrementOrDecrement.text = counter.toString()
        }



        detailImageShare.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUrl)
                type = "image/*"
            }
            startActivity(Intent.createChooser(shareIntent, resources.getText(R.string.send_to)))
        }

        detailImageUnlike.setOnClickListener {
            detailImageLike.visibility = View.VISIBLE
            detailImageUnlike.visibility = View.GONE
        }

        detailImageLike.setOnClickListener {
            detailImageLike.visibility = View.GONE
            detailImageUnlike.visibility = View.VISIBLE
        }


    }

    @SuppressLint("SetTextI18n")
    private fun getDataFromDb(_pdtId: String) {
        firestoreDb.collection("test1")
            .document("doc_test2")
            .collection("col_test3")
            .whereEqualTo("productId", _pdtId)
            .get()
            .addOnSuccessListener {

                for (document in it.documents) {

                    val data = document.toObject(ProductInformationModel::class.java)

                    if (data != null) {
                        productPrice.text = "ugx " + data.productActualPrice
                        productName.text = data.productName
                        productFullDescription.text = data.productFullDescription
                        productShortDescription.text = data.productShortDescription
                        productStatus.text = data.productStatus
                        productDeliveryType.text = data.productDeliveryType

                        imageUrl = data.productImageUrl.toString()


                        Picasso
                            .get()
                            .load(data.productImageUrl)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(productImageView)


                        detailBtnBuyNow.setOnClickListener {


                        }

                        //giving the add to cart button and buy now button functionality
                        detailBtnAddToCart.setOnClickListener {

                            cartProductQuantity = counter.toString()

                            if (cartProductQuantity.toInt() == 0) {
                                Toast.makeText(context, "no product", Toast.LENGTH_SHORT).show()

                            } else {
                                totalCarProductPrice =
                                    cartProductQuantity.toDouble() * data.productActualPrice!!.toDouble()
                                val cartProduct =
                                    ProductCartModel(
                                        _pdtId,
                                        data.productImageUrl,
                                        data.productName,
                                        data.productActualPrice,
                                        data.productRegularPrice,
                                        cartProductQuantity,
                                        totalCarProductPrice.toString()
                                    )

                                firestoreDb.collection(EllipcomAppConstants.ELLIPCOM_APP_CART)
                                    .document(EllipcomAppConstants.ELLIPCOM_APP_SUB_CART)
                                    .collection(EllipcomAppConstants.ELLIPCOM_APP_PRODUCTS)
                                    .document(_pdtId)
                                    .set(cartProduct)
                                    .addOnSuccessListener {
                                        Snackbar.make(
                                            detailBtnAddToCart,
                                            "product added to cart",
                                            Snackbar.LENGTH_LONG
                                        ).show()
                                    }
                                    .addOnFailureListener {
                                        Snackbar.make(
                                            detailBtnAddToCart,
                                            "try again",
                                            Snackbar.LENGTH_LONG
                                        ).show()
                                    }

                            }


                        }
                    }

                }
            }
            .addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
    }


}