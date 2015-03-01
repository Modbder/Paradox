package com.paradox.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class VoidProviderOverworld extends WorldProvider
{

	@Override
	public String getDimensionName() {
		// TODO Auto-generated method stub
		return "Overworld";
	}
	
	@Override
    public IChunkProvider createChunkGenerator()
    {
    	return new VoidChunkProviderOverworld(this.worldObj,this.getSeed(),false);
    }

    public float getSunBrightnessFactor(float par1)
    {
        return 1F;
    }
    
    @SideOnly(Side.CLIENT)
    public float getSunBrightness(float par1)
    {
        return 1F;
    }
}
