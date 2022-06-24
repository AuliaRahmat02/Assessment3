package org.d3if2074.kontak.model

import org.d3if2074.kontak.db.KontakEntity

fun KontakEntity.inputKontak(): Kontak{
    val nama = nama
    val nomor =nomor

    return Kontak(nama, nomor)
}