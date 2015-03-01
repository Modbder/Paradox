package com.paradox.common;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class VoidChunkProviderOverworld extends ChunkProviderGenerate
{

	public VoidChunkProviderOverworld(World p_i2006_1_, long p_i2006_2_,boolean p_i2006_4_)
	{
		super(p_i2006_1_, p_i2006_2_, p_i2006_4_);
	}
	
	@Override
    public void func_147424_a(int p_147424_1_, int p_147424_2_, Block[] p_147424_3_)
    {
    	
    }
	
	@Override
    public void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
    {
    	
    }

}
