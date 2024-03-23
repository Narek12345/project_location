package com.newapp.post_script.ui.app

import android.app.Application
import com.sousa.location_project.di.KoinLauncher

class App: Application() {


    override fun onCreate() {
        super.onCreate()

        KoinLauncher.init(this)
    }
}