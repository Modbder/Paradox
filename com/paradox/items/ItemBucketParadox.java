package com.paradox.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBucketParadox extends ItemBucket{

	public ItemBucketParadox(Block b)
	{
		super(b);
	}
	
	@Override
	public void addInformation(ItemStack stk, EntityPlayer p, List lst,
			boolean held) {
		lst.add("Just a regular fluid");
		lst.add("Gives off as much light, as the Glowstone does");
		lst.add("Slowly negates movement within itself");
		lst.add("Internal:");
		lst.add(" *Density:"+EnumChatFormatting.GREEN+" 10000");
		lst.add(" *Temperature:"+EnumChatFormatting.GREEN+"290K(16.85C)");
		lst.add(" *Viscosity:"+EnumChatFormatting.GREEN+"10000");
		lst.add(" *Luminosity:"+EnumChatFormatting.GREEN+"15");
	}

}
