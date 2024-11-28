plugins {
    id("com.pettsme.showcase.core")
}

android {
    namespace = "com.pettsme.showcase.core.domain"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:network"))

    api(libs.androidx.core.ktx)
    api(libs.timber)
}
