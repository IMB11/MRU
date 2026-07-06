@file:Suppress("UnstableApiUsage")

plugins {
    id("dev.kikugie.loom-back-compat")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
}

val minecraft = stonecutter.current.version
val mcVersion = stonecutter.current.project.substringBeforeLast('-')

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version") + "+" + prop("deps.minecraft")
        this["minecraft"] = prop("mod.mc_dep_fabric")
        this["mod_id"] = prop("mod.id")
        this["mod_name"] = prop("mod.name")
        this["mod_description"] = prop("mod.description")
        this["mod_license"] = prop("mod.license")
        this["target_loader"] = prop("deps.fabric_loader")
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }

}

tasks.named("processResources") {
    dependsOn(":${stonecutter.current.project}:stonecutterGenerate")
}

version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
base.archivesName = property("mod.id") as String

//loom {
//    accessWidenerPath = rootProject.file("src/main/resources/${property("mod.id")}.accesswidener")
//}

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    mavenLocal()
    maven {
        name = "shedaniel (Cloth Config)"
        url = uri("https://maven.shedaniel.me/")
        content {
            includeGroupAndSubgroups("me.shedaniel")
        }
    }
    maven {
        name = "Terraformers (Mod Menu)"
        url = uri("https://maven.terraformersmc.com/releases/")
        content {
            includeGroupAndSubgroups("com.terraformersmc")
            includeGroup("dev.emi")
        }
    }
    maven {
        name = "Wisp Forest Maven"
        url = uri("https://maven.wispforest.io/releases/")
        content {
            includeGroupAndSubgroups("io.wispforest")
        }
    }
    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroupAndSubgroups("maven.modrinth")
        }
    }
    maven {
        name = "WTHIT"
        url = uri("https://maven2.bai.lol")
        content {
            includeGroupAndSubgroups("mcp.mobius.waila")
            includeGroupAndSubgroups("lol.bai")
        }
    }
    maven {
        name = "Sisby Maven"
        url = uri("https://repo.sleeping.town/")
        content {
            includeGroupAndSubgroups("folk.sisby")
        }
    }
    maven {
        name = "Parchment Mappings"
        url = uri("https://maven.parchmentmc.org")
        content {
            includeGroupAndSubgroups("org.parchmentmc")
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
    maven {
        name = "Fuzs Mod Resources"
        url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
        content {
            includeGroupAndSubgroups("fuzs")
        }
    }
    maven {
        name = "FzzyMaven"
        url = uri("https://maven.fzzyhmstrs.me/")
        content {
            includeGroup("me.fzzyhmstrs")
        }
    }
    maven {
        name = "Cardinal Components"
        url = uri("https://maven.ladysnake.org/releases")
        content {
            includeGroupAndSubgroups("dev.onyxstudios")
            includeGroupAndSubgroups("org.ladysnake")
        }
    }
    maven {
        name = "Fabricators of Create (Snapshots)"
        url = uri("https://mvn.devos.one/snapshots")
        content {
            includeGroupAndSubgroups("net.createmod")
            includeGroupAndSubgroups("dev.engine-room")
            includeGroupAndSubgroups("io.github.fabricators_of_create")
            includeGroupAndSubgroups("com.simibubi")
        }
    }
    maven {
        name = "Fabricators of Create (Releases)"
        url = uri("https://mvn.devos.one/releases")
        content {
            includeGroupAndSubgroups("net.createmod")
            includeGroupAndSubgroups("dev.engine-room")
            includeGroupAndSubgroups("io.github.fabricators_of_create")
            includeGroupAndSubgroups("com.simibubi")
        }
    }
    repositories {
        exclusiveContent {
            forRepository {
                maven {
                    url = uri("https://cursemaven.com")
                }
            }
            filter {
                includeGroup ("curse.maven")
            }
        }
    }
    maven {
        name = "Nucleoid Maven (Trinkets)"
        url = uri("https://maven.nucleoid.xyz")
        content {
            includeGroupAndSubgroups("eu.pb4")
            includeGroupAndSubgroups("xyz.nucleoid")
        }
    }
    maven {
        name = "NeoForge"
        url = uri("https://maven.neoforged.net/releases/")
        content {
            includeGroupAndSubgroups("net.neoforged")
            includeGroupAndSubgroups("cpw.mods")
            includeGroupAndSubgroups("net.minecraftforge")
        }
    }
    maven {
        name = "Curios"
        url = uri("https://maven.theillusivec4.top/")
        content {
            includeGroup("top.theillusivec4.curios")
        }
    }
    mavenCentral()

}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    loomx.applyMojangMappings()
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")

    implementation("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    if (stonecutter.eval(mcVersion, ">26")) {
        modLocalRuntime("maven.modrinth:mcqoy:0.4.1+fabric-26.1")
    } else {
        modLocalRuntime("maven.modrinth:mcqoy:k8u6AZVM")
    }

    // Cloth Config
    modCompileOnly("me.shedaniel.cloth:cloth-config-fabric:${mod.dep("cloth")}")
    modLocalRuntime("me.shedaniel.cloth:cloth-config-fabric:${mod.dep("cloth")}")
    // YACL
    modCompileOnly("dev.isxander:yet-another-config-lib:${mod.dep("yacl")}")
    modLocalRuntime("dev.isxander:yet-another-config-lib:${mod.dep("yacl")}")
    // Mod Menu
    modCompileOnly("com.terraformersmc:modmenu:${mod.dep("modmenu")}")
    modLocalRuntime("com.terraformersmc:modmenu:${mod.dep("modmenu")}")
    // Map Atlases
    if (stonecutter.eval(mcVersion, ">26")) {
        compileOnly("curse.maven:map-atlases-forge-519759:${mod.dep("map_atlases")}")
    } else if (stonecutter.eval(mcVersion, "<1.21.4")) {
        modCompileOnly("maven.modrinth:map-atlases:${mod.dep("map_atlases")}")
    }
    // Accesories
    if (hasProperty("deps.accessories")) {
        modCompileOnly("io.wispforest:accessories-fabric:${mod.dep("accessories")}")
    } else {
        compileOnly("io.wispforest:accessories-neoforge:1.4.3-beta+1.21.10") {
            isTransitive = false
        }
    }
    if (hasProperty("deps.curios")) {
        compileOnly("top.theillusivec4.curios:curios-neoforge:${mod.dep("curios")}:api")
        compileOnly("net.neoforged:neoforge:${mod.dep("neoforge")}:universal")
    }
    // Tough as Nails
    modCompileOnly("maven.modrinth:travelersbackpack:${mod.dep("travelers_backpack")}")

    // Sophisticated Backpacks
    if (stonecutter.eval(mcVersion, ">26")) {
        compileOnly("maven.modrinth:sophisticated-core:${mod.dep("sophisticated_core")}")
        compileOnly("maven.modrinth:sophisticated-backpacks:${mod.dep("sophisticated_backpacks")}")
    } else if (stonecutter.eval(mcVersion, "<1.21.4")) {
        modCompileOnly("maven.modrinth:9jxwkYQL:${mod.dep("sophisticated_core")}")
        modCompileOnly("maven.modrinth:ouNrBQtq:${mod.dep("sophisticated_backpacks")}")
    }

    modCompileOnly("io.github.fabricators_of_create.Porting-Lib:transfer:2.3.9+1.20.1")

    // Jade
    modCompileOnly("maven.modrinth:jade:${mod.dep("jade")}")
    modLocalRuntime("maven.modrinth:jade:${mod.dep("jade")}")

    // Cardinal Components
    if (hasProperty("deps.cca") && stonecutter.eval(mcVersion, ">1.21")) {
        modCompileOnly("org.ladysnake.cardinal-components-api:cardinal-components-entity:${mod.dep("cca")}")
        modCompileOnly("org.ladysnake.cardinal-components-api:cardinal-components-base:${mod.dep("cca")}")
    }
    else if (stonecutter.eval(mcVersion, ">1.21")) {
        modCompileOnly("org.ladysnake.cardinal-components-api:cardinal-components-entity:6.1.2")
        modCompileOnly("org.ladysnake.cardinal-components-api:cardinal-components-base:6.1.2")
    } else if (stonecutter.eval(mcVersion, "<1.21")) {
        modCompileOnly("dev.onyxstudios.cardinal-components-api:cardinal-components-entity:5.2.3")
        modCompileOnly("dev.onyxstudios.cardinal-components-api:cardinal-components-base:5.2.3")
    }

    // Trinkets
    if (stonecutter.eval(mcVersion, ">26")) {
        compileOnly("eu.pb4:trinkets:${mod.dep("trinkets")}")
    } else if (stonecutter.eval(mcVersion, "<1.21.4")) {
        modCompileOnly("dev.emi:trinkets:${mod.dep("trinkets")}")
    }

    // Ohmega
    modCompileOnly("io.github.swackyy:ohmega-fabric:${mod.dep("ohmega")}-mc${property("deps.minecraft")}")
    modCompileOnly("fuzs.forgeconfigapiport:forgeconfigapiport-fabric:${mod.dep("forge_config_api_port")}")

    if (hasProperty("deps.surveyor")) {
        modLocalRuntime("maven.modrinth:surveyor:${mod.dep("surveyor")}")
    }

    // Backpacked
//    modCompileOnly("curse.maven:backpacked-352835:${mod.dep("backpacked")}")
//    modCompileOnly("curse.maven:framework-549225:${mod.dep("framework")}")

    // Player Locator Bar Backports

    // Mixin Constraints - embedded
//    implementation("com.moulberry:mixinconstraints:1.0.9")
//    include("com.moulberry:mixinconstraints:1.0.9")
    implementation("org.jspecify:jspecify:1.0.0")
}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    }
}

stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.21.10")
        replace("ResourceLocation", "Identifier")
    }
    replacements.string {
        direction = eval(current.version, ">26")
        replace("GuiGraphics", "GuiGraphicsExtractor")
    }
    replacements.string {
        direction = eval(current.version, ">26")
        replace("guiGraphics.drawString", "guiGraphics.text")
    }
}

/*
fabricApi {
    configureDataGeneration() {
        outputDirectory = file("$rootDir/src/main/generated")
        client = true
    }
}
*/

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }


    register<Copy>("buildAndCollect") {
        group = "build"
        from(loomx.modJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }


}

java {
    withSourcesJar()
    val javaCompat = if (stonecutter.eval(stonecutter.current.version, ">26")) {
        JavaVersion.VERSION_25
    } else if (stonecutter.eval(stonecutter.current.version, ">=1.21")) {
        JavaVersion.VERSION_21
    } else {
        JavaVersion.VERSION_17
    }
    sourceCompatibility = javaCompat
    targetCompatibility = javaCompat
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishMods {
    file = loomx.modJar.map { it.archiveFile.get() }
    additionalFiles.from(loomx.modSourcesJar.map { it.archiveFile.get() })

    // one of BETA, ALPHA, STABLE
    type = STABLE
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} Fabric"
    version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("fabric")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env.MODRINTH_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft") as String)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env.CURSEFORGE_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft") as String)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
        client = true
        server = true
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "cc.cassian.mru"
            artifactId = "mru-fabric"
            version = "${property("mod.version")}+${property("deps.minecraft")}"

            from(components["java"])
        }
    }
}