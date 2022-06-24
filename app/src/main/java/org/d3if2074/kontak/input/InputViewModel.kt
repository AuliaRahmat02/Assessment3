package org.d3if2074.kontak.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2074.kontak.db.KontakDao
import org.d3if2074.kontak.db.KontakEntity
import org.d3if2074.kontak.model.Kontak
import org.d3if2074.kontak.model.inputKontak

class InputViewModel(private val db: KontakDao): ViewModel() {

    private val kontak = MutableLiveData<Kontak?>()

    fun inputKontak(nama : String, nomor: String){
        val dataKontak = KontakEntity(
            nama=nama,
            nomor = nomor
        )

        kontak.value = dataKontak.inputKontak()

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.insert(dataKontak)
            }
        }
    }

    fun getKontak():LiveData<Kontak?> = kontak
}