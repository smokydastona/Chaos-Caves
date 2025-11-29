# Changelog

All notable changes to Fractured Lands will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.1] - 2025-11-28

### Added
- Complete worldgen data from datapack (dimension_type, noise_settings, placed_features)
- 150+ placed_feature files for all trees, vegetation, and structures
- Structure configurations for villages, outposts, temples spawning in caves

### Removed
- TerraBlender dependency (worldgen now fully handled by JSON)
- Redundant Java surface rules (JSON noise_settings handles this)
- Unused worldgen classes (FracturedRegion, FracturedSurfaceRules)

### Changed
- Mod now matches datapack functionality with better performance
- Mixins handle feature spawning instead of TerraBlender

## [1.0.0] - 2025-11-28

### Added
- Initial release
- Cave world dimension with bedrock ceiling
- Mixin system for bypassing light checks
- Server-side configuration
- Structure spawning optimization
