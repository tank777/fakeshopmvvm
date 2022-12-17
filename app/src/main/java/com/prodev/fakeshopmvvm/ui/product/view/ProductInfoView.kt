package com.prodev.fakeshopmvvm.ui.product.view

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.prodev.fakeshopmvvm.R
import com.prodev.fakeshopmvvm.api.product.model.ProductInfo
import com.prodev.fakeshopmvvm.base.ConstraintLayoutWithLifecycle
import com.prodev.fakeshopmvvm.databinding.ProductInfoViewBinding

class ProductInfoView(context: Context) : ConstraintLayoutWithLifecycle(context) {

    private var binding: ProductInfoViewBinding? = null

    init {
        initUi()
    }

    private fun initUi() {
        val view = View.inflate(context, R.layout.product_info_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        binding = ProductInfoViewBinding.bind(view)

    }

    fun bind(productInfo: ProductInfo) {
        binding?.apply {
            Glide.with(context)
                .load(productInfo.image)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(productImageView)
            productPriceTextView.text = "$${productInfo.price}"
            productRatingTextView.rating = productInfo.rating?.rate?.toFloat() ?: 0.0f
            productRatingCountTextView.text = productInfo.rating?.count.toString()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}