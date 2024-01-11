package com.den.finalproject.ui.halaman.kendaraan

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.den.finalproject.R
import com.den.finalproject.model.PenyediaViewModel
import com.den.finalproject.model.kendaraan.EditKendaraanViewModel
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar
import kotlinx.coroutines.launch

object DestinasiEditKendaraan : DestinasiNavigasi {
    override val route = "edit_kendaraan"
    override val titleRes = R.string.update_kendaraan
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KendaraanEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditKendaraanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WashTopAppBar(
                title = stringResource(DestinasiEditKendaraan.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryKendaraanBody(
            uiStateKendaraan = viewModel.KendaraanUiState,
            onKendaraanValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKendaraan()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}