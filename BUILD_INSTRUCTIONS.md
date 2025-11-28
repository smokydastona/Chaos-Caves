# Chaos Caves - Build Instructions

## Prerequisites

1. **Install Java JDK 17**
   - Download from: https://adoptium.net/temurin/releases/
   - Choose: OpenJDK 17 (LTS)
   - Add to PATH during installation

2. **Install Gradle**
   - Download from: https://gradle.org/releases/
   - Choose version 8.1.1
   - Extract and add to PATH

## Building the Mod

Once Java and Gradle are installed:

```
cd "c:\Users\smoky\OneDrive\Documents\My Minecraft Crap\DataPacks\Completed\tempyfhk\serverdatapacks\Chaos Caves"
gradle wrapper --gradle-version 8.1.1
.\gradlew.bat build
```

The compiled mod will be in: `build\libs\chaoscaves-1.0.0.jar`

## Quick Install (Without Building)

If you don't want to build it yourself, I've created all the necessary files. The mod uses JSON-based density functions which are much more efficient than the datapack version because they're compiled into the mod.

The mod will:
- Create bedrock ceiling at Y 315-320
- Allow caves from Y -64 to Y 300
- Work with all biome mods
- Have better performance than the datapack
- Work server-side only (clients don't need it)
