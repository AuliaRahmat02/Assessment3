package org.d3if2074.kontak.daftarkontak

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2074.kontak.db.KontakDao

class KontakViewModelFactory(
    private val db: KontakDao
    ): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KontakViewModel::class.java)){
            return KontakViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}