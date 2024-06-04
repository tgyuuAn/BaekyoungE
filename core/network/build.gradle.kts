import java.util.Properties

plugins {
    id("plugin.android.library")
    id("plugin.android.hilt")
    kotlin("plugin.serialization") version "1.9.23"
}

android {
    namespace = "com.tgyuu.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("/local.properties").bufferedReader())
        buildConfigField(
            "String",
            "OPEN_AI_API_KEY",
            "\"${properties["open_ai_api_key"]}\"",
        )
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.firestore)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.okhttp)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
}
