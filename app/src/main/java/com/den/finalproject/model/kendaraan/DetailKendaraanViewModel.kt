package com.den.finalproject.model.kendaraan

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.finalproject.repositori.RepositoriKendaraan
import com.den.finalproject.ui.halaman.kendaraan.DestinasiDetailKendaraan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsKendaraanViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKendaraan: RepositoriKendaraan
) : ViewModel() {
    private val KendaraanId: Int =
        checkNotNull(savedStateHandle[DestinasiDetailKendaraan.beliIdArg])
    val uiState: StateFlow<KendaraanDetailsUiState> =
        repositoriKendaraan.getKendaraanStream(KendaraanId).filterNotNull()
            .map {
                KendaraanDetailsUiState(detailKendaraan = it.toDetailKendaraan())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = KendaraanDetailsUiState()
            )

    suspend fun deleteItem() {
        repositoriKendaraan.deleteKendaraan(uiState.value.detailKendaraan.toKendaraan())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class KendaraanDetailsUiState(
    val outOfStock: Boolean = true, val detailKendaraan: DetailKendaraan = DetailKendaraan(),
)