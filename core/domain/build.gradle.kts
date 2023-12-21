plugins {
    id("plugin.android.library")
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