package com.pettsme.showcase.build.logic

import com.android.build.api.dsl.CommonExtension
import com.pettsme.showcase.build.logic.ext.getVersionCatalog
import com.pettsme.showcase.build.logic.ext.kotlinOptions
import com.pettsme.showcase.build.logic.ext.library
import com.pettsme.showcase.build.logic.model.DefaultBuildConfiguration
import org.gradle.api.JavaVersion.VERSION_17
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = DefaultBuildConfiguration.COMPILE_SDK

        defaultConfig {
            minSdk = DefaultBuildConfiguration.MIN_SDK
        }

        compileOptions {
            sourceCompatibility = VERSION_17
            targetCompatibility = VERSION_17
        }

        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.Experimental",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=androidx.compose.ui.text.ExperimentalTextApi",
                "-Xcontext-receivers"
            )

            jvmTarget = VERSION_17.toString()
        }
    }

    val versionCatalog = getVersionCatalog()

    dependencies {
        add("coreLibraryDesugaring", versionCatalog.library("android.desugarJdkLibs"))
    }
}
