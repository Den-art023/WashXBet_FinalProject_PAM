package com.den.finalproject.model.kendaraan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.finalproject.repositori.RepositoriKendaraan
import com.den.finalproject.ui.halaman.kendaraan.DestinasiEditKendaraan
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditKendaraanViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKendaraan: RepositoriKendaraan
) : ViewModel() {
    var KendaraanUiState by mutableStateOf(UIStateKendaraan())
        private set
    private val itemId: Int = checkNotNull(savedStateHandle[DestinasiEditKendaraan.itemIdArg])

    init {
        viewModelScope.launch {
            KendaraanUiState = repositoriKendaraan
                .getKendaraanStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateKendaraan(true)
        }
    }

    suspend fun updateKendaraan() {
        if (validasiInput(KendaraanUiState.detailKendaraan)) {
            repositoriKendaraan.updateKendaraan(KendaraanUiState.detailKendaraan.toKendaraan())
        } else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailKendaraan: DetailKendaraan) {
        KendaraanUiState = UIStateKendaraan(
            detailKendaraan = detailKendaraan,
            isEntryValid = validasiInput(detailKendaraan)
        )
    }

    private fun validasiInput(uiState: DetailKendaraan = KendaraanUiState.detailKendaraan): Boolean {
        return with(uiState) {
            merk.isNotBlank() && plat.isNotBlank()
        }
    }
}