package com.chaoscaves;

import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.SurfaceRuleManager;

public class ChaosCavesSurfaceRules {
    public static void registerSurfaceRules() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, "chaoscaves", 
            SurfaceRules.sequence(
                // Bedrock floor
                SurfaceRules.ifTrue(
                    SurfaceRules.verticalGradient("minecraft:bedrock_floor", 
                        SurfaceRules.yBlockCheck(SurfaceRules.LazyYCheck.AboveBottom.INSTANCE, 0),
                        SurfaceRules.yBlockCheck(SurfaceRules.LazyYCheck.AboveBottom.INSTANCE, 5)),
                    SurfaceRules.state(net.minecraft.world.level.block.Blocks.BEDROCK.defaultBlockState())
                ),
                // Bedrock ceiling
                SurfaceRules.ifTrue(
                    SurfaceRules.verticalGradient("minecraft:bedrock_roof",
                        SurfaceRules.yBlockCheck(SurfaceRules.LazyYCheck.BelowTop.INSTANCE, 5),
                        SurfaceRules.yBlockCheck(SurfaceRules.LazyYCheck.BelowTop.INSTANCE, 0)),
                    SurfaceRules.state(net.minecraft.world.level.block.Blocks.BEDROCK.defaultBlockState())
                )
            )
        );
    }
}
