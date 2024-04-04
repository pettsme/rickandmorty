package com.pettsme.showcase.build.logic

import com.diffplug.gradle.spotless.SpotlessExtension
import com.pettsme.showcase.build.logic.model.BuildConstants
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureSpotless(
    ktLintVersion: String,
) {
    extensions.configure<SpotlessExtension> {
        //ratchetFrom("origin/${BuildConstants.baseBranch}")
        kotlin {
            ktlint(ktLintVersion)
                .editorConfigOverride(
                    mapOf(
                        "disabled_rules" to "filename",
                        "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                        "ij_kotlin_allow_trailing_comma" to "true",
                    )
                )
            target("**/*.kt")
        }
    }
}
