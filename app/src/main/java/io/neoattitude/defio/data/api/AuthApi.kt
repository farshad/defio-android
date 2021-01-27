package io.neoattitude.defio.data.api

import io.neoattitude.defio.data.model.AuthCriteria
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/signin")
    fun signIn(@Body authCriteria: AuthCriteria): Call<String>
}