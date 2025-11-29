package com.fracturedlands.mixins;

import com.fracturedlands.config.FracturedConfig;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Mixin to enhance structure spawning in cave world
 * Expands height ranges and spawn conditions for all structures
 */
@Mixin(Structure.class)
public class StructureSpawningMixin {
    
    @ModifyVariable(method = "findValidGenerationPoint", at = @At("HEAD"), argsOnly = true)
    private Heightmap.Types expandHeightmapTypes(Heightmap.Types original) {
        if (FracturedConfig.ENHANCE_BIOME_SPAWNING.get()) {
            // Use WORLD_SURFACE instead of more restrictive heightmaps
            // This allows structures to spawn at any solid surface in caves
            return Heightmap.Types.WORLD_SURFACE;
        }
        return original;
    }
}