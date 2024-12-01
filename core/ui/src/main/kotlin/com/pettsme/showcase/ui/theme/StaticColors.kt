package com.pettsme.showcase.ui.theme

import androidx.compose.ui.graphics.Color

// Colors that are independent from the dark/light themes
object StaticColors {
    // generally preferring product agnostic naming tokens, but as they are only used in a single place...
    object VitalStatusColors {
        // as text and bg color pairs
        val alive = Color(0xFF155724) to Color(0xFFD4EDDA)
        val dead = Color(0xFF721C24) to Color(0xFFF8D7DA)
        val unknown = Color(0xFF0C5460) to Color(0xFFD1ECF1)
    }
}
