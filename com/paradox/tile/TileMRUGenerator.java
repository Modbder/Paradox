package com.paradox.tile;

import java.util.UUID;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.MiscUtils;
import ec3.api.IMRUStorage;
import ec3.api.ITEHasMRU;
import ec3.api.ITERequiresMRU;
import ec3.common.item.ItemBoundGem;
import ec3.utils.common.ECUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileMRUGenerator extends TileParadoxCommon implements ITERequiresMRU{
	public int genTime = 0;
	int mru;
	int maxMRU = 100;
	UUID uuid = UUID.randomUUID();
	float balance;
	
	public TileMRUGenerator()
	{
		this.setEjectParadox(true);
		this.setAcceptParadox(false);
		this.setMaxParadox(100);
		this.setMaxMRU(5000);
		this.setSlotsNum(1);
		this.setSlotAccessibleFrom(0, ForgeDirection.VALID_DIRECTIONS);
	}
	
	public void updateEntity()
	{
		super.updateEntity();
		ECUtils.mruIn(this, 0);
		ECUtils.spawnMRUParticles(this, 0);
		IInventory inv = this;
		int slotNum = 0;
		TileEntity tile = this;
		if(inv.getStackInSlot(slotNum) != null && inv.getStackInSlot(slotNum).getItem() instanceof ItemBoundGem && inv.getStackInSlot(slotNum).getTagCompound() != null)
		{
			ItemStack s = inv.getStackInSlot(slotNum);
			int[] o = ItemBoundGem.getCoords(s);
			if(MathUtils.getDifference(tile.xCoord, o[0]) <= 16 && MathUtils.getDifference(tile.yCoord, o[1]) <= 16 && MathUtils.getDifference(tile.zCoord, o[2]) <= 16)
			{
    			if(tile.getWorldObj().getTileEntity(o[0], o[1], o[2]) != null && tile.getWorldObj().getTileEntity(o[0], o[1], o[2]) instanceof ITEHasMRU)
    			{
    				this.setBalance(((ITEHasMRU) tile.getWorldObj().getTileEntity(o[0], o[1], o[2])).getBalance());
    			}
    		}
		}
		if(this.getMRU() - 100 >= 0 && this.genTime < 20 && this.getParadox() + 1 <= this.getMaxParadox())
		{
			this.setMRU(this.getMRU()-100);
			++this.genTime;
		}
		if(this.genTime >= 20 && this.getParadox() + 1 <= this.getMaxParadox())
		{
			if(this.increaseParadoxBy(1))
				this.genTime = 0;
		}
	}
	
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		genTime = i.getInteger("generatorTime");
		super.readFromNBT(i);
		ECUtils.loadMRUState(this, i);
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
		i.setInteger("generatorTime", genTime);
    	super.writeToNBT(i);
    	ECUtils.saveMRUState(this, i);
    }
	
	@Override
	public int getMRU() {
		// TODO Auto-generated method stub
		return mru;
	}

	@Override
	public int getMaxMRU() {
		// TODO Auto-generated method stub
		return maxMRU;
	}

	@Override
	public boolean setMRU(int i) {
		mru = i;
		return true;
	}

	@Override
	public float getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	@Override
	public boolean setBalance(float f) {
		balance = f;
		return true;
	}

	@Override
	public boolean setMaxMRU(float f) {
		maxMRU = (int) f;
		return true;
	}

	@Override
	public UUID getUUID() {
		// TODO Auto-generated method stub
		return uuid;
	}
}
