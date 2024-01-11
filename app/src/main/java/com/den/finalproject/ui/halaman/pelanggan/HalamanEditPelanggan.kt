package com.den.finalproject.ui.halaman.pelanggan

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.den.finalproject.R
import com.den.finalproject.model.pelanggan.EditPelangganViewModel
import com.den.finalproject.model.PenyediaViewModel
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar
import kotlinx.coroutines.launch

object DestinasiEdit : DestinasiNavigasi {
    override val route = "edit"
    override val titleRes = R.string.update
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPelangganViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WashTopAppBar(
                title = stringResource(DestinasiEdit.titleRes),
                canNavigateBack = true, navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryPelangganBody(
            uiStatePelanggan = viewModel.pelangganUiState,
            onPelangganValueChange = viewModel::updateUiState, onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePelanggan()
                    navigateBack()
                }
            }, modifier = Modifier.padding(innerPadding)
        )
    }
}