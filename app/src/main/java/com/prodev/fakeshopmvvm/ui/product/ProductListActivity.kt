package com.prodev.fakeshopmvvm.ui.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.prodev.fakeshopmvvm.application.FakeShopApplication
import com.prodev.fakeshopmvvm.base.BaseActivity
import com.prodev.fakeshopmvvm.base.ViewModelFactory
import com.prodev.fakeshopmvvm.databinding.ActivityProductListBinding
import com.prodev.fakeshopmvvm.extension.getViewModelFromFactory
import com.prodev.fakeshopmvvm.extension.subscribeAndObserveOnMainThread
import com.prodev.fakeshopmvvm.ui.product.view.ProductListAdapter
import com.prodev.fakeshopmvvm.ui.product.viewmodel.ProductListViewModel
import com.prodev.fakeshopmvvm.ui.product.viewmodel.ProductListViewState
import javax.inject.Inject

class ProductListActivity : BaseActivity() {
    private lateinit var binding: ActivityProductListBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<ProductListViewModel>
    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var productListAdapter: ProductListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FakeShopApplication.component.inject(this)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productListViewModel = getViewModelFromFactory(viewModelFactory)
        listenToViewModel()
        listenToViewEvent()
        productListViewModel.loadProductList()
    }

    private fun listenToViewEvent() {
        productListAdapter = ProductListAdapter(this)
        binding.productRecyclerview.apply {
            adapter = productListAdapter
        }
    }

    private fun listenToViewModel() {
        productListViewModel.productListStates.subscribeAndObserveOnMainThread {
            when (it) {
                is ProductListViewState.ErrorMessage -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
                is ProductListViewState.LoadingState -> {
                    binding.progressBar.isVisible = it.isLoading
                }
                is ProductListViewState.ProductInfoListState -> {
                    productListAdapter.listOfProductInfo = it.listOfProductInfo
                }
            }
        }.autoDispose()
    }
}