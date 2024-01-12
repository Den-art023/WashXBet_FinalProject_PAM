package com.den.finalproject.model.kendaraan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.finalproject.data.Kendaraan
import com.den.finalproject.repositori.RepositoriKendaraan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeKendaraaanViewModel(private val repositoriKendaraan: RepositoriKendaraan) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val kendaraanUiState: StateFlow<KendaraanUiState> =
        repositoriKendaraan.getAllKendaraanStream().filterNotNull()
            .map { KendaraanUiState(listKendaraan = it.toList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KendaraanUiState()
            )
}

data class KendaraanUiState(
    val listKendaraan: List<Kendaraan> = listOf()
)