package com.sousa.data.repository

import com.sousa.data.network.service.AuthService
import com.sousa.data.network.service.WorkService
import com.sousa.domain.repository.WorkRepository
import com.sousa.entity.WifiDots
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WorkRepositoryImpl(
    private val workService: WorkService
) : WorkRepository {
    override fun startWork(): Flow<Boolean> {
        return workService.startWork()
    }

    override fun endWork(): Flow<Boolean> {
        return workService.endWork()
    }

    override fun sendWifi(wifiDots: List<WifiDots>): Flow<Boolean> {
        return workService.sendWifi()
    }
}