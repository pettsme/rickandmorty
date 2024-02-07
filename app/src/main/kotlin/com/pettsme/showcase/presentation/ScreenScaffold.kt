package com.pettsme.showcase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pettsme.showcase.characterlist.presentation.CharacterListScreen
import com.pettsme.showcase.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CharacterListScreen()
    }
}

@Preview
@Composable
fun MainAppPreview() {
    AppTheme {
        ScreenScaffold()
    }
}
