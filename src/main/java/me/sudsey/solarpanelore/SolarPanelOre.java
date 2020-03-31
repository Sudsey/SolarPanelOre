package me.sudsey.solarpanelore;

import me.sudsey.solarpanelore.biome.BiomeInjector;
import me.sudsey.solarpanelore.block.BlockSolarPanel;
import me.sudsey.solarpanelore.block.BlockSolarPanelOre;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod(SolarPanelOre.MODID)
public class SolarPanelOre {

    public static final String MODID = "solarpanelore";

    @ObjectHolder(MODID)
    private static class Blocks {
        public static final BlockSolarPanelOre solar_panel_ore = null;
        public static final BlockSolarPanel solar_panel = null;
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    private static class ModEvents {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    new BlockSolarPanelOre().setRegistryName(BlockSolarPanelOre.NAME),
                    new BlockSolarPanel().setRegistryName(BlockSolarPanel.NAME)
            );
        }

        @SubscribeEvent
        public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().registerAll(
                    Blocks.solar_panel.getTileEntityType().setRegistryName(BlockSolarPanel.NAME)
            );
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                    Blocks.solar_panel_ore.getBlockItem().setRegistryName(BlockSolarPanelOre.NAME),
                    Blocks.solar_panel.getBlockItem().setRegistryName(BlockSolarPanel.NAME)
            );
        }

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            BiomeInjector.inject();
        }
    }

}
