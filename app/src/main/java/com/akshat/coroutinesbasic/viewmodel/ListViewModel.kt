package com.akshat.coroutinesbasic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshat.coroutinesbasic.model.CountriesService
import com.akshat.coroutinesbasic.model.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel: ViewModel() {
    val countriesService = CountriesService.getCountiresServoce()
    val job: Job?= null
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val response = countriesService.getCountries()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                }
                else{
                    onError("Error ${response.message()}")
                }
            }
        }
    }


    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }
}