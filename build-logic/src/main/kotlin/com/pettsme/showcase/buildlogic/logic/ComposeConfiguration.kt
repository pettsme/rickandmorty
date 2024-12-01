package com.pettsme.showcase.buildlogic.logic

import com.android.build.api.dsl.CommonExtension
import com.pettsme.showcase.buildlogic.logic.ext.bundle
import com.pettsme.showcase.buildlogic.logic.ext.getVersionCatalog
import com.pettsme.showcase.buildlogic.logic.ext.library
import com.pettsme.showcase.buildlogic.logic.ext.version
import com.pettsme.showcase.build.logic.model.BuildConstants.ANDROID_TEST_IMPLEMENTATION
import com.pettsme.showcase.build.logic.model.BuildConstants.DEBUG_API
import com.pettsme.showcase.buildlogic.logic.ext.kotlinOptions
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.utils.API
import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    val versionCatalog = getVersionCatalog()

    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                versionCatalog.version("androidxComposeCompiler")
        }
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=${project.rootProject.projectDir.absolutePath}/compose_compiler.conf",
            )
        }

        dependencies {
            val bom = versionCatalog.library("androidx-compose-bom")
            add(IMPLEMENTATION, platform(bom))
            add(ANDROID_TEST_IMPLEMENTATION, platform(bom))
            add(API, versionCatalog.bundle("compose-core"))
            add(API, versionCatalog.library("androidx-compose-ui-tooling-preview"))
            add(DEBUG_API, versionCatalog.library("androidx-compose-ui-tooling"))
        }
    }
}
