package com.sousa.data.repository

import com.sousa.data.Prefs
import com.sousa.domain.repository.ProfileRepository
import com.sousa.entity.Profile
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProfileRepositoryImpl : ProfileRepository {
    override fun getProfile(): Flow<Profile> {
        return flow {
            emit(Profile(Prefs().fio,Prefs().age,Prefs().city,Prefs().address,Prefs().urlPhoto,Prefs().userId))
        }.flowOn(IO)
    }
}