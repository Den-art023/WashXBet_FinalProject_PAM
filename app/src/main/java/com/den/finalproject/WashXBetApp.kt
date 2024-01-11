package com.den.finalproject

import android.app.Application
import com.den.finalproject.repositori.ContainerApp
import com.den.finalproject.repositori.ContainerDataApp

class WashXBetApp : Application() {
    lateinit var container: ContainerApp
    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}