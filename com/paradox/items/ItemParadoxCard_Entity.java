package com.paradox.items;

import java.util.List;

import DummyCore.Utils.UnformedItemStack;

import com.paradox.common.utils.ParadoxUtils;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

public class ItemParadoxCard_Entity extends Item{
	
	public ItemParadoxCard_Entity()
	{
		this.setHasSubtypes(true);
		this.setMaxStackSize(16);
		this.setMaxDamage(0);
	}
	
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs ct, List lst)
    {
    	for(int j = 0; j < ParadoxUtils.allRegisteredEntities.size(); ++j)
    		lst.add(new ItemStack(i, 1, j));
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stk, EntityPlayer p, List lst, boolean held) 
    {
    	if(ParadoxUtils.allRegisteredEntities.size() > stk.getItemDamage())
    	{
    		String cName = ParadoxUtils.entitiesClassMapping.get(stk.getItemDamage());
    		Class eClass = ParadoxUtils.allRegisteredEntities.get(stk.getItemDamage());
    		if(eClass != null)
    		{
    			lst.add(EnumChatFormatting.BOLD+StatCollector.translateToLocal(EntityList.classToStringMapping.get(eClass).toString()));
    			lst.add("");
    			lst.add("Paradox Cost: "+EnumChatFormatting.AQUA+MathHelper.floor_float(ParadoxUtils.entityCosts.get(cName)));
    		}else
    		{
    			if(ParadoxUtils.entityCosts.containsKey(cName))
    			{
    				lst.add(EnumChatFormatting.BOLD+cName);
    				lst.add(EnumChatFormatting.RED+"Entity not found!");
        			lst.add("");
        			lst.add("Paradox Cost: "+EnumChatFormatting.AQUA+MathHelper.floor_float(ParadoxUtils.entityCosts.get(cName)));
    			}
    		}	
    	}
    }

}
