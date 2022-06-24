package org.d3if2074.kontak.kontakpenting

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2074.kontak.MainActivity
import org.d3if2074.kontak.model.KontakPenting
import org.d3if2074.kontak.network.ApiStatus
import org.d3if2074.kontak.network.KontakApi
import org.d3if2074.kontak.network.UpdateWorker
import java.lang.Exception
import java.util.concurrent.TimeUnit

class PentingViewModel : ViewModel() {
    private val data = MutableLiveData<List<KontakPenting>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retreiveData()
    }

    private fun retreiveData(){
        viewModelScope.launch ( Dispatchers.IO){
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(KontakApi.service.getKontakPenting())
                status.postValue(ApiStatus.SUCCESS)
            }catch (e: Exception){
                Log.d("KontakView", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            } }
    }

    fun getData(): LiveData<List<KontakPenting>> = data

    fun getStatus() :LiveData<ApiStatus> = status

    fun scheduleAdapter(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}