package com.pettsme.showcase.build.logic.ext

import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.provider.Provider

internal fun VersionCatalog.library(versionCatalogName: String): Provider<MinimalExternalModuleDependency> {
    return findLibrary(versionCatalogName).get()
}

internal fun VersionCatalog.version(alias: String): String {
    return findVersion(alias).get().toString()
}

internal fun VersionCatalog.bundle(alias: String): Provider<ExternalModuleDependencyBundle> {
    return findBundle(alias).get()
}
