package com.ellipcom.ellipcom.model

data class DeliveryModel(
    val addressId: String? = null,
    val paymentMethod: String? = null,
    val shippingMethod: String? = null,
    val totalAmountToBePaid: String? = null
)
