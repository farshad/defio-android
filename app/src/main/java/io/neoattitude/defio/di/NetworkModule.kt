package io.neoattitude.defio.di

import com.google.gson.GsonBuilder
import io.neoattitude.defio.BuildConfig
import io.neoattitude.defio.data.api.AuthApi
import io.neoattitude.defio.data.api.util.RetrofitClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single(createdAtStart = true) { provideRetrofit() }
    single { createApiService<AuthApi>(get()) }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient().create()
            )
        )
        .client(RetrofitClient().setInterceptor().build())
        .build();
}

inline fun <reified T> createApiService(retrofit: Retrofit): T = retrofit.create(T::class.java)