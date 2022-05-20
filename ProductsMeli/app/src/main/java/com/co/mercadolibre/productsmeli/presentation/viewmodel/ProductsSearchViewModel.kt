package com.co.mercadolibre.productsmeli.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.co.mercadolibre.productsmeli.R
import com.co.mercadolibre.productsmeli.data.model.ProductDetailResponse
import com.co.mercadolibre.productsmeli.data.model.ProductsSearchResponse
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import com.co.mercadolibre.productsmeli.data.model.Result
import com.co.mercadolibre.productsmeli.domain.Const
import com.co.mercadolibre.productsmeli.domain.Utils
import com.co.mercadolibre.productsmeli.domain.usecase.GetProductDetailUseCase

class ProductsSearchViewModel(
    private val app: Application,
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase
) :
    AndroidViewModel(app) {

    val productsList: MutableLiveData<Resource<ProductsSearchResponse>> = MutableLiveData()
    val productDetail: MutableLiveData<Resource<ProductDetailResponse>> = MutableLiveData()

    private var searchedList: MutableList<Result> = mutableListOf()
    var offset: Int = 0
    private var lastSearch: String = ""


    /**
     * Refresh de products list reactive variable and set results for view products list.
     * @param productQuery Id for search product's details
     * @param context context of view for show error message thought of Resources strings file
     */
    fun getProducts(productQuery: String, context: Context) =
        viewModelScope.launch(Dispatchers.IO) {
            productsList.postValue(Resource.Loading())
            try {
                if (Utils.isNetworkAvailable(app)) {
                    val apiResult = getProductsUseCase.execute(productQuery, offset)
                    productsList.postValue(apiResult)
                    lastSearch = productQuery
                    offset += Const.PRODUCTS_LIMIT_PAGINATION
                    searchedList = apiResult.data?.results ?: mutableListOf()
                } else {
                    productsList.postValue(Resource.Error(context.getString(R.string.internet_not_available)))
                }
            } catch (e: Exception) {
                productsList.postValue(Resource.Error(e.message.toString()))
            }
        }

    /**
     * Calls get products details services and update the reactive variable
     * @param productId Id for search product's details
     * @param context context of view for show error message thought of Resources strings file
     */
    fun getProductDetail(productId: String, context: Context) =
        viewModelScope.launch(Dispatchers.IO) {
            productDetail.postValue(Resource.Loading())
            try {
                if (Utils.isNetworkAvailable(app)) {
                    val apiResult = getProductDetailUseCase.execute(productId)
                    productDetail.postValue(apiResult)
                } else {
                    productsList.postValue(Resource.Error(context.getString(R.string.internet_not_available)))
                }
            } catch (e: Exception) {
                productsList.postValue(Resource.Error(e.message.toString()))
            }
        }


    /**
     * Calls the search products services for add other page of results to pagination
     */
    fun getNextPage() = viewModelScope.launch(Dispatchers.IO) {
        productsList.postValue(Resource.Loading())
        try {
            if (Utils.isNetworkAvailable(app)) {
                val apiResult = getProductsUseCase.execute(lastSearch, offset)
                searchedList.addAll(apiResult.data?.results ?: mutableListOf())
                apiResult.data!!.results = searchedList
                productsList.postValue(apiResult)
                offset += Const.PRODUCTS_LIMIT_PAGINATION
            } else {
                productsList.postValue(Resource.Error("Internet its not available"))
            }
        } catch (e: Exception) {
            productsList.postValue(Resource.Error(e.message.toString()))
        }
    }

    /**
     * separates main features by lines breaks. also add bullet point for each line
     * @return text with products main features concatenated
     */
    fun concatFeaturesList(): String {
        var features = ""
        val featuresList = productDetail.value?.data?.mainFeatures ?: emptyList()
        for (feature in featuresList) {
            features += "● ${feature.text} \n\n"
        }
        return features
    }

}