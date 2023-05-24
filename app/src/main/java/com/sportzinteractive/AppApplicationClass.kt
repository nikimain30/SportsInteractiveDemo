package com.sportzinteractive

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import java.lang.ref.WeakReference

class AppApplicationClass : Application()  {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        wApp!!.clear()
        wApp = WeakReference(this@AppApplicationClass)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        private var wApp: WeakReference<AppApplicationClass>? =
            WeakReference<AppApplicationClass>(null)
        val context: Context
            get() {
                val app = if (null != wApp) wApp!!.get() else AppApplicationClass()
                return if (app != null) app.applicationContext else AppApplicationClass().applicationContext
            }
    }
}

