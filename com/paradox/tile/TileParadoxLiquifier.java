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

public class TileParadoxLiquifier extends TileParadoxCommon implements IFluidHandler{
	
	public FluidTank tank = new FluidTank(100);
	
	public TileParadoxLiquifier()
	{
		this.setMaxParadox(100);
		this.setAcceptParadox(true);
		this.setEjectParadox(false);
	}
	
	public void updateEntity()
	{
		if(this.getParadox() > 0)
		{
			if(this.tank.getFluid() == null)
			{
				this.tank.setFluid(new FluidStack(ParadoxBlocks.paradox,0));
			}
			if(this.tank.getFluidAmount() + 1 <= this.tank.getCapacity())
			{
				if(this.increaseParadoxBy(-1))
					this.tank.fill(new FluidStack(ParadoxBlocks.paradox,1), true);
			}
		}
		
		super.updateEntity();
		
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
        return 0;
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
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return from == ForgeDirection.UP;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] { tank.getInfo() };
    }
    

}
