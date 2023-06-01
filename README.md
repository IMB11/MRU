# Mineblock's Repeated Utilities

This library contains useful features such as:

- Automatic Compatability Entrypoints
- UI Colour standards.
- String/Math utilities.
- Other misc. stuff used by my mods.

## Installation

```groovy
maven {
    url "https://maven.mineblock11.dev/releases"
}

dependencies {
    modImplementation "mine.block:MRU:2.0.0+1.20"
}
```

## Automatic Compat. Entrypoints

MRU calls the `mru-compat-X` (where X is the mod ID) for all mods that are loaded by fabric.

See the test mod for an example on how to use it.

