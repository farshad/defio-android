package io.neoattitude.defio.di

import io.neoattitude.defio.data.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get(), get()) }
}