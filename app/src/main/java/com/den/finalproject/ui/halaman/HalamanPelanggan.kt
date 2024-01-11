package com.den.finalproject.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.den.finalproject.data.Pelanggan

@Composable
fun ListPelanggan(
    itemPelanggan: List<Pelanggan>,
    modifier: Modifier = Modifier,
    onItemClick: (Pelanggan) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemPelanggan, key = { it.id })
        { buy ->
            DataPelanggan(
                pelanggan = buy,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(buy) }
            )
        }
    }
}

@Composable
fun DataPelanggan(
    pelanggan: Pelanggan,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Nama : " + pelanggan.nama,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text =
                "No HP : " + pelanggan.telpon,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}