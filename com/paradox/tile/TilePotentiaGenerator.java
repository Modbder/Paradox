package com.paradox.tile;

import essentialThaumaturgy.common.init.AspectsInit;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TilePotentiaGenerator extends TileParadoxCommon implements IAspectContainer, IEssentiaTransport{
	public int cooldown = 0;
	public AspectList aspects = new AspectList();
	public int maxAspects = 64;
	
	@Override
    public void readFromNBT(NBTTagCompound i)
    {
		super.readFromNBT(i);
		aspects.readFromNBT(i);
		cooldown = i.getInteger("cooldown");
    }
	
	@Override
    public void writeToNBT(NBTTagCompound i)
    {
    	super.writeToNBT(i);
		aspects.writeToNBT(i);
		i.setInteger("cooldown", cooldown);
    }
	
	public TilePotentiaGenerator()
	{
		this.setEjectParadox(true);
		this.setAcceptParadox(false);
		this.setMaxParadox(100);
	}
	
	@Override
	public boolean isConnectable(ForgeDirection face) {
		return face == ForgeDirection.DOWN;
	}

	@Override
	public boolean canInputFrom(ForgeDirection face) {
		return face == ForgeDirection.DOWN;
	}
	
	@Override
	public boolean canOutputTo(ForgeDirection face) {
		return false;
	}
	
	@Override
	public void setSuction(Aspect aspect, int amount) {
		
	}

	@Override
	public Aspect getSuctionType(ForgeDirection face) {
		return face == ForgeDirection.DOWN ? Aspect.ENERGY : null;
	}

	@Override
	public int getSuctionAmount(ForgeDirection face) {
		return face == ForgeDirection.DOWN ? 64 : 0;
	}
	
	@Override
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
		
		return 0;
	}

	@Override
	public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
		return this.addToContainer(aspect, amount);
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection face) {
		return face == ForgeDirection.DOWN ? Aspect.ENERGY : null;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection face) {
		if(this.aspects.size() != 0)
			return this.aspects.getAmount(this.aspects.getAspectsSortedAmount()[0]);
		else
			return 0;
	}

	@Override
	public int getMinimumSuction() {
		return 0;
	}

	@Override
	public boolean renderExtendedTube() {
		return true;
	}

	@Override
	public AspectList getAspects() {
		return this.aspects;
	}

	@Override
	public void setAspects(AspectList aspects) {
		this.aspects = aspects;
		
	}

	@Override
	public boolean doesContainerAccept(Aspect tag) {
		return this.aspects.getAmount(tag) < this.maxAspects;
	}

	@Override
	public int addToContainer(Aspect tag, int amount) {
		if(amount > maxAspects)
			amount = maxAspects;
		if(this.aspects.getAmount(tag) == 0)
		{
			this.aspects.add(tag, amount);
			return amount;
		}else
		{
			if(this.aspects.getAmount(tag)+amount < maxAspects)
			{
				this.aspects.merge(tag, this.aspects.getAmount(tag)+amount);
				return amount;
			}
		}
		return 0;
	}

	@Override
	public boolean takeFromContainer(Aspect tag, int amount) {
		if(this.aspects.getAmount(tag) > 0 && this.aspects.getAmount(tag)-amount >= 0)
		{
			this.aspects.reduce(tag, amount);
			return true;
		}
		return false;
	}

	@Override
	public boolean takeFromContainer(AspectList ot) {
		if(this.doesContainerContain(ot))
		{
			for(Aspect apt : ot.getAspectsSortedAmount())
			{
				this.takeFromContainer(apt, ot.getAmount(apt));
			}
		}
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount) {
		return this.aspects.getAmount(tag) == amount;
	}

	@Override
	public boolean doesContainerContain(AspectList ot) {
		for(int o = 0; o < ot.size(); ++o)
		{
			Aspect apt = ot.getAspectsSortedAmount()[o];
			if(this.aspects.getAmount(apt) == 0)
				return false;
		}
		return true;
	}

	@Override
	public int containerContains(Aspect tag) {
		return this.aspects.getAmount(tag);
	}
	
	public void updateEntity()
	{
		if(this.getAspects().getAmount(Aspect.ENERGY) > 0 && this.cooldown <= 0 && this.getParadox() + 3 <= this.getMaxParadox())
		{
			if(this.takeFromContainer(Aspect.ENERGY, 1))
			{
				this.cooldown = 80;
			}
		}

		if(this.cooldown % 20 == 0)
		{
			this.increaseParadoxBy(1);
		}
		if(this.cooldown >= 0)--this.cooldown;
		super.updateEntity();
		ForgeDirection facing = ForgeDirection.DOWN;
		TileEntity te = ThaumcraftApiHelper.getConnectableTile(getWorldObj(), xCoord, yCoord, zCoord, facing);
		if(te != null)
		{
			IEssentiaTransport ic = (IEssentiaTransport) te;
            if(!ic.canOutputTo(facing.getOpposite()))
            {
            	
            }else
	            if(this.getAspects().getAmount(Aspect.ENERGY)+1 < this.maxAspects && ic.getSuctionAmount(facing.getOpposite()) < getSuctionAmount(facing) && ic.takeEssentia(Aspect.ENERGY, 1, facing.getOpposite()) == 1)
	            {
	            	this.addEssentia(Aspect.ENERGY, 1, facing);
	            }
		}
	}
	
	public void click(EntityPlayer player)
	{
		if(cooldown <= 0)
		{
			if(player.getFoodStats().getFoodLevel() > 3.0F && this.increaseParadoxBy(1))
			{
				cooldown = 100;
				player.addExhaustion(10F);
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 300, 3, true));
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300, 3, true));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 300, 3, true));
				
				for(int i = 0; i < 20; ++i)
					player.worldObj.playSound(player.posX, player.posY, player.posZ, "random.door_open", 1, i/10F, true);
			}
		}
	}
}
