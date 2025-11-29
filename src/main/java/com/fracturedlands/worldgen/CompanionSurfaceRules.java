package com.fracturedlands.worldgen;

import com.fracturedlands.config.FracturedConfig;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

/**
 * Surface rules for Fractured Lands cave world generation
 * Creates proper terrain generation and ensures structures can spawn at varied heights
 */
public class FracturedSurfaceRules {
    
    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
            // Bedrock ceiling (solid roof)
            SurfaceRules.ifTrue(
                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(310), 0),
                SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())
            ),
            
            // Bedrock floor (solid bottom)
            SurfaceRules.ifTrue(
                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(-60), 0),
                SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())
            ),
            
            // Enhanced biome-specific surface generation for caves
            // Desert biomes get sand floors in caves
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(net.minecraft.resources.ResourceKey.create(
                    net.minecraft.core.registries.Registries.BIOME,
                    new net.minecraft.resources.ResourceLocation("minecraft", "desert")
                )),
                SurfaceRules.ifTrue(
                    SurfaceRules.ON_FLOOR,
                    SurfaceRules.state(Blocks.SAND.defaultBlockState())
                )
            ),
            
            // Badlands get red sand
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(net.minecraft.resources.ResourceKey.create(
                    net.minecraft.core.registries.Registries.BIOME,
                    new net.minecraft.resources.ResourceLocation("minecraft", "badlands")
                )),
                SurfaceRules.ifTrue(
                    SurfaceRules.ON_FLOOR,
                    SurfaceRules.state(Blocks.RED_SAND.defaultBlockState())
                )
            ),
            
            // Default cave floor is dirt/grass for most biomes
            SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.state(Blocks.GRASS_BLOCK.defaultBlockState())
            ),
            
            // Default cave walls/ceiling are stone
            SurfaceRules.state(Blocks.STONE.defaultBlockState())
        );
    }
}