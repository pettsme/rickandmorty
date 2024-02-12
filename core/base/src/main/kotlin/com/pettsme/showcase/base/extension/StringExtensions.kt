package com.pettsme.showcase.base.extension

import java.util.Locale

fun String.makeFirstLetterCapital() =
    replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
