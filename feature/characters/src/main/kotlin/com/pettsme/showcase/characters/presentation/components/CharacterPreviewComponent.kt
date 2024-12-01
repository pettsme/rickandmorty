package com.pettsme.showcase.characters.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.AsyncImage
import com.pettsme.showcase.characters.presentation.model.CharactersUiModel.CharacterPreviewUiModel
import com.pettsme.showcase.characters.presentation.model.fakeCharacterUiModels
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen
import com.pettsme.showcase.ui.values.Dimen.paddingHalf

@Composable
internal fun CharacterPreviewComponent(
    uiModel: CharacterPreviewUiModel,
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { navigateToDetails(uiModel.id) }
            .fillMaxWidth(),
    ) {
        Row {
            AsyncImage(
                model = uiModel.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.img_placeholder),
            )
            CharacterDetailColumn(
                uiModel = uiModel,
            )
        }
    }
}

@Composable
private fun CharacterDetailColumn(
    uiModel: CharacterPreviewUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(start = Dimen.paddingDefault)
            .fillMaxWidth(),
    ) {
        AppText.H2(
            text = uiModel.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = paddingHalf),
        )
        AppText.Body(
            text = uiModel.description,
        )
    }
}

@PreviewLightDark
@Composable
fun CharacterListItemComponent_Preview() {
    AppTheme {
        CharacterPreviewComponent(
            uiModel = fakeCharacterUiModels[0],
            navigateToDetails = {},
        )
    }
}
