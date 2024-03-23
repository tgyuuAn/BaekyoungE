plugins {
    `kotlin-dsl`
}

group = "com.tgyuu.baekyounge.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.build)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.ksp.gradle)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "plugin.android.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "plugin.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidApplication") {
            id = "plugin.android.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "plugin.android.feature"
            implementationClass = "FeatureConventionPlugin"
        }
    }
}
