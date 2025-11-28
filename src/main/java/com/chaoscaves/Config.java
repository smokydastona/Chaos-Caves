package com.chaoscaves;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
    public static final ForgeConfigSpec SERVER_CONFIG;
    
    public static final ForgeConfigSpec.IntValue CAVE_FLOOR_START;
    public static final ForgeConfigSpec.IntValue CAVE_FLOOR_END;
    public static final ForgeConfigSpec.IntValue CAVE_CEILING_START;
    public static final ForgeConfigSpec.IntValue CAVE_CEILING_END;
    public static final ForgeConfigSpec.BooleanValue ENABLE_AQUIFERS;
    public static final ForgeConfigSpec.BooleanValue HAS_CEILING;
    public static final ForgeConfigSpec.BooleanValue HAS_SKYLIGHT;
    public static final ForgeConfigSpec.DoubleValue AMBIENT_LIGHT;
    
    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("Cave World Generation Settings")
               .push("cave_generation");
        
        CAVE_FLOOR_START = builder
            .comment("Y-level where cave floor gradient starts (solid below this)")
            .defineInRange("cave_floor_start", -64, -64, 320);
        
        CAVE_FLOOR_END = builder
            .comment("Y-level where cave floor gradient ends (caves above this)")
            .defineInRange("cave_floor_end", -40, -64, 320);
        
        CAVE_CEILING_START = builder
            .comment("Y-level where cave ceiling gradient starts (caves below this)")
            .defineInRange("cave_ceiling_start", 280, -64, 320);
        
        CAVE_CEILING_END = builder
            .comment("Y-level where cave ceiling gradient ends (solid above this)")
            .defineInRange("cave_ceiling_end", 310, -64, 320);
        
        ENABLE_AQUIFERS = builder
            .comment("Enable underground water aquifers (may impact performance)")
            .define("enable_aquifers", false);
        
        builder.pop();
        
        builder.comment("Dimension Settings")
               .push("dimension");
        
        HAS_CEILING = builder
            .comment("Enable bedrock ceiling at world height")
            .define("has_ceiling", true);
        
        HAS_SKYLIGHT = builder
            .comment("Enable skylight (false = always dark like Nether)")
            .define("has_skylight", false);
        
        AMBIENT_LIGHT = builder
            .comment("Ambient light level (0.0 = pitch black, 1.0 = full bright)")
            .defineInRange("ambient_light", 0.1, 0.0, 1.0);
        
        builder.pop();
        
        SERVER_CONFIG = builder.build();
    }
    
    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG, "chaoscaves-server.toml");
    }
}
