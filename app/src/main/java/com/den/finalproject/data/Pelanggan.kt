package com.den.finalproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblPelanggan")
class Pelanggan (
@PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama: String,
    val alamat: String,
    val telpon: String,
    val treatment: String

)