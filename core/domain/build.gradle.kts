plugins {
    id("plugin.android.library")
    id("plugin.android.hilt")
}

android {
    namespace = "com.tgyuu.domain"
}

dependencies {
    implementation(project(":core:model"))

    testImplementation(libs.junit)
}
