package com.chaoscaves;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("chaoscaves")
public class ChaosCavesMod {
    public static final String MODID = "chaoscaves";
    public static final Logger LOGGER = LogManager.getLogger();
    
    private static final DeferredRegister<DensityFunction> DENSITY_FUNCTIONS = 
        DeferredRegister.create(Registries.DENSITY_FUNCTION, MODID);

    public ChaosCavesMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DENSITY_FUNCTIONS.register(modEventBus);
        
        Config.register();
        LOGGER.info("Chaos Caves config registered");
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
    
    public static ResourceKey<DensityFunction> densityKey(String path) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, id(path));
    }
}
