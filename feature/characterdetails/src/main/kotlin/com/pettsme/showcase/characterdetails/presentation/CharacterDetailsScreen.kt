package com.pettsme.showcase.characterdetails.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pettsme.showcase.characterdetails.presentation.components.ActionButtonsComponent
import com.pettsme.showcase.characterdetails.presentation.components.CharacterInfoComponent
import com.pettsme.showcase.characterdetails.presentation.components.Divider
import com.pettsme.showcase.characterdetails.presentation.components.EpisodesComponent
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewData
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsState
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.presentation.component.VitalStatusTag
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
fun CharacterDetailsScreen() {
    val viewModel: CharacterDetailsViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterDetailsState.initialState)

    collectViewEvents(viewModel)

    CharacterDetailsScreenContent(
        modifier = Modifier,
        viewState = state,
        viewEventHandler = { viewModel.onViewAction(it) },
    )
}

@SuppressLint("ComposableNaming")
@Composable
private fun collectViewEvents(viewModel: CharacterDetailsViewModel) {
    viewModel.events.collectAsEffect { viewEvent ->
        when (viewEvent) {
            else -> Ignored
        }
    }
}

@Composable
private fun CharacterDetailsScreenContent(
    modifier: Modifier = Modifier,
    viewState: CharacterDetailsState,
    viewEventHandler: (CharacterDetailsAction) -> Unit,
) {
    // loading and error states not exclusive to the content in several cases,
    // depending on actual content.

    if (viewState.isLoading) {
        Loading(modifier)
    }

    if (viewState.isError) {
        // show error based on other stuff (whether it's partial data error or initial
    }

    val scrollState = rememberScrollState()

    viewState.data?.let {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            CharacterDetailsHeader(viewData = it)
            Divider()
            CharacterInfoComponent(viewData = it)
            EpisodesComponent(it.presentInEpisodes)
            ActionButtonsComponent(
                viewData = it,
                scrollState = scrollState,
                viewEventHandler = viewEventHandler,
            )
        }
    }
}

@Composable
private fun CharacterDetailsHeader(viewData: CharacterDetailsViewData) {
    AsyncImage(
        model = viewData.imageUrl,
        contentDescription = "image of ${viewData.name} character",
        placeholder = painterResource(id = R.drawable.img_placeholder),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth(),
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimen.spacingNormal)
            .padding(horizontal = Dimen.spacingNormal),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppText.Body(
            text = viewData.name,
            modifier = Modifier.weight(1f),
        )
        VitalStatusTag(vitalStatus = viewData.status)
    }
}

@Composable
private fun Loading(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun CharacterListScreenContentPreviewLight() {
    AppTheme {
        CharacterDetailsScreenContent(
            viewState = CharacterDetailsState.fakeState,
        ) {}
    }
}

@PreviewLightDark
@Composable
private fun CharacterListScreenContentPreviewDark() {
    AppTheme {
        CharacterDetailsScreenContent(
            viewState = CharacterDetailsState.fakeState,
        ) {}
    }
}
