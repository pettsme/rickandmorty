plugins {
    id("com.pettsme.showcase.core")
    id("com.pettsme.showcase.compose")
}

android {
    namespace = "com.pettsme.showcase.core.ui"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:domain"))

    api(libs.androidx.hilt.navigation.compose)
    api(libs.bundles.compose.core)
    api(libs.bundles.coil)
    api(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3.material3)
    implementation(libs.androidx.compose.material3.android)
    debugApi(libs.androidx.compose.ui.tooling)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotest.assertions.core)
}
