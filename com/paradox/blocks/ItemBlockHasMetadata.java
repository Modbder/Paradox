package com.paradox.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockHasMetadata extends ItemBlock{
	
	
	public ItemBlockHasMetadata(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

    public String getUnlocalizedName(ItemStack p_77667_1_)
    {
        return this.field_150939_a.getUnlocalizedName()+p_77667_1_.getItemDamage();
    }
    
	@Override
	public int getMetadata(int i)
	{
		return i;
	}
	
	

}
