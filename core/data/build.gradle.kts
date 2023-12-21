plugins {
    id("plugin.android.library")
    id("plugin.android.hilt")
}

android {
    namespace = "com.tgyuu.data"
}

dependencies {
    implementation(project(":core:network"))

    implementation(libs.androidx.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
}