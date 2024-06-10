plugins {
    id("plugin.android.library")
    id("plugin.android.hilt")
}

android {
    namespace = "com.tgyuu.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
}
