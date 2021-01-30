package io.neoattitude.defio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.data.model.Challenge
import io.neoattitude.defio.data.repository.ChallengeRepository
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val repository: ChallengeRepository
) : BaseViewModel() {

    val challenges: MutableLiveData<Resource<List<Challenge>>> = MutableLiveData()
    val isTokenExist: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun getChallenges() {
        viewModelScope.launch {
            challenges.postValue(Resource.Loading())
            try {
                val response = repository.getApiChallenges()
                challenges.postValue(handleResponse(response))
            } catch (t: Throwable) {
                challenges.postValue(Resource.Error(t.message!!))
            }
        }
    }
}
