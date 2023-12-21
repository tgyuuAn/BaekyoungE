plugins {
    id("plugin.android.library")
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