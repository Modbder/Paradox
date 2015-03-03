package com.paradox.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileRFGenerator extends TileParadoxCommon implements IEnergyHandler{
	
	public EnergyStorage storage;
	
	public int rfToP;
	
	public void setup(int meta)
	{
		this.setAcceptParadox(false);
		this.setEjectParadox(true);
		switch(meta)
		{
			case 0:
			{
				this.setMaxParadox(100);
				storage = new EnergyStorage(32000);
				break;
			}
			case 1:
			{
				this.setMaxParadox(250);
				storage = new EnergyStorage(64000);
				break;
			}
			case 2:
			{
				this.setMaxParadox(500);
				storage = new EnergyStorage(128000);
				break;
			}
			case 3:
			{
				this.setMaxParadox(1000);
				storage = new EnergyStorage(512000);
				break;
			}
		}
	}
	
	public TileRFGenerator()
	{
		this.setAcceptParadox(false);
		this.setEjectParadox(true);
	}
	
	public TileRFGenerator(int meta)
	{
		this.setAcceptParadox(false);
		this.setEjectParadox(true);
		switch(meta)
		{
			case 0:
			{
				this.setMaxParadox(100);
				storage = new EnergyStorage(32000);
				break;
			}
			case 1:
			{
				this.setMaxParadox(250);
				storage = new EnergyStorage(64000);
				break;
			}
			case 2:
			{
				this.setMaxParadox(500);
				storage = new EnergyStorage(128000);
				break;
			}
			case 3:
			{
				this.setMaxParadox(1000);
				storage = new EnergyStorage(512000);
				break;
			}
		}

	}
	
	public int getMetadata()
	{
		return this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {

		return true;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {

		return storage.receiveEnergy(maxReceive, simulate);
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		if(this.storage == null)
			this.setup(nbt.getInteger("meta"));
		storage.readFromNBT(nbt);
		rfToP = nbt.getInteger("rtp");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		nbt.setInteger("meta", this.getMetadata());
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setInteger("rtp", rfToP);
	}
	
	public void updateEntity()
	{
		int currentEnergy = this.storage.getEnergyStored();
		switch(this.getMetadata())
		{
			case 0:
			{
				if(rfToP < 80 && this.storage.extractEnergy(10, true) >= 10 && this.getParadox()+1<=this.getMaxParadox())
				{
					++rfToP;
					this.storage.extractEnergy(10, false);
				}
				if(rfToP >= 80 && this.getParadox() + 1 <= this.getMaxParadox())
				{
					if(this.increaseParadoxBy(1))
						rfToP -= 80;
				}
				break;
			}
			case 1:
			{
				if(rfToP < 40 && this.storage.extractEnergy(60, true) >= 60 && this.getParadox()+3<=this.getMaxParadox())
				{
					++rfToP;
					this.storage.extractEnergy(60, false);
				}
				if(rfToP >= 40 && this.getParadox() + 3 <= this.getMaxParadox())
				{
					if(this.increaseParadoxBy(3))
						rfToP -= 40;
				}
				break;
			}
			case 2:
			{
				if(rfToP < 20 && this.storage.extractEnergy(300, true) >= 300 && this.getParadox()+8<=this.getMaxParadox())
				{
					++rfToP;
					this.storage.extractEnergy(300, false);
				}
				if(rfToP >= 20 && this.getParadox() + 8 <= this.getMaxParadox())
				{
					if(this.increaseParadoxBy(8))
						rfToP -= 20;
				}
				break;
			}
			case 3:
			{
				if(rfToP < 20 && this.storage.extractEnergy(1800, true) >= 1500 && this.getParadox()+40<=this.getMaxParadox())
				{
					++rfToP;
					this.storage.extractEnergy(1800, false);
				}
				if(rfToP >= 20 && this.getParadox() + 40 <= this.getMaxParadox())
				{
					if(this.increaseParadoxBy(40))
						rfToP -= 20;
				}
				break;
			}
		}
		super.updateEntity();
	}

}
