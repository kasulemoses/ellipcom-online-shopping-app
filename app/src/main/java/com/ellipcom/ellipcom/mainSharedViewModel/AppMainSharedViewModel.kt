package com.ellipcom.ellipcom.mainSharedViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppMainSharedViewModel : ViewModel() {

    //working on the product id
    private var _productId = MutableLiveData("1")
    val productId: LiveData<String> = _productId

    fun productId(pdtId: String) {
        _productId.value = pdtId
    }

    //working on the product grand total to be paid by the user
    private var _productGrandTotal = MutableLiveData("0")
    val productGrandTotal: LiveData<String> = _productGrandTotal

    fun productGrandTotal(pdtGrandTotal: String) {
        _productGrandTotal.value = pdtGrandTotal
    }

    //working on the product grand total to be paid by the user
    private var _totalMoneyToPaid = MutableLiveData("0")
    val finalTotalMoney: LiveData<String> = _totalMoneyToPaid

    fun saveTotalOverallAmount(totalOverallAmount: String) {
        _totalMoneyToPaid.value = totalOverallAmount
    }

    //working on the product grand total to be paid by the user
    private var _addressId = MutableLiveData("0")
    val addressId: LiveData<String> = _addressId

    fun saveCustomerAddressId(customerAddressId: String) {
        _addressId.value = customerAddressId
    }

    //working on the payment method for the customer
    private var _paymentMethod = MutableLiveData("pay on delivery")
    val paymentMethod: LiveData<String> = _paymentMethod

    fun saveCustomerPaymentMethod(payMethod: String) {
        _paymentMethod.value = payMethod
    }

    //working on the payment method for the customer
    private var _shippingMethod = MutableLiveData("standard shipping")
    val shippingMethod: LiveData<String> = _shippingMethod

    fun saveCustomerShippingMethod(shipMethod: String) {
        _shippingMethod.value = shipMethod
    }


}