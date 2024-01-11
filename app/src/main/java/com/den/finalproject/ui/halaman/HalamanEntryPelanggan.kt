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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.den.finalproject.R
import com.den.finalproject.model.DetailPelanggan
import com.den.finalproject.model.UIStatePelanggan

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