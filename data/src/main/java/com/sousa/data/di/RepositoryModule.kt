package com.sousa.data.di

import com.sousa.data.repository.AuthRepositoryImpl
import com.sousa.data.repository.ProfileRepositoryImpl
import com.sousa.data.repository.WorkRepositoryImpl
import com.sousa.domain.repository.AuthRepository
import com.sousa.domain.repository.ProfileRepository
import com.sousa.domain.repository.WorkRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<WorkRepository> { WorkRepositoryImpl(get()) }
}