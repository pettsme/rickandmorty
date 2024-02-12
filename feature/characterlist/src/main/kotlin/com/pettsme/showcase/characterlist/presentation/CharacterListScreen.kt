package com.pettsme.showcase.characterlist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction.LoadNextPage
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewItem
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewState
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
fun CharacterListScreen(modifier: Modifier = Modifier) {
    val viewModel: CharacterListViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterListViewState.initialState)

    viewModel.events.collectAsEffect { viewEvent ->
        when (viewEvent) {
            else -> Ignored
        }
    }

    CharacterListScreenContent(
        modifier = modifier,
        viewState = state,
        viewEventHandler = { viewModel.onViewAction(it) },
    )
}

@Composable
internal fun CharacterListScreenContent(
    modifier: Modifier = Modifier,
    viewState: CharacterListViewState,
    viewEventHandler: (CharacterListViewAction) -> Unit,
) {
    // loading and error states not exclusive to the content in several cases,
    // depending on actual content.

    if (viewState.isLoading) {
        Loading(modifier)
    }

    if (viewState.isError) {
        // show error based on other stuff (whether it's partial data error or inital
    }

    LazyColumn(modifier.fillMaxSize()) {
        items(viewState.data) { item ->

            when (item) {
                is CharacterListViewItem.DataItem -> ListItem({
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                })

                CharacterListViewItem.Loading -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                    LaunchedEffect("loading") {
                        viewEventHandler(LoadNextPage)
                    }
                }
            }
        }
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
private fun CharacterListScreenContentPreview() {
    AppTheme {
        CharacterListScreenContent(
            viewState = CharacterListViewState.fakeState,
        ) {}
    }
}
