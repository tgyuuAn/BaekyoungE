import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("plugin.android.application")
}

android {
    namespace = "com.tgyuu.baekyounge"

    defaultConfig {
        applicationId = "com.tgyuu.baekyounge"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "KAKAO_NATIVE_APP_KEY",
            getApiKey("kakao_native_app_key"),
        )

        manifestPlaceholders["KAKAO_OAUTH_HOST"] = getApiKey("kakao_oauth_host")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))
    implementation(project(":core:network"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:consulting"))
    implementation(project(":feature:aichatting"))
    implementation(project(":feature:mentoring"))
    implementation(project(":feature:mentorchatting"))
    implementation(project(":feature:mentoring-mentee"))
    implementation(project(":feature:mentoring-mentor"))
    implementation(project(":feature:storage"))
    implementation(project(":feature:shop"))
    implementation(project(":feature:profile"))

    implementation(libs.kakao.user)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

fun getApiKey(propertyKey: String): String = gradleLocalProperties(rootDir).getProperty(propertyKey)
