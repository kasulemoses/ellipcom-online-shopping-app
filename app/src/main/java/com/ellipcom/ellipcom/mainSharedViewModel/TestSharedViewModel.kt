package com.ellipcom.ellipcom.mainSharedViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ellipcom.ellipcom.model.DetailPdtModel

class TestSharedViewModel : ViewModel() {

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

    /*
    * storing and passing the item view id
    * of the construction to the main construction recyclerview fragment
    * */
    private var _constructionViewId = MutableLiveData("constId")
    var constructionViewId:LiveData<String> = _constructionViewId
    fun savingConstructionViewId(viewId:String){
        _constructionViewId.value = viewId
    }


    private var _educationViewId = MutableLiveData("educId")
    var educationViewId:LiveData<String> = _educationViewId
    fun savingEducationViewId(viewId:String){
        _educationViewId.value = viewId
    }

    private var _pdtDetails = MutableLiveData<DetailPdtModel>()
    var pdtDetails:LiveData<DetailPdtModel> = _pdtDetails
    fun savingPdtDetails(pd:DetailPdtModel){

        _pdtDetails.value = pd

    }

    //--------------------------------------------------------------------------------------------/
    /*
    * saving the sub cat name to be transfer to their
    * corresponding display fragment
    * */
    private var _electronicsSubDetails = MutableLiveData("name")
    var electronicSubDetails:LiveData<String> = _electronicsSubDetails
    fun savingSubPdtDetails(sPd:String){
        _electronicsSubDetails.value = sPd

    }
    private var _phonesAndTabletsSubDetails = MutableLiveData("1")
    var phonesAndTabletsSubDetails:LiveData<String> = _phonesAndTabletsSubDetails
    fun savingPhoneAndTabletSubPdtDetails(sPd1:String){
        _phonesAndTabletsSubDetails.value = sPd1

    }

    private var _homeAndOfficeSubDetails = MutableLiveData("2")
    var homeAndOfficeSubDetails:LiveData<String> = _homeAndOfficeSubDetails
    fun savingHomeAndOfficeSubPdtDetails(sPd2:String){
        _homeAndOfficeSubDetails.value = sPd2

    }
    private var _computingSubDetails = MutableLiveData("3")
    var computingSubDetails:LiveData<String> = _computingSubDetails
    fun savingComputingSubPdtDetails(sPd3:String){
        _computingSubDetails.value = sPd3

    }
    private var _furnitureSubDetails = MutableLiveData("4")
    var furnitureSubDetails:LiveData<String> = _furnitureSubDetails
    fun savingFurnitureSubPdtDetails(sPd4:String){
        _furnitureSubDetails.value = sPd4

    }

/*
    * saving the sub cat name to be transfer to their
    * corresponding display fragment
    * */

    //--------------------------------------------------------------------------------------/




    private var _foodAndDrinksViewId = MutableLiveData("foodId")
    var foodAndDrinksViewId:LiveData<String> = _foodAndDrinksViewId
    fun savingFoodAndDrinksViewId(viewId:String){
        _foodAndDrinksViewId.value = viewId
    }

    private var _houseHoldSubCatViewId = MutableLiveData("householdId")
    var householdSubCatViewId:LiveData<String> = _houseHoldSubCatViewId
    fun savingHouseholdSubCatViewId(viewId:String){
        _houseHoldSubCatViewId.value = viewId
    }

    private var _medicalSubCatViewId = MutableLiveData("medicalId")
    var medicalSubCatViewId:LiveData<String> = _medicalSubCatViewId
    fun savingMedicalSubCatViewId(viewId:String){
        _medicalSubCatViewId.value = viewId
    }




}