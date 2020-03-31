package me.sudsey.solarpanelore.block;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class BlockSolarPanelOre extends Block {

    public static final String NAME = "solar_panel_ore";


    public BlockSolarPanelOre() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0F, 3.0F)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2));
    }


    public BlockItem getBlockItem() {
        return new BlockItem(this, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
    }

}
