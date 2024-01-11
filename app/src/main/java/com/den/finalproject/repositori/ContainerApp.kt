package com.den.finalproject.repositori

import android.content.Context
import com.den.finalproject.data.DatabaseCustomer

interface ContainerApp {
    val repositoriPelanggan: RepositoriPelanggan
}

class ContainerDataApp(private val context: Context) : ContainerApp {
    override val repositoriPelanggan: RepositoriPelanggan by lazy {
        OfflineRepositoriPelanggan(DatabaseCustomer.getDatabase(context).PelangganDao())
    }
}