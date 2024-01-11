package com.den.finalproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pelanggan::class], version = 1, exportSchema = false)
abstract class DatabaseCustomer : RoomDatabase() {
    abstract fun PelangganDao(): PelangganDao

    companion object {
        @Volatile
        private var Instance: DatabaseCustomer? = null
        fun getDatabase(context: Context): DatabaseCustomer {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseCustomer::class.java,
                    "pelanggan_database"
                )
                    .build()
                    .also { Instance = it }
            })
        }
    }
}