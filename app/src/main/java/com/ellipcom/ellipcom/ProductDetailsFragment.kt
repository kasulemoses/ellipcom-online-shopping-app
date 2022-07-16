package com.ellipcom.ellipcom

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.databinding.FragmentProductDetailsBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.DetailPdtModel
import com.ellipcom.ellipcom.model.ProductCartModel
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class ProductDetailsFragment : Fragment() {
    private var _binding: FragmentProductDetailsBinding? = null
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

    //private late init var viewModel: DetailProductViewModel

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
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        firestoreDb = FirebaseFirestore.getInstance()

        sharedViewModel.pdtDetails.observe(viewLifecycleOwner) {

            val imageUrl = it.productImageUrl.toString()
            val pdtName = it.productName.toString()
            val pdtPrice = it.productActualPrice.toString()
            val pdtId = it.productId.toString()
            val pdtStatus = it.productStatus.toString()
            val pdtDeliveryType = it.productDeliveryType.toString()
            val pdtShortDesc = it.productShortDescription.toString()
            val pdtFullDesc = it.productFullDescription.toString()

            attachDataDetailsView(imageUrl,pdtName,pdtPrice,pdtId,pdtStatus,pdtDeliveryType,pdtShortDesc,
            pdtFullDesc)
        }


        //pdtId = productIdReceived.productDetails

        bindingDataToViews()
        binding.cardDetailBack.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailsFragment_to_navigation_home)
        }

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

    private fun attachDataDetailsView(
        imageUrl: String,
        pdtName: String,
        pdtPrice: String,
        pdtId: String,
        pdtStatus: String,
        pdtDeliveryType: String,
        pdtShortDesc: String,
        pdtFullDesc: String
    ) {
        productPrice.text = pdtPrice
        productName.text = pdtName
        productFullDescription.text = pdtFullDesc
        productShortDescription.text = pdtShortDesc
        productStatus.text = pdtStatus
        productDeliveryType.text = pdtDeliveryType

        Picasso
            .get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(productImageView)

        //giving the add to cart button and buy now button functionality
        detailBtnAddToCart.setOnClickListener {

            cartProductQuantity = counter.toString()

            if (cartProductQuantity.toInt() == 0) {
                Toast.makeText(context, "no product", Toast.LENGTH_SHORT).show()

            } else {

                totalCarProductPrice =
                    cartProductQuantity.toDouble() * pdtPrice.toDouble()


                val cartProduct =
                    ProductCartModel(
                        pdtId,
                        imageUrl,
                        pdtName,
                        pdtPrice,
                        "",
                        cartProductQuantity,
                        totalCarProductPrice.toString()
                    )

                firestoreDb.collection(EllipcomAppConstants.ELLIPCOM_APP_CART)
                    .document(EllipcomAppConstants.ELLIPCOM_APP_SUB_CART)
                    .collection(EllipcomAppConstants.ELLIPCOM_APP_PRODUCTS)
                    .document(pdtId)
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

    @SuppressLint("SetTextI18n")
    private fun getDataFromDb(_pdtId: String) {
        firestoreDb.collection("test1")
            .document("doc_test2")
            .collection("col_test3")
            .whereEqualTo("productId", _pdtId)
            .get()
            .addOnSuccessListener {

                for (document in it.documents) {

                    val data = document.toObject(DetailPdtModel::class.java)

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


                    }

                }
            }
            .addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
    }

}