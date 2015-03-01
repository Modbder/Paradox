package com.paradox.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockParadoxalLiquid extends BlockFluidClassic
{

	public BlockParadoxalLiquid(Fluid fluid, Material material)
	{
		super(fluid, material);
	}
	
    @Override
    public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec)
    {
        if (densityDir > 0) return;
        Vec3 vec_flow = this.getFlowVector(world, x, y, z);
        vec.xCoord += vec_flow.xCoord * (quantaPerBlock * 4);
        vec.yCoord += vec_flow.yCoord * (quantaPerBlock * 4);
        vec.zCoord += vec_flow.zCoord * (quantaPerBlock * 4);
        entity.motionX += vec.xCoord/10000;
        entity.motionY += vec.yCoord/10000;
        entity.motionZ += vec.zCoord/10000;
    }
    
    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) 
    {
    	
    }
    
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    	super.registerBlockIcons(p_149651_1_);
    	this.stack.getFluid().setIcons(blockIcon, blockIcon);
    }
    

}
