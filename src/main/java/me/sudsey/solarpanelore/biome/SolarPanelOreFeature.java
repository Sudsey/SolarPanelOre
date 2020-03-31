package me.sudsey.solarpanelore.biome;

import me.sudsey.solarpanelore.SolarPanelOre;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ObjectHolder;

public class SolarPanelOreFeature {

    private static final int VEIN_SIZE = 3;
    private static final int VEINS_PER_CHUNK = 6;

    private static final int MIN_HEIGHT = 0;
    private static final int MAX_HEIGHT = 32;

    @ObjectHolder(SolarPanelOre.MODID)
    private static class Blocks {
        public static final Block solar_panel_ore = null;
    }

    public static ConfiguredFeature<?, ?> getFeature() {
        OreFeatureConfig config = new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                Blocks.solar_panel_ore.getDefaultState(),
                VEIN_SIZE
        );
        ConfiguredPlacement<?> placement = Placement.COUNT_RANGE.configure(
                new CountRangeConfig(VEINS_PER_CHUNK, MIN_HEIGHT, MIN_HEIGHT, MAX_HEIGHT)
        );

        return Feature.ORE.withConfiguration(config).withPlacement(placement);
    }

}
