package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
import com.pettsme.showcase.characterdetails.presentation.model.EpisodeUiModel
import com.pettsme.showcase.ui.presentation.component.base.Bordered
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.values.Dimen
import com.pettsme.showcase.ui.values.Dimen.paddingDefault
import com.pettsme.showcase.ui.values.Dimen.paddingHalf
import com.pettsme.showcase.ui.values.Dimen.paddingQuarter

@Composable
internal fun RelatedEpisodesComponent(
    uiModel: RelatedEpisodesUiModel,
    modifier: Modifier = Modifier,
) {
    val widthOfEpisodeTile = (LocalConfiguration.current.screenWidthDp / 2.5).dp

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AppText.H2(
            text = uiModel.sectionTitle,
            modifier = modifier.padding(vertical = paddingDefault),
        )

        LazyRow(
            modifier = modifier.fillMaxWidth(),
        ) {
            itemsIndexed(uiModel.episodes) { index, item ->
                EpisodeCard(
                    data = item,
                    modifier = Modifier
                        .size(widthOfEpisodeTile)
                        .listPadding(index, uiModel.episodes.lastIndex),
                )
            }
        }
    }
}

@Composable
private fun EpisodeCard(data: EpisodeUiModel, modifier: Modifier) {
    Bordered(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingHalf),
        ) {
            AppText.Body(
                text = data.name,
                modifier = Modifier.padding(bottom = paddingQuarter),
            )
            AppText.Body(
                text = data.code,
            )
            Spacer(modifier = Modifier.weight(1f))
            AppText.Body(
                text = data.aired,
            )
        }
    }
}

private fun Modifier.listPadding(index: Int, lastItem: Int): Modifier = when (index) {
    0 -> then(padding(end = Dimen.paddingQuarter))
    lastItem -> then(padding(start = Dimen.paddingQuarter))
    else -> then(padding(horizontal = Dimen.paddingQuarter))
}
