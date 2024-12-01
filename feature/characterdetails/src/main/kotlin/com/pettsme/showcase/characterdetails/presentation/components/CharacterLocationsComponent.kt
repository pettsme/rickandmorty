package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pettsme.showcase.base.extensions.firstSubtype
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.fakeCharacterDetailUiModels
import com.pettsme.showcase.ui.extensions.applyIf
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen.paddingDefault

@Composable
internal fun CharacterLocationsComponent(
    uiModel: LocationsUiModel,
    scrollState: ScrollState?,
    onViewAction: (CharacterDetailsAction) -> Unit,
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
        uiModel.locations.mapIndexed { index, locationUiModel ->
            ExpandableLocationComponent(
                modifier = Modifier.applyIf(index == 0) { padding(bottom = paddingDefault) },
                uiModel = locationUiModel,
                scrollState = scrollState,
                onViewAction = onViewAction,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CharacterLocationComponent_Preview() {
    AppTheme {
        CharacterLocationsComponent(
            uiModel = fakeCharacterDetailUiModels.firstSubtype<CharacterDetailsUiModel, LocationsUiModel>(),
            scrollState = null,
            onViewAction = {},
        )
    }
}
