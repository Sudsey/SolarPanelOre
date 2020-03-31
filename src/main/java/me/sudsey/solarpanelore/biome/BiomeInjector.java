package me.sudsey.solarpanelore.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInjector {

    public static void inject() {
        DeferredWorkQueue.runLater(BiomeInjector::doInject);
    }

    private static void doInject() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            injectOres(biome);
        }
    }

    private static void injectOres(Biome biome) {
        biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, SolarPanelOreFeature.getFeature());
    }

}
