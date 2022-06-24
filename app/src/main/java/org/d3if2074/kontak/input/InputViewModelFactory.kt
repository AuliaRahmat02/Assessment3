package org.d3if2074.kontak.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2074.kontak.db.KontakDao

class InputViewModelFactory(
    private val db: KontakDao
):ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InputViewModel::class.java)){
            return InputViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}