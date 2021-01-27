package io.neoattitude.defio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.neoattitude.defio.data.model.AuthCriteria
import io.neoattitude.defio.data.repository.AuthRepository
import io.neoattitude.defio.util.Resource
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val token: MutableLiveData<Resource<String>> = MutableLiveData()
    val isTokenExist: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun signIn(idToken: String?) {
        viewModelScope.launch {
            token.postValue(Resource.Loading())
            try {
                val response = authRepository.signIn(AuthCriteria(idToken))
                token.postValue(handleResponse(response))
            } catch (t: Throwable) {
                token.postValue(Resource.Error(t.message!!))
            }
        }
    }

    fun insertToken(token: String) = viewModelScope.launch { authRepository.insertToken(token) }

    fun deleteAll() = viewModelScope.launch { authRepository.deleteAll() }

    fun checkTokenExist() {
        viewModelScope.launch {
            isTokenExist.postValue(authRepository.isTokenExist().value)
        }
    }
}