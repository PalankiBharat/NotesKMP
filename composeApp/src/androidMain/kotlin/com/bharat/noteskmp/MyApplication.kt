package com.bharat.noteskmp

import android.app.Application
import di.initKoin

class MyApplication: Application() {
    override fun onCreate() {
        initKoin {

        }
        super.onCreate()
    }
}