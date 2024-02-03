import org.gradle.api.JavaVersion.VERSION_11
import org.gradle.api.JavaVersion.VERSION_17

plugins {
    `kotlin-dsl`
}

group = "com.pettsme.showcase.build.logic"

java {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
}

dependencies {
    // Gradle Plugins required by the build script for each of the Convention Plugins.
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("app") {
            id = "com.pettsme.showcase.app"
            implementationClass = "AppConventionPlugin"
        }
        register("appBuildTypes") {
            id = "com.pettsme.showcase.app.build.types"
            implementationClass = "AppBuildTypesConventionPlugin"
        }
        register("libraryBuildTypes") {
            id = "com.pettsme.showcase.library.build.types"
            implementationClass = "LibraryBuildTypesConventionPlugin"
        }
        register("core") {
            id = "com.pettsme.showcase.core"
            implementationClass = "CoreConventionPlugin"
        }
        register("feature") {
            id = "com.pettsme.showcase.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("compose") {
            id = "com.pettsme.showcase.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("spotless") {
            id = "com.pettsme.showcase.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("hilt") {
            id = "com.pettsme.showcase.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}
