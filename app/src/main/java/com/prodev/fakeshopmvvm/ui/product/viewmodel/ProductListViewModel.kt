package com.prodev.fakeshopmvvm.ui.product.viewmodel

import com.prodev.fakeshopmvvm.api.product.ProductRepository
import com.prodev.fakeshopmvvm.api.product.model.ProductInfo
import com.prodev.fakeshopmvvm.base.BaseViewModel
import com.prodev.fakeshopmvvm.extension.subscribeOnIoAndObserveOnMainThread
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ProductListViewModel(
    private val productRepository: ProductRepository
) : BaseViewModel() {
    private val productListStateSubject: PublishSubject<ProductListViewState> =
        PublishSubject.create()
    val productListStates: Observable<ProductListViewState> = productListStateSubject.hide()

    fun loadProductList() {
        productRepository.getProductList()
            .doOnSubscribe {
                productListStateSubject.onNext(ProductListViewState.LoadingState(true))
            }
            .doAfterTerminate {
                productListStateSubject.onNext(ProductListViewState.LoadingState(false))
            }.subscribeOnIoAndObserveOnMainThread({
                productListStateSubject.onNext(ProductListViewState.ProductInfoListState(it))
            }, {
                productListStateSubject.onNext(
                    ProductListViewState.ErrorMessage(
                        it.localizedMessage ?: ""
                    )
                )
            }).autoDispose()
    }
}

sealed class ProductListViewState {
    data class ErrorMessage(val errorMessage: String) : ProductListViewState()
    data class LoadingState(val isLoading: Boolean) : ProductListViewState()
    data class ProductInfoListState(val listOfProductInfo: List<ProductInfo>) :
        ProductListViewState()
}