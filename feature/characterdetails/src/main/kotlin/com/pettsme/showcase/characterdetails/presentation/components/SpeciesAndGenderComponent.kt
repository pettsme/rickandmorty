package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.SpeciesGenderUiModel
import com.pettsme.showcase.ui.presentation.component.base.Bordered
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.values.Dimen.paddingDefault
import com.pettsme.showcase.ui.values.Dimen.paddingHalf

@Composable
internal fun SpeciesAndGenderComponent(
    uiModel: SpeciesGenderUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AppText.H2(
            text = uiModel.sectionTitle,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = paddingDefault),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            BorderedLabel(
                label = uiModel.species,
            )
            BorderedLabel(
                label = uiModel.gender,
            )
        }
    }
}

@Composable
fun BorderedLabel(
    label: String,
    modifier: Modifier = Modifier,
) {
    Bordered(modifier = modifier.padding(paddingHalf)) {
        AppText.Body(text = label)
    }
}
