plugins {
    id("com.pettsme.showcase.core")
}

android {
    namespace = "com.pettsme.showcase.core.viewmodelbase"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:base"))
    api(libs.androidx.core.ktx)
    api(libs.bundles.lifecycle.ktx)

    api(libs.timber)
}
