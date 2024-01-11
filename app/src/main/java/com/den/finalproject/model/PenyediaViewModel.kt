package com.den.finalproject.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.den.finalproject.ui.WashXBetApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            PelangganViewModel(washui().container.repositoriPelanggan)
        }
        initializer {
            InputViewModel(washui().container.repositoriPelanggan)
        }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                washui().container.repositoriPelanggan
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(), washui().container.repositoriPelanggan
            )
        }
    }
}

fun CreationExtras.washui(): WashXBetApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WashXBetApp)