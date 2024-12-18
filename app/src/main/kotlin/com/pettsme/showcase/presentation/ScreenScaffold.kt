package com.pettsme.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pettsme.showcase.navigation.MainNavHost
import com.pettsme.showcase.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val navController = rememberNavController()
        Scaffold {
            MainNavHost(
                navController = navController,
                modifier = Modifier.padding(it),
            )
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
