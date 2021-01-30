package io.neoattitude.defio.di

import io.neoattitude.defio.viewmodel.AuthViewModel
import io.neoattitude.defio.viewmodel.ChallengeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { ChallengeViewModel(get()) }
}