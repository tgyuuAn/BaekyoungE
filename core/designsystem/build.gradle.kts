plugins {
    id("plugin.android.library")
    id("plugin.android.feature")
}

android {
    namespace = "com.tgyuu.designsystem"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.bom)
    implementation(libs.lottie.compose)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
}
