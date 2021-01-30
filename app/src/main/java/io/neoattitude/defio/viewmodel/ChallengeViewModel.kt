package io.neoattitude.defio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.data.model.AuthCriteria
import io.neoattitude.defio.data.model.Challenge
import io.neoattitude.defio.data.repository.AuthRepository
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val challenges: MutableLiveData<Resource<Challenge>> = MutableLiveData()
    val isTokenExist: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun getChallenges(idToken: String?) {
        viewModelScope.launch {
            challenges.postValue(Resource.Loading())
            try {
                val response = authRepository.signIn(AuthCriteria(idToken))
                challenges.postValue(handleResponse(response))
            } catch (t: Throwable) {
                challenges.postValue(Resource.Error(t.message!!))
            }
        }
    }
}
