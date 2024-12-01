package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pettsme.showcase.core.ui.R
import com.pettsme.showcase.ui.presentation.component.base.Bordered
import com.pettsme.showcase.ui.values.Dimen.paddingDefault
import com.pettsme.showcase.ui.values.Dimen.paddingQuarter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandableComponent(
    onExpanded: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState? = null,
    openCloseIconColor: Color = Color.Unspecified,
    label: @Composable RowScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    val opened = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    val extraPaddingInPx = with(LocalDensity.current) { paddingDefault.toPx() }

    Bordered(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                label()
                IconButton(
                    onClick = {
                        onExpanded()
                        opened.value = !opened.value
                        coroutineScope.launch {
                            delay(400)
                            bringIntoViewRequester.bringIntoView()
                            scrollState?.animateScrollBy(extraPaddingInPx)
                        }
                    },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(paddingQuarter),
                        painter = painterResource(
                            id = if (opened.value) {
                                R.drawable.ic_arrow_up
                            } else {
                                R.drawable.ic_arrow_down
                            },
                        ),
                        contentDescription = null,
                        tint = openCloseIconColor,
                    )
                }
            }
            AnimatedVisibility(visible = opened.value) {
                content()
            }
        }
    }
}
