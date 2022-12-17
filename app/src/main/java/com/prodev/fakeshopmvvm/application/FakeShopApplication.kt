package com.prodev.fakeshopmvvm.application

import android.app.Activity
import android.app.Application
import com.prodev.fakeshopmvvm.di.DaggerFakeShopAppComponent
import com.prodev.fakeshopmvvm.di.FakeShopAppComponent
import com.prodev.fakeshopmvvm.di.FakeShopAppModule

class FakeShopApplication : Application() {

    companion object {

        operator fun get(app: Application): FakeShopApplication {
            return app as FakeShopApplication
        }

        operator fun get(activity: Activity): FakeShopApplication {
            return activity.application as FakeShopApplication
        }

        lateinit var component: FakeShopAppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        try {
            component = DaggerFakeShopAppComponent.builder()
                .fakeShopAppModule(FakeShopAppModule(this))
                .build()
            component.inject(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}