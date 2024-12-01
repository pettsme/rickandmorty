package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction.LocationExpanded
import com.pettsme.showcase.characterdetails.presentation.model.LocationDetailsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.LocationUiModel
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.ProjectTheme
import com.pettsme.showcase.ui.values.Dimen.paddingDefault
import com.pettsme.showcase.ui.values.Dimen.paddingQuarter

@Composable
internal fun ExpandableLocationComponent(
    uiModel: LocationUiModel,
    scrollState: ScrollState?,
    onViewAction: (CharacterDetailsAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    ExpandableComponent(
        modifier = modifier,
        scrollState = scrollState,
        openCloseIconColor = ProjectTheme.colors.primary,
        onExpanded = {
            onViewAction(
                LocationExpanded(
                    uiModel.type,
                    uiModel.id,
                ),
            )
        },
        label = {
            AppText.Body(
                text = uiModel.label,
                modifier = Modifier
                    .padding(paddingDefault)
                    .weight(1f),
            )
        },
        content = {
            ExpandableContent(uiModel.details)
        },
    )
}

@Composable
private fun ExpandableContent(
    uiModel: LocationDetailsUiModel?,
) {
    if (uiModel == null) {
        ExpandedLocationLoading()
    } else {
        ExpandedLocationContent(uiModel)
    }
}

@Composable
private fun ExpandedLocationLoading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onTertiary,
        )
    }
}

@Composable
private fun ExpandedLocationContent(
    uiModel: LocationDetailsUiModel,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AppText.Body(
            modifier = Modifier
                .padding(
                    vertical = paddingQuarter,
                    horizontal = paddingDefault,
                )
                .fillMaxWidth(),
            text = uiModel.dimension,
        )
        AppText.Body(
            modifier = Modifier
                .padding(
                    vertical = paddingQuarter,
                    horizontal = paddingDefault,
                )
                .fillMaxWidth(),
            text = uiModel.type,
        )
        Spacer(modifier = Modifier.height(paddingDefault))
    }
}
