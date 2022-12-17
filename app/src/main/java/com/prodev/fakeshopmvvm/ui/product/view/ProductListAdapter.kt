package com.prodev.fakeshopmvvm.ui.product.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prodev.fakeshopmvvm.api.product.model.ProductInfo

class ProductListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var adapterItems = listOf<AdapterItem>()

    var listOfProductInfo: List<ProductInfo>? = null
        set(value) {
            field = value
            updateAdapterItems()
        }

    @SuppressLint("NotifyDataSetChanged")
    @Synchronized
    private fun updateAdapterItems() {
        val adapterItem = mutableListOf<AdapterItem>()
        listOfProductInfo?.forEach { productInfo ->
            adapterItem.add(AdapterItem.ProductInfoItem(productInfo))
        }
        this.adapterItems = adapterItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.ProductInfoViewItemType.ordinal -> {
                ProductListViewHolder(ProductInfoView(context))
            }
            else -> throw IllegalAccessException("Unsupported ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapterItem = adapterItems.getOrNull(position) ?: return
        when (adapterItem) {
            is AdapterItem.ProductInfoItem -> {
                (holder.itemView as ProductInfoView).bind(adapterItem.productInfo)
            }
        }
    }

    override fun getItemCount(): Int {
        return adapterItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return adapterItems[position].type
    }

    private class ProductListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    sealed class AdapterItem(val type: Int) {
        data class ProductInfoItem(val productInfo: ProductInfo) :
            AdapterItem(ViewType.ProductInfoViewItemType.ordinal)
    }

    private enum class ViewType {
        ProductInfoViewItemType
    }
}