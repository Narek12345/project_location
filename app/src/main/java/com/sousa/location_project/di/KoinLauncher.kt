package com.sousa.location_project.di

import android.app.Application
import com.sousa.data.di.networkModule
import com.sousa.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinLauncher {

    private fun getAppModules():List<Module>{
        return listOf(
            networkModule,
            repositoryModule,
            viewModelModel
        )
    }

    fun init(application: Application){
        startKoin {
            androidContext(application)
            modules(getAppModules())
        }
    }
}