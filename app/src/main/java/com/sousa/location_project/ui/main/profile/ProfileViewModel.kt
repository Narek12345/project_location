package com.sousa.location_project.ui.main.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sousa.domain.repository.AuthRepository
import com.sousa.domain.repository.ProfileRepository
import com.sousa.entity.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val application: Application
) : AndroidViewModel(application) {

    val profile: MutableLiveData<Profile> = MutableLiveData()

    init {
        getProfile()
    }
    fun getProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository
                .getProfile()
                .catch {  }
                .collect{
                    profile.postValue(it)
                }
        }
    }

}