package com.paradox.items;

import java.util.List;

import DummyCore.Utils.UnformedItemStack;

import com.paradox.common.utils.ParadoxUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

public class ItemParadoxCard extends Item{
	
	public ItemParadoxCard()
	{
		this.setHasSubtypes(true);
		this.setMaxStackSize(16);
		this.setMaxDamage(0);
	}
	
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs ct, List lst)
    {
    	for(int j = 0; j < ParadoxUtils.allRegisteredStacks.size(); ++j)
    		lst.add(new ItemStack(i, 1, j));
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stk, EntityPlayer p, List lst, boolean held) 
    {
    	if(ParadoxUtils.allRegisteredStacks.size() > stk.getItemDamage())
    	{
    		UnformedItemStack is = ParadoxUtils.allRegisteredStacks.get(stk.getItemDamage());
    		ItemStack istk = is.getISToDraw(p.ticksExisted);
    		if(istk != null)
    		{
    			lst.add(EnumChatFormatting.BOLD+istk.getDisplayName());
    			lst.add("");
    			lst.add("Paradox Cost: "+EnumChatFormatting.AQUA+MathHelper.floor_float(ParadoxUtils.paradoxCosts.get(is)));
    		}else
    		{
    			if(ParadoxUtils.oreDictionaryNames.containsKey(is))
    			{
    				lst.add(EnumChatFormatting.BOLD+ParadoxUtils.oreDictionaryNames.get(is));
    				lst.add(EnumChatFormatting.RED+"Ore not found!");
        			lst.add("");
        			lst.add("Paradox Cost: "+EnumChatFormatting.AQUA+MathHelper.floor_float(ParadoxUtils.paradoxCosts.get(is)));
    			}
    		}	
    	}
    }

}
