package com.sousa.location_project.di

import com.sousa.location_project.ui.auth.AuthViewModel
import com.sousa.location_project.ui.main.profile.ProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModel = module {
    viewModel { AuthViewModel(get(),androidApplication()) }
    viewModel { ProfileViewModel(get(),androidApplication()) }
}