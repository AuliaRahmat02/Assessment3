package org.d3if2074.kontak.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kontak")
data class KontakEntity (
        @PrimaryKey(autoGenerate = true)
        var id : Long = 0L,
        var nama :String,
        var nomor : String
        )