package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeViewData
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.values.Dimen

@Composable
fun EpisodesComponent(
    listOfEpisodes: List<EpisodeViewData>?,
    modifier: Modifier = Modifier,
) {
    val widthOfEpisodeTile = (LocalConfiguration.current.screenWidthDp / 3).dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.spacingNormal, vertical = Dimen.spacingHalf),
    ) {
        AppText.Body(
            text = stringResource(id = R.string.details_episodes),
            modifier = modifier.padding(bottom = Dimen.spacingNormal),
        )

        if (listOfEpisodes != null) {
            LazyRow(
                modifier = modifier.fillMaxWidth(),
            ) {
                itemsIndexed(listOfEpisodes) { index, item ->
                    EpisodeCard(
                        data = item,
                        modifier = Modifier
                            .size(widthOfEpisodeTile)
                            .listPadding(index, listOfEpisodes.lastIndex),
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun EpisodeCard(data: EpisodeViewData, modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(corner = CornerSize(Dimen.cornerRadiusNormal)),
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimen.spacingHalf),
        ) {
            AppText.Body(
                text = data.name,
            )
            AppText.Body(
                text = data.episodeCode,
            )
            Spacer(modifier = Modifier.weight(1f))
            AppText.Body(
                text = stringResource(id = R.string.details_episodes_card_aired),
            )
            AppText.Body(
                text = data.aired,
            )
        }
    }
}

private fun Modifier.listPadding(index: Int, lastItem: Int): Modifier = when (index) {
    0 -> then(padding(end = Dimen.spacingQuarter))
    lastItem -> then(padding(start = Dimen.spacingQuarter))
    else -> then(padding(horizontal = Dimen.spacingQuarter))
}
