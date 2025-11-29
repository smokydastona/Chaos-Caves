package com.fracturedlands;

import com.fracturedlands.config.FracturedConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(FracturedLandsMod.MODID)
public class FracturedLandsMod {
    public static final String MODID = "fracturedlands";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public FracturedLandsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register config
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, FracturedConfig.SERVER_CONFIG);
        
        // Setup events
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Worldgen is handled via JSON data files in resources/data/minecraft/
            // Mixins handle feature spawning overrides
            LOGGER.info("Fractured Lands initialized - Cave world generation via JSON worldgen data");
        });
    }
}