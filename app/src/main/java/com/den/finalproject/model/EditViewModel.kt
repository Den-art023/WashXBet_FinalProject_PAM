package com.den.finalproject.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.finalproject.repositori.RepositoriPelanggan
import com.den.finalproject.ui.halaman.DestinasiEdit
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriPelanggan: RepositoriPelanggan
) : ViewModel() {
    var pelangganUiState by mutableStateOf(UIStatePelanggan())
        private set
    private val itemId: Int = checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init {
        viewModelScope.launch {
            pelangganUiState = repositoriPelanggan.getPelangganStream(itemId)
                .filterNotNull()
                .first()
                .toUiStatePelanggan(true)
        }
    }

    suspend fun updatePelanggan() {
        if (validasiInput(pelangganUiState.detailPelanggan)) {
            repositoriPelanggan.updatePelanggan(pelangganUiState.detailPelanggan.toPelanggan())
        } else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailPelanggan: DetailPelanggan) {
        pelangganUiState = UIStatePelanggan(
            detailPelanggan = detailPelanggan,
            isEntryValid = validasiInput(detailPelanggan)
        )
    }

    private fun validasiInput(uiState: DetailPelanggan = pelangganUiState.detailPelanggan): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank() && treatment.isNotBlank()
        }
    }
}