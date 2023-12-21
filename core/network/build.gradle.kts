plugins {
    id("plugin.android.library")
    id("plugin.android.hilt")
}

android {
    namespace = "com.tgyuu.network"
}

dependencies {
    implementation(libs.androidx.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
}