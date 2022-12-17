package com.prodev.fakeshopmvvm.viewmodelmodule

import com.prodev.fakeshopmvvm.api.product.ProductRepository
import com.prodev.fakeshopmvvm.ui.product.viewmodel.ProductListViewModel
import dagger.Module
import dagger.Provides

@Module
class FakeShopViewModelModule {

    @Provides
    fun provideProductListViewModel(
        productRepository: ProductRepository
    ): ProductListViewModel {
        return ProductListViewModel(
            productRepository
        )
    }
}