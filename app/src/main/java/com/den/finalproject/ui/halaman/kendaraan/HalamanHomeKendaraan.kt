package com.den.finalproject.ui.halaman.kendaraan

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.den.finalproject.R
import com.den.finalproject.data.Kendaraan
import com.den.finalproject.model.PenyediaViewModel
import com.den.finalproject.model.kendaraan.KendaraanViewModel
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar

object DestinasiKendaran : DestinasiNavigasi {
    override val route = "home_kendaraan"
    override val titleRes = R.string.kendaraan
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KendaraanScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: KendaraanViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (Int) -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WashTopAppBar(
                title = stringResource(DestinasiKendaran.titleRes), canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.entry_pelanggan)
                )
            }
        },
    ) { innerPadding ->
        val uiStateKendaraan by viewModel.kendaraanUiState.collectAsState()
        BodyKendaraan(
            itemKendaraan = uiStateKendaraan.listKendaraan,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            onDetailClick = onDetailClick
        )
    }
}

@Composable
fun BodyKendaraan(
    itemKendaraan: List<Kendaraan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(8.dp),            // modifier = modifier.size(280.dp, 240.dp)
            modifier = Modifier.padding(
                10.dp,
                5.dp,
                10.dp,
                10.dp
            ),            //set card elevation of the card
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
                        text = "Data Kendaraan",
                        style = MaterialTheme.typography.titleMedium, maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Silahkan isi data kendaraan",
                        //maxLines = 1,
                        //overflow = TextOverflow.Ellipsis,
                        // style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
        if (itemKendaraan.isEmpty()) {
            Text(
                text = stringResource(id = R.string.desc), textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
        } else {
            ListKendaraan(
                itemKendaraan = itemKendaraan, modifier = Modifier.padding(horizontal = 8.dp),
                onItemClick = { onDetailClick(it.id) })
        }
    }
}

@Composable
fun ListKendaraan(
    itemKendaraan: List<Kendaraan>,
    modifier: Modifier = Modifier,
    onItemClick: (Kendaraan) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemKendaraan, key = { it.id })
        { car ->
            DataKendaraan(
                kendaraan = car,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(car) }
            )
        }
    }
}

@Composable
fun DataKendaraan(
    kendaraan: Kendaraan,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Merk : " + kendaraan.merk,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Nomor Kendaraan : " + kendaraan.plat,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}