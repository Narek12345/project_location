package com.sousa.data.di

import com.sousa.data.network.service.ApiService
import com.sousa.data.network.service.AuthService
import com.sousa.data.network.NetworkClientProvider
import com.sousa.data.network.service.WorkService
import org.koin.dsl.module

val networkModule = module {

    single { get<NetworkClientProvider>().provideRetrofit() }

    single {
        get<NetworkClientProvider>().provideApiService(
            get(),
            ApiService::class.java
        )
    }

    single { AuthService(get(),get()) }

    single { WorkService(get(),get()) }

    single { NetworkClientProvider() }

}