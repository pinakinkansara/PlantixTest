package com.pinakin.www.plantixtest.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinakin.www.plantixtest.model.Profile
import com.pinakin.www.plantixtest.repository.ProfileRepository
import kotlinx.coroutines.launch

/**
 * ViewModel class act as a layer between UI & Repository
 *
 * In real app this view model will take repository as constructor parameter.
 * I will leverage dependency injection framework like Dagger Hilt.
 */
class ProfileListViewModel : ViewModel() {

    // FIXME: this will get injected via dependency injection
    private val repository = ProfileRepository()

    private val _profiles = MutableLiveData<List<Profile>>()
    val profiles: LiveData<List<Profile>>
        get() = _profiles

    init {

        viewModelScope.launch {
            _profiles.value = repository.getProfiles()
        }
    }
}