plugins {
    id("com.pettsme.showcase.app")
    id("com.pettsme.showcase.compose") // temporary application until UI doesn't have a separate module
}

android {
    namespace = "com.pettsme.showcase"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:ui"))
    implementation(project(":feature:characterlist"))
    implementation(project(":feature:characterdetails"))
}
