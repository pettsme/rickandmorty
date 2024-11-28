import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.pettsme.showcase.buildlogic.logic.configureKotlinAndroid
import com.pettsme.showcase.buildlogic.logic.model.DefaultBuildConfiguration

/**
 * Configures the android application on the application module.
 */
class AppConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
                apply("com.pettsme.showcase.app.build.types")
                apply("com.pettsme.showcase.compose")
                apply("com.pettsme.showcase.spotless")
                apply("com.pettsme.showcase.hilt")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig {
                    targetSdk = DefaultBuildConfiguration.TARGET_SDK
                    applicationId = DefaultBuildConfiguration.PACKAGE_NAME
                    versionCode = DefaultBuildConfiguration.versionCode
                    versionName = DefaultBuildConfiguration.versionName

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                packaging {
                    resources {
                        excludes += mutableListOf(
                            "/META-INF/{AL2.0,LGPL2.1}",
                            "/META-INF/LICENSE.md",
                            "/META-INF/LICENSE-notice.md"
                        )
                    }
                }
            }
        }
    }
}
