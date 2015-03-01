package com.paradox.tile;

import com.paradox.blocks.ParadoxBlocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileParadoxifier extends TileParadoxCommon implements IFluidHandler{
	
	public FluidTank tank = new FluidTank(100);
	
	public TileParadoxifier()
	{
		this.setMaxParadox(100);
		this.setAcceptParadox(false);
		this.setEjectParadox(true);
	}
	
	public void updateEntity()
	{
		float requiredParadox = this.getMaxParadox()-this.getParadox();
		if(this.getParadox() < this.getMaxParadox())
		{
			if(this.tank.getFluid() == null)
			{
				this.tank.setFluid(new FluidStack(ParadoxBlocks.paradox,0));
			}
			int fA = this.tank.getFluidAmount();
			if(this.tank.getFluidAmount() - requiredParadox  >= 0)
			{
				if(this.increaseParadoxBy(requiredParadox))
					this.tank.drain((int) requiredParadox, true);
			}
			if(this.getParadox() + fA <= this.getMaxParadox())
			{
				if(this.increaseParadoxBy(fA))
					this.tank.drain(fA, true);
			}
		}
		
		super.updateEntity();
		
		/*
		TileEntity t = this.worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
		{
			if(t instanceof IFluidHandler)
			{
				IFluidHandler tile = (IFluidHandler) t;
				if(this.tank != null && this.tank.getFluid() != null && this.tank.getFluidAmount() >= 1 && tile.canFill(ForgeDirection.DOWN, this.tank.getFluid().getFluid()))
					{
						if(tile.fill(ForgeDirection.DOWN, new FluidStack(this.tank.getFluid().getFluid(),this.tank.getFluidAmount()), false) > 0)
						{
							this.tank.drain(tile.fill(ForgeDirection.DOWN, new FluidStack(this.tank.getFluid().getFluid(),this.tank.getFluidAmount()), true), true);
						}
					}
			}
		}
		*/
	}
	
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
        if(tag.hasKey("empty"))
        	this.tank.setFluid(null);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
        {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] { tank.getInfo() };
    }
    

}
