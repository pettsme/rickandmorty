package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.NameUiModel
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.values.Dimen.paddingDefault

@Composable
internal fun CharacterNameComponent(
    uiModel: NameUiModel,
    modifier: Modifier = Modifier,
) {
    AppText.H1(
        text = uiModel.name,
        modifier = modifier.fillMaxWidth()
            .padding(vertical = paddingDefault),
    )
}
