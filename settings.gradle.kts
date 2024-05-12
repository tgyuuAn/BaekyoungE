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
        maven("https://jitpack.io")
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }
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
include(":feature:auth")
include(":feature:home")
include(":feature:splash")
include(":feature:consulting")
include(":feature:aichatting")
include(":feature:profile")
include(":feature:mentoring")
include(":feature:storage")
include(":feature:shop")
include(":core:database")
include(":feature:mentoring-mentee")
include(":feature:mentorchatting")
include(":feature:mentoring-mentor")
