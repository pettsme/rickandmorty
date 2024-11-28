package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewData
import com.pettsme.showcase.ui.values.Dimen

@Composable
internal fun ActionButtonsComponent(
    viewData: CharacterDetailsViewData,
    scrollState: ScrollState,
    viewEventHandler: (CharacterDetailsAction) -> Unit,
) {
    if (viewData.origin.id != -1) {
        ExpandableTextComponent(
            simpleLocation = viewData.origin,
            fullLocation = viewData.originFullLocation,
            scrollState = scrollState,
            viewEventHandler = viewEventHandler,
        )
    }
    if (viewData.lastKnownLocation.id != -1) {
        ExpandableTextComponent(
            simpleLocation = viewData.lastKnownLocation,
            fullLocation = viewData.lastKnownFullLocation,
            scrollState = scrollState,
            viewEventHandler = viewEventHandler,
        )
    }
    Spacer(modifier = Modifier.height(Dimen.spacingNormal))
}
