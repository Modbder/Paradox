package com.paradox.tile;

import java.util.ArrayList;
import java.util.Arrays;

import DummyCore.Utils.MiscUtils;

import com.paradox.api.IParadoxStorage;
import com.paradox.common.utils.ParadoxUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileParadoxCommon extends TileEntity implements IParadoxStorage, IInventory, ISidedInventory{
	
	public ArrayList<ArrayList<Integer>> slotsFromSize = new ArrayList();
	public int syncTick;
	public ArrayList<Integer> notAccessible = new ArrayList();
	
	public float paradox, maxParadox;
	private ItemStack[] items = new ItemStack[1];
	
	public boolean canAcceptParadox = false, canEjectParadox = false;
	
	public void setAcceptParadox(boolean b)
	{
		canAcceptParadox = b;
	}
	
	public void setEjectParadox(boolean b)
	{
		canEjectParadox = b;
	}
	
	public void setSlotsNum(int i)
	{
		items = new ItemStack[i];
	}
	
	public void setSlotAccessibleFrom(int slot, ForgeDirection... dir)
	{
		if(slotsFromSize.isEmpty())
		{
			for(int i = 0; i < 6; ++i)
			{
				slotsFromSize.add(new ArrayList<Integer>());
			}
		}
		
		for(ForgeDirection d : dir)
		{
			slotsFromSize.get(d.ordinal()).add(slot);
		}
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) 
	{
		if(slotsFromSize.isEmpty())
		{
			for(int i = 0; i < 6; ++i)
			{
				slotsFromSize.add(new ArrayList<Integer>());
			}
		}
		ArrayList<Integer> slots = slotsFromSize.get(side);
		int[] retInt = new int[]{};
		if(slots.isEmpty())
		{
			retInt = new int[this.getSizeInventory()];
			for(int i = 0; i < this.getSizeInventory(); ++i)
			{
				retInt[i] = i;
			}
			for(int i = 0; i < this.notAccessible.size(); ++i)
			{
				retInt[this.notAccessible.get(i)] = 0;
			}
		}else
		{
			retInt = new int[slots.size()];
			for(int i = 0; i < retInt.length; ++i)
			{
				retInt[i] = slots.get(i);
			}
		}
		return retInt;
	}

	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		super.readFromNBT(i);
		ParadoxUtils.loadInventory(this,i);
		MiscUtils.loadInventory(this, i);
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
    	super.writeToNBT(i);
    	ParadoxUtils.saveInventory(this,i);
    	MiscUtils.saveInventory(this, i);
    }
	
	public void updateEntity() 
	{
		if(canEjectParadox)
		{
			for(int i = 0; i < 6; ++i)
			{
				ForgeDirection dir = ForgeDirection.getOrientation(i);
				TileEntity tile = this.worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
				if(tile instanceof IParadoxStorage)
				{
					if(((IParadoxStorage) tile).canInsertParadoxInto(dir.getOpposite()))
					{
						IParadoxStorage storage = (IParadoxStorage) tile;
						float leftoverParadox = (storage.getMaxParadox() - storage.getParadox())/10;
						if(this.getParadox()-leftoverParadox >= 0)
						{
							if(storage.increaseParadoxBy(leftoverParadox))
								this.increaseParadoxBy(-leftoverParadox);
						}else
						{
							float addedParadox = this.getParadox();
							if(storage.increaseParadoxBy(addedParadox))
								this.increaseParadoxBy(-addedParadox);
						}
					}
				}
			}
		}
		
		if(syncTick == 0)
		{
			if(!this.worldObj.isRemote)
			{
				MiscUtils.sendPacketToAllAround(worldObj, getDescriptionPacket(), xCoord, yCoord, zCoord, this.worldObj.provider.dimensionId, 128);
			}
			syncTick = 10;
		}else
			--this.syncTick;
	}
	
	@Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -10, nbttagcompound);
    }
	
	@Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
		if(net.getNetHandler() instanceof INetHandlerPlayClient)
			if(pkt.func_148853_f() == -10)
				this.readFromNBT(pkt.func_148857_g());
    }
	
	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_,
			int p_102007_3_) {
		return this.isItemValidForSlot(p_102007_1_, p_102007_2_);
	}
	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_,
			int p_102008_3_) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return this.items.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1) {
		return this.items[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
        if (this.items[par1] != null)
        {
            ItemStack itemstack;

            if (this.items[par1].stackSize <= par2)
            {
                itemstack = this.items[par1];
                this.items[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.items[par1].splitStack(par2);

                if (this.items[par1].stackSize == 0)
                {
                    this.items[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.items[par1] != null)
        {
            ItemStack itemstack = this.items[par1];
            this.items[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
        this.items[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInventoryName()
	{
		return "genericInvName";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
	{
		return p_70300_1_.dimension == this.worldObj.provider.dimensionId;
	}

	@Override
	public void openInventory() 
	{
		
	}

	@Override
	public void closeInventory()
	{
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
	}

	@Override
	public float getParadox() 
	{
		return this.paradox;
	}

	@Override
	public boolean canEjectParadoxFrom(ForgeDirection dir) 
	{
		return dir != ForgeDirection.UP && dir != ForgeDirection.DOWN;
	}

	@Override
	public boolean canInsertParadoxInto(ForgeDirection dir)
	{
		return (dir == ForgeDirection.UP || dir == ForgeDirection.DOWN) && canAcceptParadox;
	}

	@Override
	public boolean setParadox(float newAmount) 
	{
		if(newAmount >= 0 && newAmount <= this.getMaxParadox())
		{
			this.paradox = newAmount;
			return true;
		}
		return false;
	}

	@Override
	public boolean increaseParadoxBy(float increasedBy) 
	{
		if(this.getParadox()+increasedBy <= this.getMaxParadox() && this.getParadox()+increasedBy >= 0)
		{
			return this.setParadox(this.getParadox()+increasedBy);
		}
		return false;
	}

	@Override
	public float getMaxParadox() {
		return this.maxParadox;
	}

	@Override
	public boolean setMaxParadox(float newAmount) {
		this.maxParadox = newAmount;
		return true;
	}

}
