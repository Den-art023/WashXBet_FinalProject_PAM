package com.den.finalproject.model.pelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.den.finalproject.data.Pelanggan
import com.den.finalproject.repositori.RepositoriPelanggan

class InputViewModel(private val repositoriPelanggan: RepositoriPelanggan) : ViewModel() {
    var uiStatePelanggan by mutableStateOf(UIStatePelanggan())
        private set

    private fun validasiInput(uiState: DetailPelanggan = uiStatePelanggan.detailPelanggan): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank() && treatment.isNotBlank()
        }
    }

    fun updateUiState(detailPelanggan: DetailPelanggan) {
        uiStatePelanggan = UIStatePelanggan(
            detailPelanggan = detailPelanggan,
            isEntryValid = validasiInput(detailPelanggan)
        )
    }

    suspend fun savePelanggan() {
        if (validasiInput()) {
            repositoriPelanggan.insertPelanggan(uiStatePelanggan.detailPelanggan.toPelanggan())
        }
    }
}

data class UIStatePelanggan(
    val detailPelanggan: DetailPelanggan = DetailPelanggan(),
    val isEntryValid: Boolean = false
)

data class DetailPelanggan(
    val id: Int = 0, val nama: String = "",
    val alamat: String = "", val telpon: String = "",
    val treatment: String = "",
)

fun DetailPelanggan.toPelanggan(): Pelanggan = Pelanggan(
    id = id, nama = nama,
    alamat = alamat, telpon = telpon,
    treatment = treatment,
)

fun Pelanggan.toUiStatePelanggan(isEntryValid: Boolean = false): UIStatePelanggan =
    UIStatePelanggan(
        detailPelanggan = this.toDetailPelanggan(), isEntryValid = isEntryValid
    )

fun Pelanggan.toDetailPelanggan(): DetailPelanggan = DetailPelanggan(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon,
    treatment = treatment,
)