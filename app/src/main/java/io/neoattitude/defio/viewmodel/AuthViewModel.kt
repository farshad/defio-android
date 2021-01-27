package io.neoattitude.defio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.data.model.AuthCriteria
import io.neoattitude.defio.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val token: MutableLiveData<String> = MutableLiveData()

    fun signIn(idToken: String?) {
        viewModelScope.launch {
            val response = authRepository.signIn(AuthCriteria(idToken))
            token.postValue(response.body())
        }
    }
}