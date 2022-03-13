package com.ellipcom.ellipcom.model

data class ProductInformationModel(
    val productId: String? = null,
    val productName: String? = null,
    val productImageUrl: String? = null,
    val productRegularPrice: String? = null,
    val productActualPrice: String? = null,
    val productShortDescription: String? = null,
    val productFullDescription: String? = null,
    val productStatus: String? = null,
    val productDeliveryType: String? = null
    //val productGallery: ArrayList<String>? = null
)
