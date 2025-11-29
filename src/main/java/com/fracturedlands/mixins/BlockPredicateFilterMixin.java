package com.fracturedlands.mixins;

import com.fracturedlands.config.FracturedConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

/**
 * Mixin to override block predicate filters that check for light/sky access
 * Allows all features to spawn regardless of lighting conditions in cave world
 */
@Mixin(BlockPredicateFilter.class)
public class BlockPredicateFilterMixin {
    
    @Inject(method = "getPositions", at = @At("HEAD"), cancellable = true)
    private void overrideLightChecks(PlacementContext context, RandomSource random, BlockPos pos, CallbackInfoReturnable<Stream<BlockPos>> cir) {
        if (FracturedConfig.IGNORE_LIGHT_CONDITIONS.get() || FracturedConfig.IGNORE_SKY_ACCESS.get()) {
            // Bypass the predicate check and allow the feature to place
            cir.setReturnValue(Stream.of(pos));
        }
    }
}