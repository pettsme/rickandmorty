package com.pettsme.showcase.base.test.fakes

import com.pettsme.showcase.base.presentation.StringProvider

class FakeStringProvider : StringProvider {
    override fun getString(stringId: Int): String {
        return stringId.toString()
    }

    override fun getString(stringId: Int, vararg values: Any): String {
        return if (values.isEmpty()) {
            stringId.toString()
        } else {
            "$stringId-${values.joinToString(",")}".trimEnd { it == ',' }
        }
    }
}
