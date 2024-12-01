import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import com.pettsme.showcase.buildlogic.logic.ext.getVersionCatalog
import com.pettsme.showcase.buildlogic.logic.ext.library
import com.pettsme.showcase.buildlogic.logic.model.BuildConstants.KAPT
import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

/**
 * Configures and applies HILT on the gradle project.
 */
class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("kotlin-kapt")
            }

            val kaptExtension = extensions.getByType<KaptExtension>()
            kaptExtension.correctErrorTypes = true

            val versionCatalog = getVersionCatalog()
            dependencies {
                add(IMPLEMENTATION, versionCatalog.library("hilt.android"))
                add(KAPT, versionCatalog.library("hilt.compiler"))
            }
        }
    }
}
