package com.den.finalproject.repositori

import com.den.finalproject.data.Pelanggan
import kotlinx.coroutines.flow.Flow

interface RepositoriPelanggan {
    fun getAllPelangganStream(): Flow<List<Pelanggan>>
    fun getPelangganStream(id: Int): Flow<Pelanggan?>
    suspend fun insertPelanggan(pelanggan: Pelanggan)
    suspend fun deletePelanggan(pelanggan: Pelanggan)
    suspend fun updatePelanggan(pelanggan: Pelanggan)
}