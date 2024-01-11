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
import com.den.finalproject.WashXBetApp
import com.den.finalproject.ui.halaman.AppHomeScreen
import com.den.finalproject.ui.halaman.DestinasiDetailPemesanan
import com.den.finalproject.ui.halaman.DestinasiEdit
import com.den.finalproject.ui.halaman.DestinasiEntry
import com.den.finalproject.ui.halaman.DestinasiHome
import com.den.finalproject.ui.halaman.DestinasiPelanggan
import com.den.finalproject.ui.halaman.DetailsScreen
import com.den.finalproject.ui.halaman.Homepage
import com.den.finalproject.ui.halaman.ItemEditScreen
import com.den.finalproject.ui.halaman.PelangganScreen

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
    CenterAlignedTopAppBar(title = { Text(title) }, modifier = modifier,
        scrollBehavior = scrollBehavior, navigationIcon = {
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
            AppHomeScreen(navigasiRent = { navController.navigate(DestinasiPelanggan.route) })
        }
        composable(DestinasiPelanggan.route) {
            PelangganScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { navController.navigate("${DestinasiDetailPemesanan.route}/$it") })
        }
        composable (DestinasiEntry.route) {
            PelangganScreen(
                navigateBack = { navController.popBackStack() })
        }
        composable(DestinasiDetailPemesanan.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailPemesanan.beliIdArg) {
                type = NavType.IntType
            }
            )) {
            DetailsScreen(
                navigateToEditItem = { navController.navigate("${DestinasiEdit.route}/$it") },
                navigasiBack = { navController.popBackStack() })
        }
        composable(DestinasiEdit.routeWithArgs,
            arguments = listOf(navArgument(DestinasiEdit.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.popBackStack() })
        }
    }
}