package com.ellipcom.ellipcom.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ellipcom.ellipcom.Interface.OnCategoryClickListener
import com.ellipcom.ellipcom.Interface.OnProductClickListener
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.adapter.MainHomeAppAdapter
import com.ellipcom.ellipcom.databinding.FragmentHomeBinding
import com.ellipcom.ellipcom.mainSharedViewModel.AppMainSharedViewModel
import com.ellipcom.ellipcom.model.CategoryModel
import com.ellipcom.ellipcom.model.ProductData
import com.ellipcom.ellipcom.model.DetailPdtModel
import com.ellipcom.ellipcom.ui.home.homeRecyclerviewAdapters.HomeRecyclerviewAdapter1
import com.ellipcom.ellipcom.utilities.EllipcomAppConstants
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomeFragment : Fragment(), OnProductClickListener, OnCategoryClickListener {

    private val sharedViewModel: AppMainSharedViewModel by activityViewModels()


    private var _binding: FragmentHomeBinding? = null

    // image carousel
    private lateinit var carousel: ImageCarousel

    //arraylist
    private lateinit var categoryList: ArrayList<CategoryModel>

    private lateinit var productList: ArrayList<ProductData>

    private val categoryAdapter by lazy { MainHomeFragmentCategoryAdapter(categoryList) }

    private val productAdapter by lazy { MainHomeAppAdapter(productList, this) }
    private val productAdapter1 by lazy { MainHomeAppAdapter(productList, this) }

    //firebase
    private lateinit var firebaseDb: FirebaseFirestore

    //recycler views
    private lateinit var recyclerview1Adapter: HomeRecyclerviewAdapter1
    private lateinit var recyclerview1: RecyclerView
    private lateinit var productList1: ArrayList<DetailPdtModel>

    private lateinit var categoryRV: RecyclerView

    private lateinit var allProductsRV: RecyclerView


    // onDestroyView.
    private val binding get() = _binding!!

    var pdtId = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        firebaseDb = FirebaseFirestore.getInstance()

        imageCarouselSettings()
        allRecentHomeProduct()

        attachCategoryRvWithData()
        attachAllProductRvWithData()

        binding.viewAllCategories.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_allSubCategoriesFragment)
        }

        imageCaurusel()

        return binding.root
    }

    private fun imageCaurusel() {
        // Kotlin
        val carousel: ImageCarousel = binding.carousel

// Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragment it will be viewLifecycleOwner/getViewLifecycleOwner().
        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()

        firebaseDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
            .document(EllipcomAppConstants.ELLIPCOM_APP_CATEGORY)
            .collection("slider_flyers")
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                        .show()
                    return@addSnapshotListener
                }

                if (value != null) {

                    for (product in value.documentChanges) {
                        if (product.type == DocumentChange.Type.ADDED) {

                            val data = product.document.toObject(CarouselItem::class.java)

                            list.add(data)


                        }
                    }
                    carousel.setData(list)
                    carousel.start()
                }

            }


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun attachAllProductRvWithData() {
        productList = ArrayList()

        allProductsRV = binding.recyclerViewHomeMain
        allProductsRV.setHasFixedSize(true)
        allProductsRV.layoutManager = GridLayoutManager(context, 2)
        allProductsRV.adapter = productAdapter1


        try {
            firebaseDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(EllipcomAppConstants.ELLIPCOM_APP_LATEST_PRODUCTS)
                .collection(EllipcomAppConstants.ELLIPCOM_APP_NEW_PRODUCTS)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                productList.add(product.document.toObject(ProductData::class.java))

                            }
                        }

                    }
                    productAdapter1.notifyDataSetChanged()

                }
        } catch (e: Exception) {
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun attachCategoryRvWithData() {

        categoryRV = binding.recyclerViewHomeCategory
        categoryRV.setHasFixedSize(true)
        categoryRV.layoutManager = GridLayoutManager(context, 3)
        categoryList = ArrayList()
        categoryRV.adapter = categoryAdapter


        try {

            firebaseDb.collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_DATABASE)
                .document(
                    EllipcomAppConstants.ELLIPCOM_APP_CATEGORY
                )
                .collection(EllipcomAppConstants.ELLIPCOM_APP_MAIN_CATEGORY)
                .addSnapshotListener { value, error ->

                    if (error != null) {
                        Toast.makeText(
                            context,
                            "error occurred" + error.message,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        return@addSnapshotListener
                    }

                    if (value != null) {

                        for (product in value.documentChanges) {
                            if (product.type == DocumentChange.Type.ADDED) {

                                categoryList.add(product.document.toObject(CategoryModel::class.java))


                            }
                        }

                    }
                    categoryAdapter.notifyDataSetChanged()

                }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun allRecentHomeProduct() {

        recyclerview1 = binding.homeRecyclerView1
        recyclerview1.layoutManager = GridLayoutManager(context, 2)
        recyclerview1.setHasFixedSize(true)

        productList1 = ArrayList()

        recyclerview1Adapter = HomeRecyclerviewAdapter1(productList1, this)
        recyclerview1.adapter = recyclerview1Adapter

        firebaseDb.collection("test1")
            .document("doc_test2")
            .collection("col_test3")
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Toast.makeText(context, "error occurred" + error.message, Toast.LENGTH_SHORT)
                        .show()
                    return@addSnapshotListener
                }
                if (value != null) {
                    for (product in value.documentChanges) {
                        if (product.type == DocumentChange.Type.ADDED) {

                            productList1.add(product.document.toObject(DetailPdtModel::class.java))


                        }
                    }

                    recyclerview1Adapter.notifyDataSetChanged()

                }
            }
    }

    private fun imageCarouselSettings() {
        carousel = binding.carousel
        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.restaurant,
                caption = "restuarant"
            )
        )
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.television,
                caption = "televisions"
            )
        )
        // If you want auto slide, turn this feature on.
        carousel.autoPlay = true
        carousel.autoPlayDelay = 3000 // Milliseconds
        carousel.showNavigationButtons = false

        carousel.touchToPause = true

//        // Carousel listener
//        carousel.carouselListener = object : CarouselListener {
//            override fun onCreateViewHolder(
//                layoutInflater: LayoutInflater,
//                parent: ViewGroup
//            ): ViewBinding? {
//                // ...
//            }
//
//            override fun onBindViewHolder(
//                binding: ViewBinding,
//                imageScaleType: ImageView.ScaleType,
//                item: CarouselItem,
//                position: Int
//            ) {
//                // ...
//            }
//
//            override fun onClick(position: Int, carouselItem: CarouselItem) {
//                // ...
//            }
//
//            override fun onLongClick(position: Int, dataObject: CarouselItem) {
//                // ...
//            }
//
//        }

        // Start auto play
        carousel.start()

        carousel.setData(list)


    }

    override fun onProductItemClick(position: Int) {
        val dPdtDetails = productAdapter1.saveProductId(position)
        sharedViewModel.savingPdtDetails(dPdtDetails)
       findNavController().navigate(R.id.action_navigation_home_to_productDetailsFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryItemClick(position: Int) {
        Toast.makeText(context, position, Toast.LENGTH_SHORT).show()
    }


}