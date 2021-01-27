package io.neoattitude.defio.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
}