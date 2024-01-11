package com.den.finalproject.ui.halaman


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.den.finalproject.R
import com.den.finalproject.navigasi.DestinasiNavigasi
import com.den.finalproject.navigasi.WashTopAppBar

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navigateToRent: () -> Unit,
    navigateToCar: () -> Unit
) {
    Scaffold(
        topBar = {
            WashTopAppBar(
                title = stringResource(id = DestinasiHome.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        DashBoard(
            onPelangganClick = navigateToRent,
            onKendaraanClick = navigateToCar,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    modifier: Modifier = Modifier,
    onPelangganClick: () -> Unit,
    onKendaraanClick: () -> Unit,
) {
    val img_kendaraan = painterResource(id = R.drawable.kendaraan)
    val img_pelanggan = painterResource(id = R.drawable.pelanggan)
    val logo = painterResource(id = R.drawable.logo)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .background(color = Color(android.graphics.Color.parseColor("#f5f5f5")))
            .padding(
                dimensionResource(id = R.dimen.padding_extra_large)
            )
            .padding(top = dimensionResource(id = R.dimen.padding_extra_large)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = logo, contentDescription = null, Modifier.requiredSize(150.dp))
        Text(
            text = "WashXBet",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(75.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#4a4848")),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Card(onClick = onPelangganClick) {
                        Image(
                            painter = img_pelanggan,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_medium))
                        .height(40.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#2accf5")),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_pelanggan),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#f5f5f5"))
                    )
                }

            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 12.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(
                        top = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(75.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#7868e5")),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Card(onClick = onKendaraanClick) {
                        Image(
                            painter = img_kendaraan,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_medium))
                        .height(40.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#2accf5")),
                            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                        ),
                ) {
                    Text(
                        text = stringResource(id = R.string.data_kendaraan),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(android.graphics.Color.parseColor("#f5f5f5"))
                    )
                }


            }
        }
    }
}