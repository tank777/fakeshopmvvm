package com.prodev.fakeshopmvvm.api.product.model

import com.google.gson.annotations.SerializedName

data class ProductInfo(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("price")
    val price: Any? = null,

    @field:SerializedName("rating")
    val rating: Rating? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("category")
    val category: String? = null
)

data class Rating(

    @field:SerializedName("rate")
    val rate: Double? = null,

    @field:SerializedName("count")
    val count: Int? = null
)