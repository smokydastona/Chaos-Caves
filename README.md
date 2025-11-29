# Fractured Lands

A lightweight Forge mod + datapack combo that transforms the overworld into a massive cave system with optimized performance.

## 🎯 Two-Part System

### **1. Fractured Lands Mod** (Performance Layer)
- Bypasses light/sky checks for features to spawn in caves
- Optimizes structure placement at varied heights
- Server-side only - vanilla clients can join
- Configurable spawn rates and conditions

### **2. Fractured Lands Datapack** (Worldgen Layer)
- Modifies overworld dimension (bedrock ceiling, no skylight)
- Configures noise settings for cave terrain generation
- All biome features, structures, and vegetation
- Can be toggled on/off without reinstalling mod

## 📦 Installation

### Both Required for Full Functionality:

**Mod Installation:**
1. Download `fractured-lands-X.X.X.jar` from releases
2. Place in your server's `mods/` folder
3. Start server (config auto-generates)

**Datapack Installation:**
1. Extract the `fractured-lands-worldgen` datapack
2. Place in `saves/[WorldName]/datapacks/`
3. **Create a NEW world** (existing worlds won't change)

## 🔧 Dependencies

- **Minecraft Forge 1.20.1+** (47.2.0+)
- **No other mods required!** (removed TerraBlender dependency)

## ⚙️ Configuration

Config file: `world/serverconfig/fracturedlands-server.toml`

```toml
[feature_spawning]
  # Force features to spawn without light/sky requirements
  ignore_light_conditions = true
  ignore_sky_access = true
  force_vanilla_features = true
  force_mod_features = true

[structure_spawning]
  # Y-level range for structures in caves
  structure_height_min = -60
  structure_height_max = 300
  # Spawn rate multiplier (1.0 = normal)
  structure_spawn_multiplier = 1.5
```

## 🌍 What Gets Changed?

**Dimension Properties (via datapack):**
- Bedrock ceiling at Y=310
- No natural skylight (always dark)
- Ambient light at 0.1 (dim cave lighting)

**Feature Spawning (via mod):**
- Trees grow underground
- Grass, flowers, and vegetation spawn in caves
- Structures generate at any height
- All biomes work in cave format

## 🔌 Compatibility

✅ **Works with ALL biome mods:**
- Biomes O' Plenty
- Terralith
- Oh The Biomes You'll Go
- Any other worldgen mods

✅ **Vanilla clients can join** (server-side mod)

## 🏗️ Building from Source

```bash
git clone https://github.com/smokydastona/Chaos-Caves.git
cd Chaos-Caves
./gradlew build
```

Output: `build/libs/fractured-lands-X.X.X.jar`

## 💡 Why Two Parts?

- **Mod** = Performance optimization (runs on server, handles heavy lifting)
- **Datapack** = Worldgen changes (easy to toggle, customize, or replace)

This separation means:
- Better performance than pure datapacks
- Easier to update worldgen without recompiling mod
- Users can disable cave world without uninstalling mod
- Compatible with other worldgen datapacks