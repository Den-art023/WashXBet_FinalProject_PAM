package com.den.finalproject.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.den.finalproject.WashXBetApp
import com.den.finalproject.model.pelanggan.DetailsViewModel
import com.den.finalproject.model.pelanggan.EditPelangganViewModel
import com.den.finalproject.model.pelanggan.InputViewModel
import com.den.finalproject.model.pelanggan.HomePelangganViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomePelangganViewModel(washui().container.repositoriPelanggan)
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
            EditPelangganViewModel(
                createSavedStateHandle(), washui().container.repositoriPelanggan
            )
        }
    }
}

fun CreationExtras.washui(): WashXBetApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WashXBetApp)