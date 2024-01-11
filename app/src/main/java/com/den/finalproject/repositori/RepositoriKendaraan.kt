package com.den.finalproject.repositori

import com.den.finalproject.data.Kendaraan
import kotlinx.coroutines.flow.Flow

interface RepositoriKendaraan {
    fun getAllKendaraanStream(): Flow<List<Kendaraan>>
    fun getKendaraanStream(id: Int): Flow<Kendaraan?>
    suspend fun insertKendaraan(kendaraan: Kendaraan)
    suspend fun deleteKendaraan(kendaraan: Kendaraan)
    suspend fun updateKendaraan(kendaraan: Kendaraan)
}