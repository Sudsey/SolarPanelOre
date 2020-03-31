package me.sudsey.solarpanelore.energy;

import net.minecraftforge.energy.IEnergyStorage;

public class SolarPanelStorage implements IEnergyStorage {

    private static final int FE_GENERATED_PER_TICK = 10;
    private static final int FE_MAX_TRANSMITTED_PER_TICK = 10;
    private static final int FE_CAPACITY = 1000;


    private int charge;

    public SolarPanelStorage() {
        this.charge = 0;
    }


    public void setCharge(int charge) {
        this.charge = charge;
    }

    public void tickCharge() {
        int newCharge = charge + FE_GENERATED_PER_TICK;
        charge = Math.min(newCharge, FE_CAPACITY);
    }


    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (maxExtract < 0) {
            return 0;
        }
        int amount = Math.min(charge, Math.min(maxExtract, FE_MAX_TRANSMITTED_PER_TICK));

        charge -= amount;
        return amount;
    }

    @Override
    public int getEnergyStored() {
        return charge;
    }

    @Override
    public int getMaxEnergyStored() {
        return FE_CAPACITY;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

}
