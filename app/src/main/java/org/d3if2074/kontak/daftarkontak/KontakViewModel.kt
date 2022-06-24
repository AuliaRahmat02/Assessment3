package org.d3if2074.kontak.daftarkontak

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2074.kontak.db.KontakDao

class KontakViewModel (private val db:KontakDao) : ViewModel(){
    val data =db.getLastKonta()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}