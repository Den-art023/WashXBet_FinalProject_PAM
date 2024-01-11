package com.den.finalproject.ui.halaman

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.den.finalproject.R
import com.den.finalproject.data.Pelanggan

@Composable
fun BodyPelanggan(
    itemPelanggan: List<Pelanggan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(8.dp),
            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(
                10.dp,
                5.dp,
                10.dp,
                10.dp
            ),
            //set card elevation of the card
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        ) {
            Column(modifier = Modifier.clickable(onClick = { }))
            {
                Image(
                    painter = painterResource(R.drawable.baner),
                    contentDescription = null, // decorative
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Gaming",
                        style = MaterialTheme.typography.titleMedium, maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Silahkan Beli Diamond Murah Meriah Hanya Di Toko Kami",
                        //maxLines = 1,
                        //overflow = TextOverflow.Ellipsis,
                        // style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
        if (itemPelanggan.isEmpty()) {
            Text(
                text = stringResource(id = R.string.desc), textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        } else {
            ListPelanggan(
                itemPelanggan = itemPelanggan, modifier = Modifier.padding(horizontal = 8.dp),
                onItemClick = { onDetailClick(it.id) })
        }
    }
}

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