package io.neoattitude.defio.data.repository

import io.neoattitude.defio.data.api.ChallengeApi

class ChallengeRepository(
    private val api: ChallengeApi,
) {
    suspend fun getApiChallenges() = api.getChallenges()
}