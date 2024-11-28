package com.pettsme.showcase.base.presentation

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultStringProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) : StringProvider {
    override fun getString(@StringRes stringId: Int) =
        context.getString(stringId)

    override fun getString(stringId: Int, vararg values: Any) =
        context.getString(stringId, *values)
}
