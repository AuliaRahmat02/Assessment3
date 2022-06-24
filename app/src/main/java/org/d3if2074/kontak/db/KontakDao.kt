package org.d3if2074.kontak.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.d3if2074.kontak.model.Kontak


@Dao
interface KontakDao {
    @Insert
    fun insert(kontak: KontakEntity)

    @Query("SELECT * FROM kontak ORDER BY id DESC")
    fun getLastKonta():LiveData<List<KontakEntity>>

    @Query("DELETE FROM kontak")
    fun clearData()
}