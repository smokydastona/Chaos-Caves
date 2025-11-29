# Versioning Guide

This project uses **automatic semantic versioning** based on commit messages.

## How It Works

When you push to `main`, GitHub Actions automatically:
1. Reads your commit message
2. Determines the version bump type
3. Updates `build.gradle` with the new version
4. Builds the JAR with the new version number

## Commit Message Format

Use [Conventional Commits](https://www.conventionalcommits.org/) format:

```
<type>(<scope>): <description>

[optional body]
```

### Version Bump Rules

| Commit Prefix | Version Bump | Example |
|--------------|--------------|---------|
| `feat:` or `feature:` | **Minor** (1.0.0 → 1.1.0) | `feat: add new biome support` |
| `fix:` or `bugfix:` | **Patch** (1.0.0 → 1.0.1) | `fix: structure spawning in deserts` |
| `feat!:` or `BREAKING` | **Major** (1.0.0 → 2.0.0) | `feat!: remove legacy config format` |
| Anything else | **Patch** (1.0.0 → 1.0.1) | `update README` |

### Examples

**Add a new feature (minor version bump):**
```bash
git commit -m "feat: add custom cave biome generation"
# 1.0.1 → 1.1.0
```

**Fix a bug (patch version bump):**
```bash
git commit -m "fix: trees not spawning in snow biomes"
# 1.0.1 → 1.0.2
```

**Breaking change (major version bump):**
```bash
git commit -m "feat!: change config file format to TOML"
# 1.0.1 → 2.0.0
```

**Optional scope:**
```bash
git commit -m "feat(worldgen): improve cave density functions"
git commit -m "fix(mixins): block predicate filter not applying"
```

## Manual Version Override

If you need to manually set a version, edit `build.gradle`:
```gradle
version = '1.2.3'
```

Then commit with a descriptive message (it will still auto-increment on next push).

## Viewing Version History

Check [CHANGELOG.md](CHANGELOG.md) for version history and changes.
