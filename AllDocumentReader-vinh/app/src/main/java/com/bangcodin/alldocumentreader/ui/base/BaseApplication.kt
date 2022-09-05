package com.bangcodin.alldocumentreader.ui.base


import android.app.Application
import com.bangcodin.alldocumentreader.utils.setAppLocale

open class BaseApplication : Application() {

    companion object {
        lateinit var app: BaseApplication

        fun getInstance(): BaseApplication {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        setAppLocale(this, "vi")
    }
}