package com.sousa.data.network.service

import com.sousa.data.network.models.LoginResponse
import com.sousa.data.network.models.SimpleResponse
import retrofit2.http.Body
import retrofit2.http.POST


private const val URL_LOGIN = "user_authorisation"
private const val URL_REGISTRATION = "user_register"
private const val URL_START_WORK = "user_begin_work"
private const val URL_STOP_WORK = "user_end_work"
private const val URL_USER_TERMS_OF_USE = "user_terms_of_use"
private const val URL_SEND_WIFI = "user_location"
interface ApiService {

    @POST(URL_LOGIN)
    suspend fun login(@Body params: Map<String, @JvmSuppressWildcards Any>): LoginResponse

    @POST(URL_REGISTRATION)
    suspend fun registration(@Body params: Map<String, @JvmSuppressWildcards Any>): SimpleResponse

    @POST(URL_START_WORK)
    suspend fun startWork(@Body params: Map<String, @JvmSuppressWildcards Any>): SimpleResponse

    @POST(URL_STOP_WORK)
    suspend fun stopWork(@Body params: Map<String, @JvmSuppressWildcards Any>): SimpleResponse


    @POST(URL_USER_TERMS_OF_USE)
    suspend fun getTermsOfUse(): SimpleResponse

    @POST(URL_SEND_WIFI)
    suspend fun sendLocation(@Body params: Map<String, @JvmSuppressWildcards Any>): SimpleResponse
}