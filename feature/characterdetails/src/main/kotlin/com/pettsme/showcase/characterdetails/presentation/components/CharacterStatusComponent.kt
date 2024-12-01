package com.pettsme.showcase.characterdetails.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pettsme.showcase.characterdetails.presentation.model.CharacterDetailsUiModel.StatusUiModel
import com.pettsme.showcase.ui.presentation.component.text.AppText
import com.pettsme.showcase.ui.presentation.component.vitalstatus.VitalStatusTag
import com.pettsme.showcase.ui.values.Dimen.paddingDefault

@Composable
internal fun CharacterStatusComponent(
    uiModel: StatusUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AppText.H2(
            text = uiModel.sectionTitle,
            modifier = modifier.fillMaxWidth()
                .padding(vertical = paddingDefault),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AppText.Body(
                text = uiModel.nameLabel,
            )
            VitalStatusTag(uiModel = uiModel.vitalStatus)
        }
    }
}
