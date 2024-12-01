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

    // test dependencies without testImplementation so they can be accessed by tests
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.kotlin.test)
}
