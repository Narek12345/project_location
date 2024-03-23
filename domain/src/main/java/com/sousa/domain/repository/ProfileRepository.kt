package com.sousa.domain.repository

import com.sousa.entity.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(): Flow<Profile>
}