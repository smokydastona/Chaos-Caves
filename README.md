# BoP Cave Compact Companion Mod

A Forge server-side mod that enhances the BoP Cave Compact datapack with proper biome spawning and feature generation.

## Features

- **Server-side only** - vanilla clients can connect
- Forces all features to spawn regardless of light/sky requirements  
- Expands structure spawn height ranges for better cave generation
- TerraBlender integration for enhanced worldgen compatibility
- Configurable spawn rates and conditions

## Building

Requirements:
- Java JDK 17
- Gradle (wrapper included)

To build:
```bash
./gradlew build
```

The compiled mod will be in `build/libs/bop-cave-compact-companion-1.1.0.jar`

## Installation

1. Build the mod using instructions above
2. Place the `.jar` file in your server's `mods/` folder  
3. Ensure TerraBlender is also installed
4. Start server - config file will be auto-generated

## Dependencies

- Minecraft Forge 1.20.1+ (47.2.0+)
- TerraBlender 3.0.1.4+

## Configuration

Config file: `world/serverconfig/bopcompanion-server.toml`

Key settings:
- `ignore_light_conditions = true` - Features spawn in dark caves
- `ignore_sky_access = true` - Features don't need sky access  
- `structure_height_min/max` - Y-level range for structures
- `structure_spawn_multiplier` - Increase structure spawn rates

## How It Works

Uses mixins to override:
- `BlockPredicateFilter` - Bypasses light/sky checks for feature placement
- `Structure` - Expands height ranges for structure spawning
- TerraBlender surface rules for proper cave terrain generation

Compatible with all biome mods - BiomesOPlenty, Terralith, Oh The Biomes You'll Go, etc.