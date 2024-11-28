package com.pettsme.showcase.characterdetails.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewData
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.values.Dimen

@Composable
internal fun CharacterInfoComponent(viewData: CharacterDetailsViewData) {
    Column(modifier = Modifier.padding(horizontal = Dimen.spacingNormal)) {
        AppText.Body(
            text = stringResource(
                id = R.string.details_character_description,
                viewData.name,
                viewData.gender.value,
                viewData.species,
                viewData.origin.name,
                viewData.lastKnownLocation.name,
            ),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimen.spacingHalf, bottom = Dimen.spacingNormal),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Label(label = R.string.details_label_species, value = viewData.species)
            Spacer(modifier = Modifier.width(Dimen.spacingNormal))
            Label(label = R.string.details_label_gender, value = viewData.gender.value)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimen.spacingHalf, bottom = Dimen.spacingNormal),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Label(label = R.string.details_label_origin, value = viewData.origin.name)
            Spacer(modifier = Modifier.width(Dimen.spacingNormal))
            Label(
                label = R.string.details_label_last_known_location,
                value = viewData.lastKnownLocation.name,
            )
        }
    }
}

@Composable
private fun RowScope.Label(@StringRes label: Int, value: String) {
    Card(
        modifier = Modifier
            .weight(1f),
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(
            modifier = Modifier.padding(Dimen.spacingQuarter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AppText.Body(
                text = stringResource(id = label),
                textAlign = TextAlign.Center,
            )
            Divider()
            AppText.Body(
                text = value,
                textAlign = TextAlign.Center,
            )
        }
    }
}
