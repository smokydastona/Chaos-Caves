# Changelog

All notable changes to Chaos Caves will be documented in this file.

## [1.0.13] - 2025-11-27
### Added
- Biome-specific surface blocks (grass, sand, dirt, etc.)
- Restored vanilla biome features (trees, grass, flowers, structures)

### Changed
- Removed dimension_type override to allow normal sky and biome features
- Enhanced surface_rule with biome-specific terrain (deserts=sand, badlands=red_sand, etc.)

## [1.0.12] - 2025-11-27
### Fixed
- Fixed unbound density function references in noise_router (replaced vanilla references with constants)

## [1.0.11] - 2025-11-27
### Fixed
- Fixed critical typo: "beds_work" → "bed_works" in dimension_type (was causing registry loading crash)

## [1.0.10] - 2025-11-27
### Fixed
- Simplified pack.mcmeta to match Biomes O' Plenty structure exactly

## [1.0.9] - 2025-11-27
### Fixed
- Added proper pack.mcmeta with Forge-specific format tags to eliminate resource pack warnings

## [1.0.8] - 2025-11-27
### Fixed
- Removed pack.mcmeta causing invalid datapack detection blocking world creation

## [1.0.7] - 2025-11-27
### Added
- Configuration file support (chaoscaves-server.toml)
- Configurable cave floor/ceiling heights
- Configurable aquifer toggle
- Configurable dimension settings (ceiling, skylight, ambient light)

## [1.0.6] - 2025-11-27
### Fixed
- Added missing pack.mcmeta to fix "invalid resource pack" error

## [1.0.5] - 2025-11-27
### Added
- Documentation for biome mod compatibility (Biomes O' Plenty, Terralith)
- Documentation for structure spawning mechanism

## [1.0.4] - 2025-11-27
### Fixed
- Set Gradle version to 8.1.1 (ForgeGradle doesn't support Gradle 9.x yet)

## [1.0.3] - 2025-11-27
### Changed
- Use Gradle setup action in GitHub Actions (no wrapper files needed)

## [1.0.2] - 2025-11-27
### Fixed
- Updated GitHub Actions workflow to use v4 of actions (v3 was deprecated)

## [1.0.1] - 2025-11-27
### Changed
- Implemented version tracking system for organized releases

## [1.0.0] - 2025-11-27
### Added
- Initial release
- Cave-only overworld with bedrock ceiling at Y 315-320
- Caves extend from Y -64 to Y 300
- Compatible with all biome mods (Biomes O' Plenty, Terralith, etc.)
- Optimized density functions for better performance than datapack
- Server-side compatible
- Structures and features spawn at cave floor level
