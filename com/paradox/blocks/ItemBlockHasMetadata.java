package com.paradox.blocks;

import java.util.List;

import com.paradox.api.IInformationProvider;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockHasMetadata extends ItemBlock{
	
	
	public ItemBlockHasMetadata(Block p_i45328_1_) {
		super(p_i45328_1_);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stk, EntityPlayer p, List lst, boolean held) 
    {
    	if(this.field_150939_a != null && this.field_150939_a instanceof IInformationProvider)
    	{
    		((IInformationProvider)this.field_150939_a).addInformation(stk, p, lst, held);
    	}
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
