package com.ellipcom.ellipcom.model

data class ProductData(
    val mainCategory: String? = null,
    val subCategory: String? = null,
    val childCategory: String? = null,
    val pdtImageUrl: String? = null,
    val pdtName: String? = null,
    val pdtId: String? = null,
    val pdtPrice: String? = null,
    val pdtRegularPrice: String? = null,
    val pdtColor: String? = null,
    val pdtBrand: String? = null,
    val pdtDeliveryStatus: String? = null,
    val pdtSize: String? = null,
    val pdtCatId: String? = null,
    val pdtSku: String? = null,
    val pdtShorDescription: String? = null,
    val pdtFullDescription: String? = null,
    val pdtDateCreated: String? = null,
    val galleryImageUrl:ArrayList<String>? = null
)
