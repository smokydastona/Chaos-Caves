# BoP Fractured World DataPack - AI Coding Agent Instructions

## Architecture Overview

This is a complex **Minecraft DataPack** that merges multiple mod ecosystems into a unified world generation system:

- **BiomesOPlenty**: Surface biomes with custom vegetation, structures, and features
- **Better Cave Worlds**: Enhanced underground terrain generation using custom noise functions
- **Cave Worlds Remastered**: Additional underground structures and features
- **Custom Integration**: Underground biome caves and jigsaw structure systems

## Critical Namespace Architecture

**Multi-namespace strategy prevents feature order cycles:**

```
data/
├── minecraft/           # Vanilla overrides (dimension, biome_source, noise_settings)
├── biomesoplenty/       # BoP biomes, features, structures
├── biomesoplenty_complete/  # Custom underground structures (.nbt files)
├── better_cave_worlds/  # Terrain generation (density functions, noise)
└── cave/               # Cave Worlds features (placed_feature/, template_pool/)
```

## Version Compatibility System

Uses **overlay system** for Minecraft version support:
- Base: `pack_format: 15` (MC 1.20.1)
- `overlay_1_20_5/`: formats 41-999
- `overlay_1_21_4/`: formats 61-999 (adds pale garden features)
- `overlay_1_21_5/`: formats 71-999 (leaf litter features)
- `overlay_1_21_9/`: formats 88-999 (preliminary_surface_level density function)

## World Generation Flow

1. **Noise Settings** (`data/minecraft/worldgen/noise_settings/overworld.json`) references `better_cave_worlds:` density functions
2. **Biome Source** (`data/minecraft/dimension/overworld.json`) uses multi_noise with BoP biomes distributed across climate parameters
3. **Features** are carefully ordered to avoid circular dependencies (never mix `biomesoplenty:` and `cave:` features in same biome)
4. **Underground Structures** use jigsaw pools (`biomesoplenty_complete:underground_forest`) with `.nbt` structure files

## Critical Development Patterns

### Feature Order Dependencies
```json
// NEVER mix these namespaces in the same biome features array
"features": [
  ["biomesoplenty:feature"], // ✓ OK
  ["minecraft:feature"],     // ✓ OK  
  ["cave:feature"]          // ✗ CAUSES CYCLES with BoP features
]
```

### Terrain Height Configuration
- Modified `better_cave_worlds:overworld/final_density` for Y=200 terrain ceiling
- Base terrain multiplier: 0.85, height targets: Y=180-200
- Uses `initial_density_without_jaggedness` (pre-1.21.9) vs `preliminary_surface_level` (1.21.9+)

### Structure Integration
- Underground caves use **jigsaw systems**: `biomesoplenty_complete:underground_forest`
- NBT files in `biomesoplenty_complete/structures/`: `mystic_grove_caves.nbt`, `redwood_caves.nbt`, etc.
- Structure step: `vegetal_decoration`, size: 6, max_distance: 80

## Testing & Debugging

**Feature Order Cycles**: Most common crash - check for mixed `biomesoplenty:` + `cave:` feature references
**Terrain Issues**: Verify `final_density.json` modifications and noise_router references
**Structure Spawning**: Confirm biome tags match structure conditions (`#biomesoplenty_complete:has_cave_biome/underground_forest`)

## Key Files for Understanding System

- `pack.mcmeta`: Version overlay configuration
- `data/minecraft/dimension/overworld.json`: Biome distribution via multi_noise
- `data/minecraft/worldgen/noise_settings/overworld.json`: Terrain generation backbone
- `data/better_cave_worlds/worldgen/density_function/overworld/final_density.json`: Height modifications
- `data/biomesoplenty_complete/worldgen/structure/*.json`: Underground cave systems

## Development Workflows

When modifying biomes: Always check feature references don't create namespace cycles
When adding structures: Create matching `.nbt` in `biomesoplenty_complete/structures/`  
When updating versions: Test overlay compatibility and add version-specific features to appropriate overlay directories

## Automatic Versioning System

**GitHub Actions automatically manages version numbers:**
- **Patch** (1.0.0 → 1.0.1): Default for all commits to main branch
- **Minor** (1.0.0 → 1.1.0): Include `[minor]` in commit message  
- **Major** (1.0.0 → 2.0.0): Include `[major]` in commit message
- **Skip versioning**: Include `[skip-version]` in commit message

**Version locations updated automatically:**
- `VERSION` file: Tracks semantic version (1.2.3)
- `pack.mcmeta`: Updates description with version number
- Git tags: Creates `v1.2.3` tags for releases
- GitHub Releases: Auto-generates release with zip download

**Manual version control:**
- Use "Actions" tab → "Auto Version & Release" → "Run workflow" for manual version bumps