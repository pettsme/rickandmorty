import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import com.pettsme.showcase.build.logic.configureAndroidBuilds

/**
 * Configures BuildTypes for the application, can be:
 * - DEBUG
 * - SNAPSHOT
 * - RELEASE
 */
class AppBuildTypesConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val applicationExtension = extensions.getByType<ApplicationExtension>()
            configureAndroidBuilds(applicationExtension)
        }
    }
}
