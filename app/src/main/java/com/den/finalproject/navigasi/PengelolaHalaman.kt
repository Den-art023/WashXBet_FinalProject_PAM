package com.den.finalproject.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.den.finalproject.R
import com.den.finalproject.ui.halaman.pelanggan.DestinasiDetailPemesanan
import com.den.finalproject.ui.halaman.pelanggan.DestinasiEdit
import com.den.finalproject.ui.halaman.pelanggan.DestinasiEntry
import com.den.finalproject.ui.halaman.DestinasiHome
import com.den.finalproject.ui.halaman.HomePage
import com.den.finalproject.ui.halaman.kendaraan.DestinasiDetailKendaraan
import com.den.finalproject.ui.halaman.kendaraan.DestinasiEditKendaraan
import com.den.finalproject.ui.halaman.kendaraan.DestinasiEntryKendaraan
import com.den.finalproject.ui.halaman.kendaraan.DestinasiKendaran
import com.den.finalproject.ui.halaman.kendaraan.KendaraanEditScreen
import com.den.finalproject.ui.halaman.kendaraan.KendaraanScreen
import com.den.finalproject.ui.halaman.pelanggan.DestinasiPelanggan
import com.den.finalproject.ui.halaman.pelanggan.DetailsScreen
import com.den.finalproject.ui.halaman.pelanggan.ItemEditScreen
import com.den.finalproject.ui.halaman.pelanggan.PelangganScreen

@Composable
fun WashXBetApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WashTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        }
    )
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomePage(
                navigateToRent = { navController.navigate(DestinasiPelanggan.route) },
                navigateToCar = { navController.navigate(DestinasiKendaran.route) }
            )
        }
        composable(DestinasiPelanggan.route) {
            PelangganScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { navController.navigate("${DestinasiDetailPemesanan.route}/$it") })
        }
        composable(DestinasiEntry.route) {
            PelangganScreen(
                navigateBack = { navController.popBackStack() })
        }
        composable(
            DestinasiDetailPemesanan.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPemesanan.beliIdArg) {
                type = NavType.IntType
            }
            )) {
            DetailsScreen(
                navigateToEditItem = { navController.navigate("${DestinasiEdit.route}/$it") },
                navigasiBack = { navController.popBackStack() })
        }
        composable(
            DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.popBackStack() })
        }
        composable(DestinasiKendaran.route) {
            KendaraanScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntryKendaraan.route) },
                onDetailClick = { navController.navigate("${DestinasiDetailKendaraan.route}/$it") },
            )
        }
        composable(DestinasiEntryKendaraan.route) {
            KendaraanScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            DestinasiDetailKendaraan.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailKendaraan.beliIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navigateToEditItem = { navController.navigate("${DestinasiEditKendaraan.route}/$it") },
                navigasiBack = { navController.popBackStack() })
        }
        composable(
            DestinasiEditKendaraan.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEditKendaraan.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            KendaraanEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.popBackStack() })
        }
    }
}
