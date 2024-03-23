pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "baekyounge"
include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:network")
include(":core:data")
include(":core:domain")
include(":core:model")
