import com.pettsme.showcase.buildlogic.logic.ext.getVersionCatalog
import com.pettsme.showcase.buildlogic.logic.ext.library
import com.pettsme.showcase.build.logic.model.BuildConstants.KSP
import com.pettsme.showcase.build.logic.model.BuildConstants.TEST_IMPLEMENTATION
import com.pettsme.showcase.build.logic.model.BuildConstants.TEST_RUNTIME
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION

/**
 * Adds core dependencies that a typical Feature module will require,
 */
class FeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.pettsme.showcase.core")
                apply("com.pettsme.showcase.compose")
                apply("com.google.devtools.ksp")
                apply("com.pettsme.showcase.spotless")
            }

            val versionCatalog = getVersionCatalog()

            dependencies {
                add(IMPLEMENTATION, project(":core:base"))
                add(IMPLEMENTATION, project(":core:viewmodelbase"))
                add(IMPLEMENTATION, project(":core:network"))
                add(IMPLEMENTATION, project(":core:domain"))
                add(IMPLEMENTATION, project(":core:ui"))

                add(KSP, versionCatalog.library("moshi.codegen"))

                add(TEST_IMPLEMENTATION, versionCatalog.library("junit.jupiter"))
                add(TEST_RUNTIME, versionCatalog.library("junit.jupiter.engine"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("mockk"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("androidx.arch.core.testing"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("kotlinx.coroutines.test"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("kotest.assertions.core"))
                add(TEST_IMPLEMENTATION, versionCatalog.library("turbine"))
            }
        }
    }
}
