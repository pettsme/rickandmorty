package com.pettsme.showcase.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.pettsme.showcase.ui.values.Dimen

interface AppTypography {
    val h1: TextStyle // screen title
    val h2: TextStyle // character name
    val body: TextStyle // body texts
}

object DefaultAppTypography : AppTypography {
    override val h1 = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = Dimen.textH1,
        lineHeight = Dimen.textH1LineHeight,
    )
    override val h2 = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = Dimen.textH2,
        lineHeight = Dimen.textH2LineHeight,
    )

    override val body = TextStyle(
        fontFamily = DefaultFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = Dimen.textBody,
        lineHeight = Dimen.textBodyLineHeight,
    )
}
