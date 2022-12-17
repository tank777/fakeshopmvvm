package com.prodev.fakeshopmvvm.di

import android.app.Application
import android.content.Context
import com.prodev.fakeshopmvvm.api.ApiModule
import com.prodev.fakeshopmvvm.application.FakeShopApplication
import com.prodev.fakeshopmvvm.network.NetworkModule
import com.prodev.fakeshopmvvm.ui.product.ProductListActivity
import com.prodev.fakeshopmvvm.viewmodelmodule.FakeShopViewModelModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeShopAppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }
}

@Singleton
@Component(
    modules = [
        FakeShopAppModule::class,
        NetworkModule::class,
        ApiModule::class,
        FakeShopViewModelModule::class
    ]
)

interface FakeShopAppComponent : BaseAppComponent {
    fun inject(app: FakeShopApplication)
    fun inject(productListActivity: ProductListActivity)
}
