plugins {
    id("com.pettsme.showcase.core")
}

android {
    namespace = "com.pettsme.showcase.core.base"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.androidx.core.ktx)
    api(libs.timber)
}
