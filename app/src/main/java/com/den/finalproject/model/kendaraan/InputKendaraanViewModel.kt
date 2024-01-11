package com.den.finalproject.model.kendaraan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.den.finalproject.data.Kendaraan
import com.den.finalproject.repositori.RepositoriKendaraan

class InputKendaraanViewModel(private val repositoriKendaraan: RepositoriKendaraan) : ViewModel() {
    var uiStateKendaraan by mutableStateOf(UIStateKendaraan())
        private set

    private fun validasiInput(uiState: DetailKendaraan = uiStateKendaraan.detailKendaraan): Boolean {
        return with(uiState) {
            merk.isNotBlank() && plat.isNotBlank()
        }
    }

    fun updateUiState(detailKendaraan: DetailKendaraan) {
        uiStateKendaraan = UIStateKendaraan(
            detailKendaraan = detailKendaraan,
            isEntryValid = validasiInput(detailKendaraan)
        )
    }

    suspend fun saveKendaraan() {
        if (validasiInput()) {
            repositoriKendaraan.insertKendaraan(uiStateKendaraan.detailKendaraan.toKendaraan())
        }
    }
}

data class UIStateKendaraan(
    val detailKendaraan: DetailKendaraan = DetailKendaraan(),
    val isEntryValid: Boolean = false
)

data class DetailKendaraan(
    val id: Int = 0,
    val merk: String = "",
    val plat: String = "",
)

fun DetailKendaraan.toKendaraan(): Kendaraan = Kendaraan(
    id = id,
    merk = merk,
    plat = plat,

    )

fun Kendaraan.toUiStateKendaraan(isEntryValid: Boolean = false): UIStateKendaraan =
    UIStateKendaraan(
        detailKendaraan = this.toDetailKendaraan(),
        isEntryValid = isEntryValid
    )

fun Kendaraan.toDetailKendaraan(): DetailKendaraan = DetailKendaraan(
    id = id,
    merk = merk,
    plat = plat
)