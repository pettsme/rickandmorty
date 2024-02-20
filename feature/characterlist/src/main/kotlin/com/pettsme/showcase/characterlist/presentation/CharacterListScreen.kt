package com.pettsme.showcase.characterlist.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewAction.LoadNextPage
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewItem
import com.pettsme.showcase.characterlist.presentation.model.CharacterListViewState
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.presentation.component.VitalStatusTag
import com.pettsme.showcase.ui.values.AppTheme
import com.pettsme.showcase.ui.values.Dimen
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit,
) {
    val viewModel: CharacterListViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterListViewState.initialState)

    collectViewEvents(viewModel)

    CharacterListScreenContent(
        modifier = modifier,
        viewState = state,
        viewEventHandler = { viewModel.onViewAction(it) },
        navigateToDetails = navigateToDetails,
    )
}

@SuppressLint("ComposableNaming")
@Composable
private fun collectViewEvents(viewModel: CharacterListViewModel) {
    viewModel.events.collectAsEffect { viewEvent ->
        when (viewEvent) {
            else -> Ignored
        }
    }
}

@Composable
internal fun CharacterListScreenContent(
    modifier: Modifier = Modifier,
    viewState: CharacterListViewState,
    viewEventHandler: (CharacterListViewAction) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    // loading and error states not exclusive to the content in several cases,
    // depending on actual content.

    if (viewState.isLoading) {
        Loading(modifier)
    }

    if (viewState.isError) {
        // show error based on other stuff (whether it's partial data error or initial
    }

    CharacterList(modifier, viewState, viewEventHandler, navigateToDetails)
}

@Composable
private fun CharacterList(
    modifier: Modifier,
    viewState: CharacterListViewState,
    viewEventHandler: (CharacterListViewAction) -> Unit,
    navigateToDetails: (Int) -> Unit,
) {
    LazyColumn(modifier.fillMaxSize()) {
        items(viewState.data) { item ->
            when (item) {
                is CharacterListViewItem.DataItem -> CharacterListItemView(item, navigateToDetails)
                CharacterListViewItem.Loading -> CharacterListItemViewLoading(viewEventHandler)
            }
        }
    }
}

@Composable
private fun CharacterListItemView(
    item: CharacterListViewItem.DataItem,
    navigateToDetails: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable { navigateToDetails(item.id) }
            .padding(Dimen.spacingNormal)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.colorScheme.primaryContainer),
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
    ) {
        Row {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.img_placeholder),
            )
            CharacterDetailColumn(item)
        }
    }
}

@Composable
private fun CharacterDetailColumn(item: CharacterListViewItem.DataItem) {
    Column(
        modifier = Modifier
            .padding(Dimen.spacingNormal)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = item.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.weight(1f),
            )
            VitalStatusTag(vitalStatus = item.status)
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = Dimen.spacingQuarter),
            thickness = Dimen.dividerHeight,
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = item.species,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@Composable
private fun CharacterListItemViewLoading(viewEventHandler: (CharacterListViewAction) -> Unit) {
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
        CharacterListScreenContent(
            viewState = CharacterListViewState.fakeState,
            viewEventHandler = {},
        ) {}
    }
}

@Preview
@Composable
private fun CharacterListScreenContentPreviewDark() {
    AppTheme(useDarkTheme = true) {
        CharacterListScreenContent(
            viewState = CharacterListViewState.fakeState,
            viewEventHandler = {},
        ) {}
    }
}
