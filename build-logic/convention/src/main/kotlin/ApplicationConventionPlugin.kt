import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("com.android.application")
                apply("plugin.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                applyCompose(this)
                defaultConfig.targetSdk = libs.targetSdk
            }

            dependencies {
                "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
                "implementation"(libs.findBundle("compose.bom").get())
                "debugImplementation"(libs.findBundle("compose.debug").get())

                "implementation"(platform(libs.findLibrary("firebase-bom").get()))
                "implementation"(libs.findLibrary("firebase-analytics").get())
                "implementation"(libs.findLibrary("firebase-crashlytics").get())
            }
        }

    }
}