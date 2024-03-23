package com.sousa.data.repository

import com.sousa.data.network.service.AuthService
import com.sousa.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {

    override fun login(registrationCode:String): Flow<Boolean> {
        return authService.login(registrationCode)
    }

    override fun registration(
        fio: String,
        age: String,
        city: String,
        address: String,
        urlPhoto: String,
        registrationCode: String
    ): Flow<Boolean> {
        return authService.registration(fio, age, city, address, urlPhoto, registrationCode)
    }

    override fun getTermsOfUse(): Flow<String> {
        return authService.getTermsOfUse()
    }

}