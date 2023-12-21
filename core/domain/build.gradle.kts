plugins {
    id("plugin.android.library")
    id("plugin.android.hilt")
}

android {
    namespace = "com.tgyuu.domain"
}

dependencies {
    implementation(project(":core:data"))

    implementation(libs.androidx.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
}