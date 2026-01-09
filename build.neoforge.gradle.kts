plugins {
    id("net.neoforged.moddev")
    id ("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
}

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["mod_version"] = "${prop("mod.version")}+1.21.10"
        this["target_minecraft"] = prop("mod.target")
        this["mod_id"] = "mru"
        this["mod_name"] = "M.R.U"
        this["mod_description"] = "A library mod which provides various utilities for IMB11's mods."
        this["mod_license"] = "ARR"
        this["loader"] = "neoforge"
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }
}

version = "${property("mod.version")}+${property("deps.minecraft")}-neoforge"
base.archivesName = property("mod.id") as String

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    maven {
        name = "Xander Maven"
        url = uri("https://maven.isxander.dev/releases")
        content {
            includeGroupAndSubgroups("org.quiltmc.parsers")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-neoforge")
}

neoForge {
    version = property("deps.neoforge") as String
    validateAccessTransformers = true

    if (hasProperty("deps.parchment")) parchment {
        val (mc, ver) = (property("deps.parchment") as String).split(':')
        mappingsVersion = ver
        minecraftVersion = mc
    }

    runs {
        register("client") {
            gameDirectory = file("run/")
            client()
        }
        register("server") {
            gameDirectory = file("run/")
            server()
        }
    }

    mods {
        register(property("mod.id") as String) {
            sourceSet(sourceSets["main"])
        }
    }
    sourceSets["main"].resources.srcDir("src/main/generated")
}

tasks {
    processResources {
        exclude("**/fabric.mod.json", "**/*.accesswidener", "**/mods.toml")
    }

    named("createMinecraftArtifacts") {
        dependsOn("stonecutterGenerate")
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