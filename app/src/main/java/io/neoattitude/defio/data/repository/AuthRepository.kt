package io.neoattitude.defio.data.repository

import androidx.lifecycle.LiveData
import io.neoattitude.defio.data.api.AuthApi
import io.neoattitude.defio.data.dao.TokenDao
import io.neoattitude.defio.data.entity.Token
import io.neoattitude.defio.data.model.AuthCriteria

class AuthRepository(
    private val authApi: AuthApi,
    private val tokenDao: TokenDao
) {
    suspend fun signIn(authCriteria: AuthCriteria) = authApi.signIn(authCriteria)

    suspend fun insertToken(token: String) {
        tokenDao.insert(Token(null, token))
    }

    fun fetchAll(): LiveData<List<Token>>? {
        return tokenDao.fetchAll()
    }

    fun fetchLast(): Token? {
        return tokenDao.fetchLast()
    }
}