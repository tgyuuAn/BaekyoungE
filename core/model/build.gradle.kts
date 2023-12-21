plugins {
    id("plugin.android.library")
}

android {
    namespace = "com.tgyuu.model"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
}