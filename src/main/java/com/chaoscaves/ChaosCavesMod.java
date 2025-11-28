package com.chaoscaves;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import terrablender.api.Regions;

@Mod("chaoscaves")
public class ChaosCavesMod {
    public static final String MODID = "chaoscaves";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ChaosCavesMod() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        Config.register();
        modEventBus.addListener(this::commonSetup);
        
        LOGGER.info("Chaos Caves initialized");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new ChaosCavesRegion(new ResourceLocation(MODID, "overworld"), 10));
            ChaosCavesSurfaceRules.registerSurfaceRules();
            LOGGER.info("Chaos Caves TerraBlender integration registered");
        });
    }
}
