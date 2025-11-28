package com.bopcompanion;

import com.bopcompanion.config.CompanionConfig;
import com.bopcompanion.worldgen.CompanionSurfaceRules;
import com.bopcompanion.worldgen.CompanionRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(BoPCompanionMod.MODID)
public class BoPCompanionMod {
    public static final String MODID = "bopcompanion";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public BoPCompanionMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register config
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CompanionConfig.SERVER_CONFIG);
        
        // Setup events
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Register TerraBlender integration
            Regions.register(new CompanionRegion(new ResourceLocation(MODID, "overworld"), 2));
            
            // Register surface rules for proper cave world generation
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, CompanionSurfaceRules.makeRules());
            
            LOGGER.info("BoP Cave Compact Companion initialized - Enhanced biome spawning and structure generation enabled");
        });
    }
}