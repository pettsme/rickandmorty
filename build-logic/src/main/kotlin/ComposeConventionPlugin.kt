import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.InvalidPluginException
import org.gradle.kotlin.dsl.getByType
import com.pettsme.showcase.buildlogic.logic.configureCompose

/**
 * Configures and applies compose to the application module
 */
class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val extension = getExtension()
            configureCompose(extension)
        }
    }

    private fun Project.getExtension(): CommonExtension<*, *, *, *, *, *> =
        if (plugins.hasPlugin("com.android.application")) {
            extensions.getByType<ApplicationExtension>()
        } else if (plugins.hasPlugin("com.android.library")) {
            extensions.getByType<LibraryExtension>()
        } else {
            throw InvalidPluginException(
                "Attempting to apply Jetpack Compose to a module that " +
                        "is neither an android app module or android library module."
            )
        }
}
