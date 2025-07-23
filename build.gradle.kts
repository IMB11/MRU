plugins {
    id("dev.isxander.modstitch.base") version "+"
    id("maven-publish")
}

val loader = when {
    modstitch.isLoom -> "fabric"
    modstitch.isModDevGradleRegular -> "neoforge"
    modstitch.isModDevGradleLegacy -> "forge"
    else -> throw IllegalStateException("Unsupported loader")
}

val mcVersion = property("deps.minecraft") as String

modstitch {
    minecraftVersion = mcVersion as String?

    metadata {
        modId = "mru"
        modName = "M.R.U"
        modVersion = "${property("mod.version") as String}+${mcVersion}+${loader}"
        modGroup = "dev.imb11"
        modDescription =
            "A library mod which provides various utilities for IMB11's mods."
        modLicense = "ARR"

        replacementProperties.put("pack_format", "63")

        replacementProperties.put("target_minecraft", property("mod.target") as String)
        replacementProperties.put("loader", loader)
        replacementProperties.put("target_loader", when (loader) {
            "fabric" -> property("deps.fabric_loader") as String
            "neoforge" -> property("fml.target") as String
            else -> ""
        })
    }

    loom {
        fabricLoaderVersion = property("deps.fabric_loader") as String?

        configureLoom {
            runs {
                all {
                    runDir = "../../run"
                    ideConfigGenerated(true)
                }
            }
        }
    }

    moddevgradle {
        neoForgeVersion = findProperty("deps.neoforge") as String?

        defaultRuns()
    }
}

stonecutter {
    consts(
        "fabric" to modstitch.isLoom,
        "neoforge" to modstitch.isModDevGradleRegular,
        "forgelike" to modstitch.isModDevGradle,
    )
}

dependencies {
    modstitch.loom {
        modstitchModCompileOnly("net.fabricmc.fabric-api:fabric-api:${property("runtime.fabric_api")}")
        modstitchModCompileOnly("maven.modrinth:modmenu:${property("deps.mod_menu")}")
    }

    modstitchModCompileOnly("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-${loader}")

    "org.commonmark:commonmark:0.21.0".let {
        modstitchImplementation(it)
        modstitchJiJ(it)
    }
}

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

fun createActiveTask(
    taskProvider: TaskProvider<*>? = null,
    taskName: String? = null,
    internal: Boolean = false
): String {
    val taskExists = taskProvider != null || taskName!! in tasks.names
    val task = taskProvider ?: taskName?.takeIf { taskExists }?.let { tasks.named(it) }
    val taskName = when {
        taskProvider != null -> taskProvider.name
        taskName != null -> taskName
        else -> error("Either taskProvider or taskName must be provided")
    }
    val activeTaskName = "${taskName}Active"

    if (stonecutter.current.isActive) {
        rootProject.tasks.register(activeTaskName) {
            group = "sounds${if (internal) "/versioned" else ""}"

            task?.let { dependsOn(it) }
        }
    }

    return activeTaskName
}

val buildAndCollect by tasks.registering(Copy::class) {
    group = "build"

    dependsOn(modstitch.finalJarTask)
    from(modstitch.finalJarTask.flatMap { it.archiveFile })

    into(rootProject.layout.buildDirectory.dir("finalJars"))
}
createActiveTask(buildAndCollect)