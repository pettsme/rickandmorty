package com.pettsme.showcase.characterlist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewState
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.presentation.component.Screen
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
@ExperimentalMaterial3Api
fun CharacterListScreen(modifier: Modifier = Modifier) {
    val viewModel: CharacterListViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterListViewState.initialState)

    viewModel.events.collectAsEffect { viewEvent ->
        when (viewEvent) {
            else -> Ignored
        }
    }

    val pullToRefreshState = rememberPullToRefreshState()

    Box(modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!pullToRefreshState.isRefreshing) {
                items(state.data) { character ->
                    ListItem({
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    })
                }
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
        )
    }
}

@Composable
fun CharacterListScreenContent(
    viewState: CharacterListViewState,
    viewEventHandler: (CharacterListViewAction),
) {
    Screen(
        viewState = viewState,
    ) { pullToRefreshLoading ->
        if (pullToRefreshLoading) {
        } else {
        }
    }
}

@Preview
@Composable
fun CharacterListScreenContentPreview() {
}
