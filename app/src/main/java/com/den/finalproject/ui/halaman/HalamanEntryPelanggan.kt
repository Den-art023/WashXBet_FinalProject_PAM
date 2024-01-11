package com.den.finalproject.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.den.finalproject.R
import com.den.finalproject.model.DetailPelanggan
import com.den.finalproject.model.InputViewModel
import com.den.finalproject.model.PenyediaViewModel
import com.den.finalproject.model.UIStatePelanggan
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar
import kotlinx.coroutines.launch

object DestinasiEntry : DestinasiNavigasi {
    override val route = ""
    override val titleRes = R.string.entry_pelanggan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PelangganScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InputViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WashTopAppBar(
                title = stringResource(DestinasiEntry.titleRes), canNavigateBack = true,
                navigateUp = navigateBack, scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        EntryPelangganBody(
            uiStatePelanggan = viewModel.uiStatePelanggan,
            onPelangganValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.savePelanggan()
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
fun EntryPelangganBody(
    uiStatePelanggan: UIStatePelanggan,
    onPelangganValueChange: (DetailPelanggan) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        FormInputPelanggan(detailPelanggan = uiStatePelanggan.detailPelanggan,
            onValueChange = onPelangganValueChange,
            onSaveClick = onSaveClick,
            uiStatePelanggan = uiStatePelanggan,
            pilihanTreatment = listOf(
                stringResource(id = R.string.pilihan1),
                stringResource(id = R.string.pilihan2),
                stringResource(id = R.string.pilihan3),
                stringResource(id = R.string.pilihan4)
            ),
            onSelectionChanged = { uiStatePelanggan.detailPelanggan.treatment })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputPelanggan(
    detailPelanggan: DetailPelanggan,
    modifier: Modifier = Modifier,
    onValueChange: (DetailPelanggan) -> Unit = {},
    enabled: Boolean = true,
    pilihanTreatment: List<String>,
    onSelectionChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    uiStatePelanggan: UIStatePelanggan,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = modifier.padding(top = 24.dp))
        OutlinedTextField(
            value = detailPelanggan.nama,
            onValueChange = { onValueChange(detailPelanggan.copy(nama = it)) },
            label = { Text(stringResource(id = R.string.nama)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailPelanggan.telpon,
            onValueChange = { onValueChange(detailPelanggan.copy(telpon = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.telpon)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled
        )
        OutlinedTextField(
            value = detailPelanggan.alamat, onValueChange = {
                onValueChange(
                    detailPelanggan.copy(
                        alamat = it
                    )
                )
            },
            label = { Text(stringResource(R.string.alamat)) }, singleLine = true,
            modifier = Modifier.fillMaxWidth(), enabled = enabled
        )
        Column(modifier.fillMaxWidth()) {
            pilihanTreatment.forEach { item ->
                Row(modifier = Modifier.selectable(
                    selected = detailPelanggan.treatment == item, onClick = {
                        onValueChange(detailPelanggan.copy(treatment = item))
                        onSelectionChanged(item)
                    }
                ), verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = detailPelanggan.treatment == item,
                        onClick = {
                            onValueChange(detailPelanggan.copy(treatment = item))
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
        }
        Button(
            onClick = onSaveClick,
            enabled = uiStatePelanggan.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "PESAN")
        }
    }
}