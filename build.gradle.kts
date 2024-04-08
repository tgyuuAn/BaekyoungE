plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.ktlint)
}

allprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
    }
}
