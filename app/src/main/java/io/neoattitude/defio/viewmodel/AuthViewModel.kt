package io.neoattitude.defio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.neoattitude.defio.data.entity.Token
import io.neoattitude.defio.data.repository.AuthRepository

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var token: LiveData<String>? = null

    val tokenList: LiveData<List<Token>> = authRepository.fetchAll()!!

    fun signIn(idToken: String?) {

//        authRepository.signIn(AuthCriteria(idToken)).enqueue(object : retrofit2.Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                viewModelScope.launch(Dispatchers.IO) {
//                    if (response.isSuccessful) {
//                        authRepository.insertToken(response.body().toString())
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.d(">>>>>>>>>>", "onFailure: d")
//            }
//
//        })
    }
}