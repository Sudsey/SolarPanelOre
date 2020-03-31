package me.sudsey.solarpanelore.block;

import me.sudsey.solarpanelore.entity.TileEntitySolarPanel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class BlockSolarPanel extends Block {

    public static final String NAME = "solar_panel";


    public BlockSolarPanel() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(1.5F, 6.0F)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1));
    }


    public TileEntityType<?> getTileEntityType() {
        return TileEntityType.Builder.create(TileEntitySolarPanel::new, this).build(null);
    }

    public BlockItem getBlockItem() {
        return new BlockItem(this, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntitySolarPanel();
    }

}
