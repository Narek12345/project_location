package com.sousa.domain.repository

import com.sousa.entity.WifiDots
import kotlinx.coroutines.flow.Flow

interface WorkRepository {
    fun startWork(): Flow<Boolean>
    fun endWork(): Flow<Boolean>

    fun sendWifi(wifiDots: List<WifiDots>): Flow<Boolean>
}