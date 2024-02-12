package com.pettsme.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.navigation.MainNavHost
import com.pettsme.showcase.ui.values.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val navController = rememberNavController()
        val navBackStackEntryState by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntryState?.destination
        val shouldShowBack = currentDestination?.route != "characters"

        AppTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Showcase app") },
                        navigationIcon = {
                            if (shouldShowBack) {
                                IconButton(onClick = navController::navigateUp) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_back),
                                        contentDescription = "Navigate Back",
                                    )
                                }
                            }
                        },
                    )
                },
            ) {
                MainNavHost(
                    navController = navController,
                    modifier = Modifier.padding(it),
                )
            }
        }
    }
}

@Preview
@Composable
fun MainAppPreview() {
    AppTheme {
        ScreenScaffold()
    }
}
