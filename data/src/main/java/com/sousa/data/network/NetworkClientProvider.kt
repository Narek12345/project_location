package com.sousa.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.newapp.data.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val OKHTTP_READ_TIMEOUT = 120L
private const val OKHTTP_CONNECT_TIMEOUT = 60L

class NetworkClientProvider() {

    fun <T> provideApiService(retrofit: Retrofit, service: Class<T>): T = retrofit.create(service)

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .client(provideOkHttp())
            .build()
    }

    private fun provideGson(): Gson = GsonBuilder().create()

    private fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = getLoggingLevel()
            })
            .readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun getLoggingLevel(): HttpLoggingInterceptor.Level {
        return HttpLoggingInterceptor.Level.BODY
//        if (BuildConfig.DEBUG)
//            HttpLoggingInterceptor.Level.BODY
//        else
//            HttpLoggingInterceptor.Level.NONE
    }
}