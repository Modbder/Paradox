package com.paradox.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import DummyCore.Utils.DummyDataUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTimeExtender extends Item{
	
    public ItemStack onItemRightClick(ItemStack stk, World w, EntityPlayer p)
    {
    	String worldTimeLeft = DummyDataUtils.getCustomDataForMod("paradoxmod", "timer");
		if(worldTimeLeft == null || worldTimeLeft.equals("no data"))
		{}else
		{
			int leftTime = Integer.parseInt(DummyDataUtils.getCustomDataForMod("paradoxmod", "timer"));
			DummyDataUtils.writeCustomDataForMod("paradoxmod", "timer", Integer.toString(leftTime+72000));
		}
    	return p.inventory.decrStackSize(p.inventory.currentItem, 1);
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stk, EntityPlayer p, List lst, boolean held) 
    {
    	lst.add("Delays the paradox by 1 hour upon usage");
    }

}
