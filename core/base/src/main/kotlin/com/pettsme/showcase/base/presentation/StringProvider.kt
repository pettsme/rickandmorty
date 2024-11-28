package com.pettsme.showcase.base.presentation

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes stringId: Int): String

    fun getString(@StringRes stringId: Int, vararg values: Any): String
}
