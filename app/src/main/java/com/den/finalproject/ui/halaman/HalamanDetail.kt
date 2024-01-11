package com.den.finalproject.ui.halaman

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.den.finalproject.R
import com.den.finalproject.data.Pelanggan

@Composable
fun ItemDetails(
    pelanggan: Pelanggan,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
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
                labelResId = R.string.nama,
                itemDetail = pelanggan.nama, modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            ItemDetailsRow(
                labelResId = R.string.telpon,
                itemDetail = pelanggan.telpon, modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            ItemDetailsRow(
                labelResId = R.string.alamat,
                itemDetail = pelanggan.alamat,
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
            ItemDetailsRow(
                labelResId = R.string.treatment,
                itemDetail = pelanggan.treatment,
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }
    }
}

@Composable
private fun ItemDetailsRow(
    @StringRes
    labelResId: Int,
    itemDetail: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResId))
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = itemDetail,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /*DoNothing*/ },
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