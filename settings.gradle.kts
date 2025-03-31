pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        // Loom platform
        maven("https://maven.fabricmc.net/")

        // MDG platform
        maven("https://maven.neoforged.net/releases/")

        // Stonecutter
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.6+"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    create(rootProject) {
        fun mc(mcVersion: String, name: String = mcVersion, loaders: List<String>) =
            loaders.forEach { vers("$name-$it", mcVersion) }

        mc("1.21.1", loaders = listOf("fabric", "neoforge"))
        mc("1.21.3", loaders = listOf("fabric", "neoforge"))

        vcsVersion = "1.21.3-fabric"
    }
}

rootProject.name = "MRU"
