package com.pettsme.showcase.build.logic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.pettsme.showcase.build.logic.model.BuildType
import org.gradle.api.Project

@Suppress("NestedBlockDepth")
internal fun Project.configureAndroidBuilds(
    applicationExtension: ApplicationExtension
) {
    applicationExtension.apply {
        signingConfigs {
            create("release") {
                storeFile = project.file("../keystores/release.keystore")
                storePassword = System.getenv("STOREPASS")
                keyAlias = System.getenv("KEYALIAS")
                keyPassword = System.getenv("KEYPASS")
            }
            getByName("debug") {
                storeFile = project.file("../keystores/debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            }
        }

        createBuildTypes()
        buildTypes {
            BuildType.values().forEach { buildType ->
                getByName(buildType.name.lowercase()) {
                    signingConfig = signingConfigs.getByName(buildType.signingConfig)
                }
            }
        }

        buildTypes.forEach { buildType ->
            val buildConfig = BuildType.values()
                .first { it.name.equals(buildType.name, ignoreCase = true) }

            with(buildType) {
                versionNameSuffix = buildConfig.versionNameSuffix
//                enableUnitTestCoverage = buildConfig.testCoverageEnabled
//                enableAndroidTestCoverage = buildConfig.testCoverageEnabled
                isShrinkResources = buildConfig.shrinkResources
                isMinifyEnabled = buildConfig.isMinified
                isDebuggable = buildConfig.isDebuggable

                if (buildConfig.packageNameSuffix != null) {
                    applicationIdSuffix = buildConfig.packageNameSuffix
                }

                if (isMinifyEnabled) {
                    buildConfig.proguardFiles.forEachIndexed { index, filePath ->
                        when (index) {
                            0 -> proguardFile(getDefaultProguardFile(filePath))
                            else -> proguardFile(filePath)
                        }
                    }
                }
            }
        }
    }
}

internal fun CommonExtension<*, *, *, *, *>.createBuildTypes() {
    buildTypes {
        BuildType.values().forEach { buildType ->
            maybeCreate(buildType.name.lowercase())
        }
    }
}
