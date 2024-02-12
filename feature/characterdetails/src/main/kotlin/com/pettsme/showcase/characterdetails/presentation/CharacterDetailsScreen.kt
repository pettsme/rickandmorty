package com.pettsme.showcase.characterdetails.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsViewState
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.values.AppTheme
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
fun CharacterDetailsScreen() {
    val viewModel: CharacterDetailsViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterDetailsViewState.initialState)

    viewModel.events.collectAsEffect { viewEvent ->
        when (viewEvent) {
            else -> Ignored
        }
    }

    CharacterDetailsScreenContent(
        modifier = Modifier,
        viewState = state,
        viewEventHandler = { viewModel.onViewAction(it) },
    )
}

@Composable
internal fun CharacterDetailsScreenContent(
    modifier: Modifier = Modifier,
    viewState: CharacterDetailsViewState,
    viewEventHandler: (CharacterDetailsViewAction) -> Unit,
) {
    // loading and error states not exclusive to the content in several cases,
    // depending on actual content.

    if (viewState.isLoading) {
        Loading(modifier)
    }

    if (viewState.isError) {
        // show error based on other stuff (whether it's partial data error or initial
    }

    Text(text = viewState.data?.name ?: "Proooooblem")
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
            viewState = CharacterDetailsViewState.fakeState,
        ) {}
    }
}

@Preview
@Composable
private fun CharacterListScreenContentPreviewDark() {
    AppTheme(useDarkTheme = true) {
        CharacterDetailsScreenContent(
            viewState = CharacterDetailsViewState.fakeState,
        ) {}
    }
}
