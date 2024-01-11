package com.den.finalproject.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface KendaraanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(kendaraan: Kendaraan)

    @Update
    suspend fun update(kendaraan: Kendaraan)

    @Delete
    suspend fun delete(kendaraan: Kendaraan)

    @Query("SELECT * from Kendaraan WHERE id = :id")
    fun getKendaraan(id: Int): Flow<Kendaraan>

    @Query("SELECT * from Kendaraan ORDER BY merk ASC")
    fun getAllKendaraan(): Flow<List<Kendaraan>>
}