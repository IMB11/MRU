# mine11lib

mine11lib (formerly known as minelib) is a utility library used by most of my mods.

The following features are available:

- Packet Helpers
- Texture Data Generation
- Image Filters

### Installation

![](https://maven.mineblock11.dev/api/badge/latest/releases/mine/block/mine11lib/?color=8c3e39&name=Latest%20Maven)

Add the following to your build.gradle

```groovy
repositories {
    maven {
        name "mineblockMaven"
        url "https://maven.mineblock11.dev/releases"
    }
}

dependencies {
    // Do not "include" mine11lib, it will not work.
    modImplementation "mine.block:minelib:${project.mine11lib_version}"
}
```

For documentation, [see the minedocs page here.](https://docs.mineblock11.dev/documentation/mine11lib/)
