package com.prodev.fakeshopmvvm.api

import com.prodev.fakeshopmvvm.api.product.ProductRepository
import com.prodev.fakeshopmvvm.api.product.ProductRetrofitAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideProductRetrofitAPI(retrofit: Retrofit): ProductRetrofitAPI {
        return retrofit.create(ProductRetrofitAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideProductRepository(productRetrofitAPI: ProductRetrofitAPI): ProductRepository {
        return ProductRepository(productRetrofitAPI)
    }
}