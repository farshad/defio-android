package io.neoattitude.defio.data.api

import io.neoattitude.defio.data.model.AuthCriteria
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("remove-token: true")
    @POST("auth/signin")
    suspend fun signIn(@Body authCriteria: AuthCriteria): Response<String>
}