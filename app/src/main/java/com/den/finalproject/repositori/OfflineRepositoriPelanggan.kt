package com.den.finalproject.repositori

import com.den.finalproject.data.Kendaraan
import com.den.finalproject.data.KendaraanDao
import com.den.finalproject.data.Pelanggan
import com.den.finalproject.data.PelangganDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriPelanggan(private val pelangganDao: PelangganDao) : RepositoriPelanggan {
    override fun getAllPelangganStream(): Flow<List<Pelanggan>> = pelangganDao.getAllPelanggan()
    override fun getPelangganStream(id: Int): Flow<Pelanggan?> = pelangganDao.getPelanggan(id)
    override suspend fun insertPelanggan(pelanggan: Pelanggan) = pelangganDao.insert(pelanggan)
    override suspend fun deletePelanggan(pelanggan: Pelanggan) = pelangganDao.delete(pelanggan)
    override suspend fun updatePelanggan(pelanggan: Pelanggan) = pelangganDao.update(pelanggan)
}

class OfflineRepositoriKendaraan(private val kendaraanDao: KendaraanDao) : RepositoriKendaraan {
    override fun getAllKendaraanStream(): Flow<List<Kendaraan>> = kendaraanDao.getAllKendaraan()
    override fun getKendaraanStream(id: Int): Flow<Kendaraan?> = kendaraanDao.getKendaraan(id)
    override suspend fun insertKendaraan(kendaraan: Kendaraan) = kendaraanDao.insert(kendaraan)
    override suspend fun deleteKendaraan(kendaraan: Kendaraan) = kendaraanDao.delete(kendaraan)
    override suspend fun updateKendaraan(kendaraan: Kendaraan) = kendaraanDao.update(kendaraan)
}