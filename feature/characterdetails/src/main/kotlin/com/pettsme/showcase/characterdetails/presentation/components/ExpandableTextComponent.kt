package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pettsme.showcase.characterdetails.domain.model.FullLocation
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewAction
import com.pettsme.showcase.characterdetails.presentation.model.LocationViewData
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.values.Dimen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ExpandableTextComponent(
    simpleLocation: LocationViewData,
    fullLocation: FullLocation?,
    scrollState: ScrollState,
    viewEventHandler: (CharacterDetailsViewAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val opened = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    val extraPaddingInPx = with(LocalDensity.current) { Dimen.spacingNormal.toPx() }

    Card(
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.tertiary),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimen.spacingHalf, horizontal = Dimen.spacingNormal),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppText.Body(
                    text = stringResource(
                        id = R.string.details_action_show_more,
                        simpleLocation.name,
                    ),
                    modifier = Modifier
                        .padding(Dimen.spacingNormal)
                        .weight(1f),
                )
                IconButton(
                    onClick = {
                        viewEventHandler(
                            CharacterDetailsViewAction.LocationExpanded(
                                simpleLocation.type,
                                simpleLocation.id,
                            ),
                        )
                        opened.value = !opened.value
                        coroutineScope.launch {
                            delay(400)
                            bringIntoViewRequester.bringIntoView()
                            scrollState.animateScrollBy(extraPaddingInPx)
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(Dimen.spacingQuarter),
                        painter = painterResource(
                            id = if (opened.value) {
                                R.drawable.ic_arrow_up
                            } else {
                                R.drawable.ic_arrow_down
                            },
                        ),
                        tint = MaterialTheme.colorScheme.onTertiary,
                        contentDescription = null,
                    )
                }
            }
            ExpandableContent(opened, fullLocation)
        }
    }
}

@Composable
private fun ColumnScope.ExpandableContent(
    opened: MutableState<Boolean>,
    fullLocation: FullLocation?,
) {
    AnimatedVisibility(visible = opened.value) {
        if (fullLocation == null) {
            ExpandedLocationLoading()
        } else {
            ExpandedLocationContent(fullLocation)
        }
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
private fun ExpandedLocationContent(fullLocation: FullLocation) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AppText.Body(
            modifier = Modifier
                .padding(
                    vertical = Dimen.spacingQuarter,
                    horizontal = Dimen.spacingNormal,
                )
                .fillMaxWidth(),
            text = stringResource(
                id = R.string.details_location_dimension,
                fullLocation.dimension,
            ),
        )
        AppText.Body(
            modifier = Modifier
                .padding(
                    vertical = Dimen.spacingQuarter,
                    horizontal = Dimen.spacingNormal,
                )
                .fillMaxWidth(),
            text = stringResource(
                id = R.string.details_location_type,
                fullLocation.type,
            ),
        )
        Spacer(modifier = Modifier.height(Dimen.spacingNormal))
    }
}
