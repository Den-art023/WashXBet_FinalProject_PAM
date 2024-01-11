package com.den.finalproject.ui.halaman.kendaraan

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.den.finalproject.R
import com.den.finalproject.data.Kendaraan
import com.den.finalproject.model.PenyediaViewModel
import com.den.finalproject.model.kendaraan.DetailsKendaraanViewModel
import com.den.finalproject.model.kendaraan.KendaraanDetailsUiState
import com.den.finalproject.model.kendaraan.toKendaraan
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar
import kotlinx.coroutines.launch

object DestinasiDetailKendaraan : DestinasiNavigasi {
    override val route = "kendaraan_details"
    override val titleRes = R.string.detail_kendaraan
    const val beliIdArg = "itemId"
    val routeWithArgs = "$route/{$beliIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KendaraanDetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigasiBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsKendaraanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            WashTopAppBar(
                title = stringResource(DestinasiDetailKendaraan.titleRes),
                canNavigateBack = true,
                navigateUp = navigasiBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditItem(uiState.value.detailKendaraan.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.update)
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            kendaraanDetailsUiState = uiState.value, onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigasiBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun ItemDetailsBody(
    kendaraanDetailsUiState: KendaraanDetailsUiState,
    onDelete: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails(
            kendaraan = kendaraanDetailsUiState.detailKendaraan.toKendaraan(),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDelete()
            }, onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ItemDetails(
    kendaraan: Kendaraan,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ItemDetailsRow(
                labelResId = R.string.merk, itemDetail = kendaraan.merk,
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            ItemDetailsRow(
                labelResId = R.string.plat,
                itemDetail = kendaraan.plat, modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }
    }
}

@Composable
private fun ItemDetailsRow(
    @StringRes labelResId: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResId))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text =
            itemDetail, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*DoNothing*/ },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.confirm))
            }
        })
}