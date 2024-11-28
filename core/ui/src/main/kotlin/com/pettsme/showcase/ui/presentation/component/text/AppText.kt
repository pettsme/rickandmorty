package com.pettsme.showcase.ui.presentation.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

enum class AppText {
    H1,
    H2,
    Body,
    ;

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        text: String,
        color: Color = Color.Unspecified,
        overflow: TextOverflow = TextOverflow.Ellipsis,
        textAlign: TextAlign = TextAlign.Start,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        val style = asTextStyle()
        Text(
            modifier = modifier,
            text = text,
            color = color,
            overflow = overflow,
            textAlign = textAlign,
            minLines = minLines,
            maxLines = maxLines,
            style = style,
        )
    }
}
