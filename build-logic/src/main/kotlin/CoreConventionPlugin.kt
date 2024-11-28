import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.pettsme.showcase.buildlogic.logic.configureKotlinAndroid
import com.pettsme.showcase.buildlogic.logic.model.DefaultBuildConfiguration

/**
 * Configures Kotlin and Android Library plugin to the gradle project.
 */
class CoreConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
                apply("com.pettsme.showcase.library.build.types")
                apply("com.pettsme.showcase.spotless")
                apply("com.pettsme.showcase.hilt")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = DefaultBuildConfiguration.TARGET_SDK
                configureKotlinAndroid(this)
            }
        }
    }
}
