package me.sudsey.solarpanelore.entity;

import me.sudsey.solarpanelore.SolarPanelOre;
import me.sudsey.solarpanelore.energy.SolarPanelStorage;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntitySolarPanel extends TileEntity implements ITickableTileEntity, ICapabilityProvider {

    private static final String NBT_KEY_CHARGE = "StorageCharge";

    @ObjectHolder(SolarPanelOre.MODID)
    private static class TileEntityTypes {
        public static final TileEntityType<TileEntitySolarPanel> solar_panel = null;
    }


    private SolarPanelStorage storage;

    public TileEntitySolarPanel() {
        super(TileEntityTypes.solar_panel);

        storage = new SolarPanelStorage();
    }


    @Override
    public void read(CompoundNBT compound) {
        storage.setCharge(compound.getInt(NBT_KEY_CHARGE));
        super.read(compound);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt(NBT_KEY_CHARGE, storage.getEnergyStored());
        return super.write(compound);
    }


    @Override
    public void tick() {
        if (world == null || world.isRemote) {
            return;
        }

        if (world.canSeeSky(this.pos.up()) && world.isDaytime()) {
            storage.tickCharge();
        }

        // TODO: Efficiency

        for (Direction direction : Direction.values()) {
            BlockPos side = this.pos.offset(direction);
            TileEntity entity = this.world.getTileEntity(side);

            if (entity == null) {
                continue;
            }

            entity.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite())
                    .ifPresent(this::dischargeToExternal);
        }
    }

    private void dischargeToExternal(IEnergyStorage external) {
        int maxAccepted = external.receiveEnergy(storage.getEnergyStored(), true);

        int amount = storage.extractEnergy(maxAccepted, false);
        external.receiveEnergy(amount, false);
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return LazyOptional.of(() -> storage).cast();
        }
        return LazyOptional.empty();
    }

}
