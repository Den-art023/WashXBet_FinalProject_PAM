package com.den.finalproject.repositori

import android.content.Context
import com.den.finalproject.data.DatabaseCustomer

interface ContainerApp {
    val repositoriPelanggan: RepositoriPelanggan

    val repositoriKendaraan: RepositoriKendaraan
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoriPelanggan: RepositoriPelanggan by lazy {
        OfflineRepositoriPelanggan(DatabaseCustomer.getDatabase(context).PelangganDao())
    }

    override val repositoriKendaraan: RepositoriKendaraan by lazy {
        OfflineRepositoriKendaraan(DatabaseCustomer.getDatabase(context).KendaraanDao())
    }
}