import org.gradle.api.Plugin
import org.gradle.api.Project
import com.pettsme.showcase.buildlogic.logic.configureSpotless
import com.pettsme.showcase.buildlogic.logic.ext.getVersionCatalog
import com.pettsme.showcase.buildlogic.logic.ext.version

/**
 * Configures and applies Spotless linting tool to the gradle project
 */
class SpotlessConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            val versionCatalog = getVersionCatalog()
            val ktLintVersion = versionCatalog.version("ktlint")

            configureSpotless(ktLintVersion)
        }
    }
}
