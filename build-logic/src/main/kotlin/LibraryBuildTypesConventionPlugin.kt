import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import com.pettsme.showcase.build.logic.createBuildTypes

/**
 * Configures BuildTypes for the library module, some possible one
 * - DEBUG
 * - SNAPSHOT
 * - RELEASE
 */
class LibraryBuildTypesConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libraryExtension = extensions.getByType<LibraryExtension>()
            libraryExtension.createBuildTypes()
        }
    }
}
