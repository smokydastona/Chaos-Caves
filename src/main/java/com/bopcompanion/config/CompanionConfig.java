package com.bopcompanion.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CompanionConfig {
    public static final ForgeConfigSpec SERVER_CONFIG;
    
    public static final ForgeConfigSpec.BooleanValue ENHANCE_BIOME_SPAWNING;
    public static final ForgeConfigSpec.BooleanValue IGNORE_LIGHT_CONDITIONS;
    public static final ForgeConfigSpec.BooleanValue IGNORE_SKY_ACCESS;
    public static final ForgeConfigSpec.IntValue STRUCTURE_HEIGHT_MIN;
    public static final ForgeConfigSpec.IntValue STRUCTURE_HEIGHT_MAX;
    public static final ForgeConfigSpec.DoubleValue STRUCTURE_SPAWN_MULTIPLIER;
    public static final ForgeConfigSpec.BooleanValue FORCE_VANILLA_FEATURES;
    public static final ForgeConfigSpec.BooleanValue FORCE_MOD_FEATURES;
    
    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("BoP Cave Compact Companion - Enhanced spawning and generation settings")
               .push("biome_enhancement");
        
        ENHANCE_BIOME_SPAWNING = builder
            .comment("Enable enhanced biome spawning to ensure all biomes work in cave world")
            .define("enhance_biome_spawning", true);
        
        builder.pop().push("feature_spawning");
        
        IGNORE_LIGHT_CONDITIONS = builder
            .comment("Force all features to spawn regardless of light level requirements")
            .define("ignore_light_conditions", true);
        
        IGNORE_SKY_ACCESS = builder
            .comment("Force all features to spawn regardless of sky access requirements")
            .define("ignore_sky_access", true);
        
        FORCE_VANILLA_FEATURES = builder
            .comment("Force vanilla features (trees, grass, flowers) to spawn in caves")
            .define("force_vanilla_features", true);
        
        FORCE_MOD_FEATURES = builder
            .comment("Force modded features (BoP vegetation, etc.) to spawn in caves")
            .define("force_mod_features", true);
        
        builder.pop().push("structure_spawning");
        
        STRUCTURE_HEIGHT_MIN = builder
            .comment("Minimum Y-level for structure spawning (-64 to 320)")
            .defineInRange("structure_height_min", -60, -64, 320);
        
        STRUCTURE_HEIGHT_MAX = builder
            .comment("Maximum Y-level for structure spawning (-64 to 320)")  
            .defineInRange("structure_height_max", 300, -64, 320);
        
        STRUCTURE_SPAWN_MULTIPLIER = builder
            .comment("Structure spawn rate multiplier (1.0 = normal, 2.0 = double spawns)")
            .defineInRange("structure_spawn_multiplier", 1.5, 0.1, 5.0);
        
        builder.pop();
        
        SERVER_CONFIG = builder.build();
    }
}