package com.sousa.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login(registrationCode:String): Flow<Boolean>

    fun registration(fio:String,age:String,city:String,address:String,urlPhoto:String,registrationCode:String): Flow<Boolean>

    fun getTermsOfUse():Flow<String>
}