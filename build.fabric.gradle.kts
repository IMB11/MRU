@file:Suppress("UnstableApiUsage")

plugins {
    id("net.fabricmc.fabric-loom")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
}

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    dependsOn(":${stonecutter.current.project}:stonecutterGenerate")

    val props = HashMap<String, String>().apply {
        this["mod_version"] = "${prop("mod.version")}+26.1"
        this["target_minecraft"] = prop("mod.target")
        this["mod_id"] = "mru"
        this["mod_name"] = "M.R.U"
        this["mod_description"] = "A library mod which provides various utilities for IMB11's mods."
        this["mod_license"] = "ARR"
        this["target_loader"] = prop("deps.fabric_loader")
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }
}

version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
base.archivesName = property("mod.id") as String

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    maven {
        name = "Terraformers (Mod Menu)"
        url = uri("https://maven.terraformersmc.com/releases/")
        content {
            includeGroupAndSubgroups("com.terraformersmc")
            includeGroupAndSubgroups("dev.emi")
        }
    }
    maven {
        name = "Xander Maven"
        url = uri("https://maven.isxander.dev/releases")
        content {
            includeGroupAndSubgroups("dev.isxander")
            includeGroupAndSubgroups("org.quiltmc.parsers")
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")

    implementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    implementation("net.fabricmc.fabric-api:fabric-api:${property("runtime.fabric_api")}")

    compileOnly("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-neoforge")
    compileOnly("com.terraformersmc:modmenu:${property("deps.mod_menu")}")

}

fabricApi {
    configureDataGeneration() {
        outputDirectory = file("$rootDir/src/main/generated")
        client = true
    }
}

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(jar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

java {
    withSourcesJar()
    val javaCompat = JavaVersion.VERSION_25
    sourceCompatibility = javaCompat
    targetCompatibility = javaCompat
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishing {
    repositories {
        maven {
            name = "mineblockMaven"
            url = uri("https://maven.imb11.dev/releases")
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "dev.imb11"
            artifactId = "mru"
        }
    }
}