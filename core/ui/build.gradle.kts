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
    api(libs.androidx.hilt.navigation.compose)
    api(libs.bundles.compose.core)
    api(libs.bundles.coil)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)
}
