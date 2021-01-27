package io.neoattitude.defio.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.neoattitude.defio.data.api.exception.ServerError
import io.neoattitude.defio.util.Resource
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    inline fun <reified T> handleResponse(response: Response<T>): Resource<T>? {
        return when (response.isSuccessful) {
            true -> response.body()?.let { Resource.Success(it) }
            false -> {
                return if (response.message().isEmpty()) {
                    val serverError: ServerError =
                        Gson().fromJson(response.errorBody()?.string(), ServerError::class.java)
                    serverError.message?.let { Resource.Error(it) }
                } else {
                    Resource.Error(response.message())
                }
            }
        }
    }
}