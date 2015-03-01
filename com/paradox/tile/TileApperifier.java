package com.paradox.tile;

import DummyCore.Utils.MathUtils;
import DummyCore.Utils.UnformedItemStack;

import com.paradox.common.utils.ParadoxUtils;
import com.paradox.items.ItemParadoxCard;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileApperifier extends TileParadoxCommon{
	
	public int requiredToCreate;

	public TileApperifier()
	{
		this.setEjectParadox(false);
		this.setAcceptParadox(true);
		this.setMaxParadox(1000);
		this.setSlotsNum(1);
		this.setSlotAccessibleFrom(0, ForgeDirection.VALID_DIRECTIONS);
	}
	
	public boolean canCreateBlock()
	{
		return this.getStackInSlot(0) != null
				&& this.getStackInSlot(0).getItem() instanceof ItemParadoxCard 
				&& (this.worldObj.getBlock(xCoord, yCoord+1, zCoord).isAir(worldObj, xCoord, yCoord+1, zCoord) || (FluidRegistry.lookupFluidForBlock(Block.getBlockFromItem(this.getStackInSlot(0).getItem())) != null)
				&& (this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null && this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IFluidHandler
				&& ((IFluidHandler)this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).fill(ForgeDirection.DOWN, new FluidStack(FluidRegistry.lookupFluidForBlock(Block.getBlockFromItem(this.getStackInSlot(0).getItem())),1000), false) == 1000))
				&& !this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}
	
	public float getRequiredParadoxToCreate()
	{
		return ParadoxUtils.paradoxCosts.get(ParadoxUtils.allRegisteredStacks.get(this.getStackInSlot(0).getItemDamage()));
	}
	
	public void updateEntity()
	{
		if(this.canCreateBlock())
		{
			float required = this.getRequiredParadoxToCreate();
			requiredToCreate = (int) required;
			if(this.getParadox() >= required)
			{
				UnformedItemStack unfIS = ParadoxUtils.allRegisteredStacks.get(this.getStackInSlot(0).getItemDamage());
				ItemStack created = unfIS.getISToDraw(this.worldObj.getWorldTime());
				if(created != null)
				{
					int metadata = created.getItemDamage();
					if(metadata == OreDictionary.WILDCARD_VALUE)
						metadata = 0;
					Item itm = created.getItem();
					if(itm instanceof ItemBlock)
					{
						Block b = Block.getBlockFromItem(itm);
						if(this.increaseParadoxBy(-required))
						{
							this.worldObj.playSound(xCoord+0.5D, yCoord+1.5D, zCoord+0.5D, b.stepSound.getBreakSound(), b.stepSound.getVolume(), b.stepSound.getPitch(), true);
							for(int i = 0; i < 10; ++i)
							{
								this.worldObj.playAuxSFX(xCoord, yCoord+1, zCoord, 2001, Block.getIdFromBlock(b));
							}
							if(FluidRegistry.lookupFluidForBlock(Block.getBlockFromItem(this.getStackInSlot(0).getItem())) == null)
								this.worldObj.setBlock(xCoord, yCoord+1, zCoord, b, metadata, 3);
							else
							{
								IFluidHandler fh = (IFluidHandler) this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
								fh.fill(ForgeDirection.DOWN, new FluidStack(FluidRegistry.lookupFluidForBlock(Block.getBlockFromItem(this.getStackInSlot(0).getItem())),1000), true);
							}
						}
					}
				}
			}
		}else
			requiredToCreate = (int) this.getMaxParadox();
		super.updateEntity();
	}
	
	@Override
	public boolean canInsertParadoxInto(ForgeDirection dir)
	{
		return (dir == ForgeDirection.UP || dir == ForgeDirection.DOWN) && canAcceptParadox && this.canCreateBlock() && this.getParadox() < getRequiredParadoxToCreate();
	}

}
