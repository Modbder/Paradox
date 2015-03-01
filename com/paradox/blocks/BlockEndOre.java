package com.paradox.blocks;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockEndOre extends BlockOre{
	
	public static final Random rand = new Random();

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	return Items.ender_pearl;
    }
    
    @Override
    public int getExpDrop(IBlockAccess p_149690_1_, int p_149690_5_, int p_149690_7_)
    {
    	return MathHelper.getRandomIntegerInRange(rand, 3, 7);
    }
}
