rootProject.name = "ArchDev"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":data:database")
include(":data:preferences")
include(":core:core-di")
include(":feature:auth:auth-ui")
include(":feature:auth:auth-data")
include(":feature:auth:auth-domain")
include(":feature:auth:auth-di")
include(":core:core-domain")
include(":core:core-data")
include(":core:core-data")
include(":core:core-ui")
