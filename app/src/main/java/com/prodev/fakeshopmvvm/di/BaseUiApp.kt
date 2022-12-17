package com.prodev.fakeshopmvvm.di

import android.app.Application

interface BaseAppComponent {
    fun inject(app: Application)
}