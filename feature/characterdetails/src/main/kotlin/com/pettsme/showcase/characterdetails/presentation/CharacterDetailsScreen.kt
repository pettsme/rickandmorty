package com.pettsme.showcase.characterdetails.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.pettsme.showcase.characterdetails.presentation.components.CharacterDetailsInfoComponent
import com.pettsme.showcase.characterdetails.presentation.components.CharacterLocationsComponent
import com.pettsme.showcase.characterdetails.presentation.components.CharacterNameComponent
import com.pettsme.showcase.characterdetails.presentation.components.CharacterStatusComponent
import com.pettsme.showcase.characterdetails.presentation.components.RelatedEpisodesComponent
import com.pettsme.showcase.characterdetails.presentation.components.SpeciesAndGenderComponent
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsAction
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsState
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.InfoUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.LocationsUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.NameUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.RelatedEpisodesUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.SpeciesGenderUiModel
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.StatusUiModel
import com.pettsme.showcase.ui.extensions.collectAsEffect
import com.pettsme.showcase.ui.extensions.rememberFlowOnLifecycle
import com.pettsme.showcase.ui.presentation.component.base.RevealTitleTopBar
import com.pettsme.showcase.ui.presentation.component.base.Screen
import com.pettsme.showcase.ui.theme.AppTheme
import com.pettsme.showcase.ui.values.Dimen.paddingDefault
import com.pettsme.showcase.viewmodelbase.presentation.model.Ignored

@Composable
fun CharacterDetailsScreen(
    navigateBack: () -> Unit,
) {
    val viewModel: CharacterDetailsViewModel = hiltViewModel()
    val state by rememberFlowOnLifecycle(flow = viewModel.state)
        .collectAsState(CharacterDetailsState.initialState)

    collectViewEvents(viewModel)

    val listState = rememberLazyListState()
    val showTitle by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    Screen(
        topBar = {
            RevealTitleTopBar(
                title = state.title,
                navigateBack = navigateBack,
                showTitle = showTitle,
            )
        },
    ) {
        CharacterDetailsScreenContent(
            modifier = Modifier,
            state = state,
            listState = listState,
            onViewAction = viewModel::onViewAction,
        )
    }
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
    state: CharacterDetailsState,
    listState: LazyListState,
    onViewAction: (CharacterDetailsAction) -> Unit,
) {
    // loading and error states not exclusive to the content in several cases,
    // depending on actual content.

    if (state.isLoading) {
        Loading(modifier)
    }

    if (state.isError) {
        // show error based on other stuff (whether it's partial data error or initial
    }

    val scrollState = rememberScrollState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = paddingDefault),
        state = listState,
    ) {
        items(state.uiModels) { uiModel ->
            when (uiModel) {
                is NameUiModel -> CharacterNameComponent(uiModel)
                is InfoUiModel -> CharacterDetailsInfoComponent(uiModel)
                is LocationsUiModel -> CharacterLocationsComponent(
                    uiModel = uiModel,
                    scrollState = scrollState,
                    onViewAction = onViewAction,
                )

                is RelatedEpisodesUiModel -> RelatedEpisodesComponent(uiModel)
                is SpeciesGenderUiModel -> SpeciesAndGenderComponent(uiModel)
                is StatusUiModel -> CharacterStatusComponent(uiModel)
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

@PreviewLightDark
@Composable
private fun CharacterListScreenContent_Preview() {
    AppTheme {
        CharacterDetailsScreenContent(
            state = CharacterDetailsState.fakeState,
            listState = rememberLazyListState(),
        ) {}
    }
}
