package io.neoattitude.defio.data.api.util

import io.neoattitude.defio.data.dao.TokenDao
import io.neoattitude.defio.data.entity.Token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class RetrofitClient(private val tokenDao: TokenDao) {
    private val timeout = 40L

    fun setInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain: Interceptor.Chain ->
            var request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + getToken())
                .build()

            val auth = request.headers()["remove-token"]
            if (auth != null) {
                request = request.newBuilder().removeHeader("Authorization").build()
                request = request.newBuilder().removeHeader("remove-token").build()
            }

            chain.proceed(request)
        }
        return httpClient.build()
    }

    private fun getToken(): String? {
        val token: Token? = tokenDao.fetchLast()
        return token?.value
    }
}