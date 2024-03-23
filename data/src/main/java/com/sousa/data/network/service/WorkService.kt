package com.sousa.data.network.service

import android.content.Context
import com.sousa.data.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WorkService(
    private val context: Context,
    private val apiService: ApiService
) {
    fun startWork(): Flow<Boolean> {
        return flow {
            val params = hashMapOf(
                "userId" to Prefs().userId
            )

            val response = apiService.startWork(params)

            if(response.status != "Success"){
                error(response.responceText.toString())
            }


            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    fun endWork(): Flow<Boolean> {
        return flow {
            val params = hashMapOf(
                "userId" to Prefs().userId
            )

            val response = apiService.startWork(params)

            if(response.status != "Success"){
                error(response.responceText.toString())
            }


            emit(true)
        }.flowOn(Dispatchers.IO)
    }

    fun sendWifi(): Flow<Boolean> {
        return flow {
            val params = hashMapOf(
                "userId" to Prefs().userId
                //TODO
            )

            val response = apiService.sendLocation(params)

            if(response.status != "Success"){
                error(response.responceText.toString())
            }


            emit(true)
        }.flowOn(Dispatchers.IO)
    }
}