package com.den.finalproject.model.pelanggan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.finalproject.data.Pelanggan
import com.den.finalproject.repositori.RepositoriPelanggan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomePelangganViewModel(private val repositoriPelanggan: RepositoriPelanggan) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val pelangganUiState: StateFlow<PelangganUiState> =
        repositoriPelanggan.getAllPelangganStream().filterNotNull()
            .map { PelangganUiState(listPelanggan = it.toList()) }
            .stateIn(
                scope = viewModelScope, started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PelangganUiState()
            )
}

data class PelangganUiState(
    val listPelanggan: List<Pelanggan> = listOf()
)