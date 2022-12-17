package com.prodev.fakeshopmvvm.api.product

import com.prodev.fakeshopmvvm.api.product.model.ProductInfo
import io.reactivex.Single

class ProductRepository(private val productRetrofitAPI: ProductRetrofitAPI) {

    fun getProductList() : Single<List<ProductInfo>> {
        return productRetrofitAPI.getListOfProduct()
    }
}