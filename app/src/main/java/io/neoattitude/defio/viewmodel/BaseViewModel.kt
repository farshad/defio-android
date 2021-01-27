package io.neoattitude.defio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.neoattitude.defio.util.Resource
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    inline fun <reified T> handleResponse(response: Response<T>): Resource<T>? {
        return when (response.isSuccessful) {
            true -> response.body()?.let { Resource.Success(it) }
            false -> Resource.Error(response.message())
        }
    }
}