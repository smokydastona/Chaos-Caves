# Chaos Caves - AI Agent Instructions

## Project Overview
Minecraft 1.20.1 Forge mod that transforms the overworld into a cave-only dimension with bedrock ceiling. Uses JSON-based density functions for world generation instead of code-based noise generators.

## Architecture

### World Generation Approach
- **Primary mechanism**: JSON density functions in `src/main/resources/data/chaoscaves/worldgen/density_function/`
- **Key file**: `cave_density.json` - controls terrain generation using nested y_clamped_gradients
  - Bottom gradient (Y -64 to -40): Creates bedrock floor transition
  - Top gradient (Y 280 to 310): Creates bedrock ceiling, prevents caves near roof
  - Uses `minecraft:nether/base_3d_noise` for cave shapes
- **Dimension override**: `data/minecraft/dimension_type/overworld.json` sets `has_ceiling: true`, `has_skylight: false`
- **Noise settings**: `data/minecraft/worldgen/noise_settings/overworld.json` references custom density functions

### Code Organization
- **Minimal Java**: `ChaosCavesMod.java` only registers DeferredRegister for density functions - actual worldgen is JSON-driven
- **No runtime logic**: All terrain generation controlled by data files, not code

## Version Management (CRITICAL)
**ALWAYS update all three files together when making changes:**
1. `build.gradle` - `version = 'X.Y.Z'`
2. `src/main/resources/META-INF/mods.toml` - `version="X.Y.Z"`
3. `CHANGELOG.md` - Add entry with date, type (Added/Changed/Fixed), and description

Version increments automatically trigger GitHub Actions build.

## Build & CI

### Local Build (Won't Work Without Setup)
- Requires Java JDK 17 + Gradle 8.1.1 locally
- Users typically don't build locally - rely on GitHub Actions

### GitHub Actions Build
- **File**: `.github/workflows/build.yml`
- **Critical constraint**: Must use Gradle 8.1.1 (ForgeGradle incompatible with 9.x+)
- **Key config**: `gradle-version: 8.1.1` in setup-gradle action
- **Artifact**: Compiled JAR available in Actions → Workflow run → Artifacts
- **Build time**: ~2-3 minutes

### Build Issues History
- Gradle wrapper files caused large git pushes → removed, use gradle/actions/setup-gradle
- Gradle 9.x breaks ForgeGradle → pin to 8.1.1
- No wrapper JAR in repo by design

## Modifying World Generation

### DO NOT Change These (Breaks Structure Spawning)
- `data/minecraft/worldgen/noise_settings/overworld.json`:
  - Keep `initial_density_without_jaggedness: "minecraft:overworld/sloped_cheese"`
  - This vanilla reference is critical for structure placement detection
  - Only `final_density` should reference custom cave_density

### Change Cave Height Range
Edit `data/chaoscaves/worldgen/density_function/cave_density.json`:
- Bedrock floor: `from_y: -64, to_y: -40` (first y_clamped_gradient)
- Bedrock ceiling: `from_y: 280, to_y: 310` (second y_clamped_gradient)
- Increase upper values to allow caves higher (max Y 320 - 10 blocks for ceiling)

### Change Cave Density/Size
- Modify `argument1: 2.0` values (higher = more solid terrain, smaller caves)
- Change `minecraft:nether/base_3d_noise` to different noise generator

### Add Custom Biome Support
- **Universal compatibility**: Works with ANY biome mod without code changes (Biomes O' Plenty, Terralith, Oh The Biomes You'll Go, etc.)
- **How it works**: Biomes register through Forge's system; mod only overrides dimension type and noise settings
- **Cave conversion**: All biomes automatically become cave versions (terrain generates but caves carve through)
- Surface rules in `noise_settings/overworld.json` handle block placement

### Structure Compatibility
- **Zero configuration needed**: Structures from all mods spawn automatically
- **Key mechanism**: `initial_density_without_jaggedness` in noise_settings references `minecraft:overworld/sloped_cheese`
  - This provides "phantom terrain" that structures detect for placement
  - `final_density` (our cave_density.json) carves caves AFTER structure placement
- **Result**: Villages, temples, dungeons, modded structures all spawn in caves naturally
- **Tested with**: Biomes O' Plenty, Terralith, vanilla structures

## Key Constraints

1. **Forge Version**: 1.20.1-47.3.0 (locked in build.gradle dependencies)
2. **Pack Format**: Must match MC version (currently 15 in mods.toml)
3. **Density Function Syntax**: Must use valid Minecraft worldgen JSON schema
4. **Y-Coordinates**: Min -64, Max 320 (hardcoded world height limits)

## Testing Approach
- No automated tests (standard for Minecraft mods)
- Manual testing: Load in Minecraft client/server
- Validation: `/locate` command to verify structures spawn

## Common Tasks

### Update for New Minecraft Version
1. Change `mappings version` in build.gradle
2. Update `forge` dependency version
3. Update `loaderVersion` in mods.toml
4. Test all density functions (schema may change between versions)

### Performance Optimization
- Simplify nested density functions (fewer operations)
- Avoid `interpolated` or `blend_density` wrappers (expensive)
- Current design already optimized vs original datapack version
