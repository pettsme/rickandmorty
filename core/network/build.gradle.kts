plugins {
    id("com.pettsme.showcase.core")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.pettsme.showcase.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(libs.androidx.core.ktx)
    api(libs.bundles.retrofit)
    api(libs.okhttp.logging.interceptor)
    api(libs.moshi.core)
    ksp(libs.moshi.codegen)
}
