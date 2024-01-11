package com.den.finalproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kendaraan")
class Kendaraan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val merk: String,
    val plat: String,
)