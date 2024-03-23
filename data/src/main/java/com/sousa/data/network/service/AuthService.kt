package com.sousa.data.network.service

import android.content.Context
import com.sousa.data.Prefs
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthService(
    private val context: Context,
    private val apiService: ApiService
) {

    fun login(registrationCode:String):Flow<Boolean> {
        return flow {
            val params = hashMapOf(
                "registrationCode" to registrationCode
            )

            val response = apiService.login(params)

            if(response.status != "Success"){
                error("Неверный регистрационный код")
            }

            Prefs().userId = response.userId.toString()

            emit(true)
        }.flowOn(IO)
    }

    fun registration(fio:String,age:String,city:String,address:String,urlPhoto:String,registrationCode:String):Flow<Boolean> {
        return flow {
            val params = hashMapOf(
                "userFio" to fio,
                "userAge" to age,
                "userCity" to city,
                "userAdress" to address,
                "userPhoto" to urlPhoto,
                "registrationCode" to registrationCode
            )

            Prefs().fio = fio
            Prefs().age = age
            Prefs().city = city
            Prefs().address = address
            Prefs().urlPhoto = urlPhoto
            val response = apiService.registration(params)

            if(response.status != "Success"){
                error(response.responceText.toString())
            }

            emit(true)
        }.flowOn(IO)
    }

    fun getTermsOfUse():Flow<String>{
        return flow {
            val response = apiService.getTermsOfUse()

            if(response.status != "Success"){
                error(response.responceText.toString())
            }

            emit(response.responceText.toString())
        }.flowOn(IO)
    }

}