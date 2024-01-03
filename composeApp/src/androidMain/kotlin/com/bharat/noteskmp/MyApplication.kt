package com.bharat.noteskmp

import android.app.Application
import android.content.Context
import di.initComposeKoin
import org.koin.dsl.module


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initComposeKoin {
            modules(module {
                single<Context> { this@MyApplication }
            })
        }
    }

}