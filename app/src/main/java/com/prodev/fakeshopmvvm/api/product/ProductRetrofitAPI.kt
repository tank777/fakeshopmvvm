package com.prodev.fakeshopmvvm.api.product

import com.prodev.fakeshopmvvm.api.product.model.ProductInfo
import io.reactivex.Single
import retrofit2.http.GET

interface ProductRetrofitAPI {

    @GET("products")
    fun getListOfProduct() : Single<List<ProductInfo>>
}