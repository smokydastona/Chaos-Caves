# Changelog

All notable changes to Chaos Caves will be documented in this file.

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
