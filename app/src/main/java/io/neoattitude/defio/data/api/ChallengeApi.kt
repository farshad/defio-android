package io.neoattitude.defio.data.api

import io.neoattitude.defio.data.model.Challenge
import retrofit2.Response
import retrofit2.http.POST

interface ChallengeApi {
    @POST("challenge/list")
    suspend fun getChallenges(): Response<List<Challenge>>
}