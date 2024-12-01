package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil.compose.AsyncImage
import com.pettsme.showcase.base.extensions.firstSubtype
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.InfoUiModel
import com.pettsme.showcase.characterdetails.presentation.model.fakeCharacterDetailUiModels
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen.paddingDefault

@Composable
internal fun CharacterDetailsInfoComponent(
    uiModel: InfoUiModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            modifier = Modifier.padding(end = paddingDefault),
            model = uiModel.imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.img_placeholder),
        )
        AppText.Body(
            text = uiModel.description,
            modifier = Modifier.weight(1f),
        )
    }
}

@PreviewLightDark
@Composable
private fun CharacterDetailsInfoComponent_Preview() {
    AppTheme {
        CharacterDetailsInfoComponent(
            uiModel = fakeCharacterDetailUiModels.firstSubtype<CharacterDetailsUiModel, InfoUiModel>(),
        )
    }
}
