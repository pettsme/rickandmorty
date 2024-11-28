package com.pettsme.showcase

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pettsme.showcase.presentation.ScreenScaffold
import com.pettsme.showcase.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupEdgeToEdge()
        setContent {
            AppTheme {
                ScreenScaffold()
            }
        }
    }

    private fun setupEdgeToEdge() {
        val isNightMode =
            (resources.configuration.uiMode and UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES
        val navBarStyle = if (isNightMode) {
            SystemBarStyle.dark(TRANSPARENT)
        } else {
            SystemBarStyle.light(TRANSPARENT, TRANSPARENT)
        }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(TRANSPARENT, TRANSPARENT),
            navigationBarStyle = navBarStyle,
        )
    }
}
