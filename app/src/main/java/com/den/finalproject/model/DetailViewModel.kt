package com.den.finalproject.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.den.finalproject.repositori.RepositoriPelanggan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriPelanggan: RepositoriPelanggan
) : ViewModel() {
    private val pelangganId: Int =
        checkNotNull(savedStateHandle[DestinasiDetailPemesanan.beliIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriPelanggan.getPelangganStream(pelangganId).filterNotNull().map {
            ItemDetailsUiState(detailPelanggan = it.toDetailPelanggan())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )

    suspend fun deleteItem() {
        repositoriPelanggan.deletePelanggan(uiState.value.detailPelanggan.toPelanggan())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailPelanggan: DetailPelanggan = DetailPelanggan(),
)