package com.pettsme.showcase.characters.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.pettsme.showcase.characters.presentation.components.CharacterPreviewComponent
import com.pettsme.showcase.characters.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characters.presentation.model.CharacterListViewAction.LoadNextPage
import com.pettsme.showcase.characters.presentation.model.CharacterListViewState
import com.pettsme.showcase.characters.presentation.model.CharactersUiModel.CharacterPreviewUiModel
import com.pettsme.showcase.characters.presentation.model.CharactersUiModel.LoadingUiModel
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.presentation.component.base.Screen
import com.pettsme.showcase.ui.presentation.component.base.SimpleTopBar
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen
import com.pettsme.showcase.ui.values.Dimen.spacingNormal
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
fun CharacterListScreen(
    navigateToDetails: (Int) -> Unit,
) {
    val viewModel: CharactersViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterListViewState.initialState)

    collectViewEvents(viewModel)

    CharacterListScreenContent(
        viewState = state,
        viewEventHandler = { viewModel.onViewAction(it) },
        navigateToDetails = navigateToDetails,
    )
}

@SuppressLint("ComposableNaming")
@Composable
private fun collectViewEvents(viewModel: CharactersViewModel) {
    viewModel.events.collectAsEffect { viewEvent ->
        when (viewEvent) {
            else -> Ignored
        }
    }
}

@Composable
internal fun CharacterListScreenContent(
    viewState: CharacterListViewState,
    viewEventHandler: (CharacterListViewAction) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    // loading and error states not exclusive to the content in several cases,
    // depending on actual content.
    if (viewState.isLoading) {
        Loading()
    }

    if (viewState.isError) {
        // show error based on other stuff (whether it's partial data error or initial
    }

    CharacterList(
        viewState = viewState,
        viewEventHandler = viewEventHandler,
        navigateToDetails = navigateToDetails,
    )
}

@Composable
private fun CharacterList(
    viewState: CharacterListViewState,
    modifier: Modifier = Modifier,
    viewEventHandler: (CharacterListViewAction) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    Screen(
        topBar = {
            SimpleTopBar(
                title = stringResource(R.string.characters_screen_title),
            )
        },
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = spacingNormal)
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(spacingNormal),
        ) {
            items(viewState.data) { item ->
                when (item) {
                    is CharacterPreviewUiModel -> CharacterPreviewComponent(
                        uiModel = item,
                        navigateToDetails = navigateToDetails,
                    )

                    LoadingUiModel -> CharacterPreviewLoading(viewEventHandler)
                }
            }
        }
    }
}

@Composable
private fun CharacterPreviewLoading(viewEventHandler: (CharacterListViewAction) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.padding(top = Dimen.spacingNormal))
    }
    LaunchedEffect("loading") {
        viewEventHandler(LoadNextPage)
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
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
        CharacterListScreenContent(
            viewState = CharacterListViewState.fakeState,
            viewEventHandler = {},
        ) {}
    }
}

@PreviewLightDark
@Composable
private fun CharacterListScreenContentPreviewDark() {
    AppTheme {
        CharacterListScreenContent(
            viewState = CharacterListViewState.fakeState,
            viewEventHandler = {},
        ) {}
    }
}
