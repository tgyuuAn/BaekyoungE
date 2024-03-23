plugins {
    id("plugin.android.library")
}

android {
    namespace = "com.tgyuu.common"
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}
