package com.den.finalproject.ui.halaman.kendaraan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.den.finalproject.R
import com.den.finalproject.model.PenyediaViewModel
import com.den.finalproject.model.kendaraan.DetailKendaraan
import com.den.finalproject.model.kendaraan.InputKendaraanViewModel
import com.den.finalproject.model.kendaraan.UIStateKendaraan
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar
import kotlinx.coroutines.launch

object DestinasiEntryKendaraan : DestinasiNavigasi {
    override val route = "entry_kendaraan"
    override val titleRes = R.string.entry_kendaraan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KendaraanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InputKendaraanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WashTopAppBar(
                title = stringResource(DestinasiEntryKendaraan.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        EntryKendaraanBody(
            uiStateKendaraan = viewModel.uiStateKendaraan,
            onKendaraanValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveKendaraan()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
        )
    }
}

@Composable
fun EntryKendaraanBody(
    uiStateKendaraan: UIStateKendaraan,
    onKendaraanValueChange: (DetailKendaraan) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        FormInputKendaraan(
            detailKendaraan = uiStateKendaraan.detailKendaraan,
            onValueChange = onKendaraanValueChange,
            onSaveClick = onSaveClick,
            uiStateKendaraan = uiStateKendaraan,
            onSelectionChanged = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKendaraan(
    detailKendaraan: DetailKendaraan,
    modifier: Modifier = Modifier,
    onValueChange: (DetailKendaraan) -> Unit = {},
    enabled: Boolean = true,
    onSelectionChanged: (String) -> Unit,
    onSaveClick: () -> Unit, uiStateKendaraan: UIStateKendaraan,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = modifier.padding(top = 24.dp))
        OutlinedTextField(
            value = detailKendaraan.merk,
            onValueChange = { onValueChange(detailKendaraan.copy(merk = it)) },
            label = { Text(stringResource(id = R.string.merk)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailKendaraan.plat,
            onValueChange = { onValueChange(detailKendaraan.copy(plat = it)) },
            label = { Text(stringResource(R.string.plat)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateKendaraan.isEntryValid, shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Confirm")
        }
    }
}