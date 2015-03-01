package com.paradox.common.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import DummyCore.Utils.UnformedItemStack;

import com.paradox.api.IParadoxStorage;
import com.paradox.items.ParadoxItems;
import com.paradox.tile.TileParadoxCommon;

import cpw.mods.fml.common.registry.GameRegistry;
import ec3.api.ITEHasMRU;

public class ParadoxUtils {
	
	public static Hashtable<UnformedItemStack, Float> paradoxCosts = new Hashtable();
	public static Hashtable<UnformedItemStack, String> oreDictionaryNames = new Hashtable();
	public static List<UnformedItemStack> allRegisteredStacks = new ArrayList();
	
	
	public static UnformedItemStack findUnformedISByIS(ItemStack is)
	{
		for(UnformedItemStack stk : allRegisteredStacks)
		{
			if(stk != null && stk.itemStackMatches(is))
				return stk;
		}
		return null;
	}
	
	public static float getParadoxValue(ItemStack is)
	{
		UnformedItemStack stk = findUnformedISByIS(is);
		if(stk != null)
		{
			return paradoxCosts.get(stk);
		}
		return -1;
	}
	
	public static void registerParadoxValueFor(UnformedItemStack is, float value)
	{
		paradoxCosts.put(is, value);
		allRegisteredStacks.add(is);
	}
	
	public static void registerParadoxValueFor(String s, float value)
	{
		UnformedItemStack is = new UnformedItemStack(s);
		paradoxCosts.put(is, value);
		allRegisteredStacks.add(is);
		oreDictionaryNames.put(is, s);
	}
	
	public static void saveInventory(TileParadoxCommon tile, NBTTagCompound saveTag)
	{
		saveTag.setFloat("paradox", tile.getParadox());
		saveTag.setFloat("maxParadox", tile.getMaxParadox());
	}
	
	public static void loadInventory(TileParadoxCommon tile, NBTTagCompound loadTag)
	{
		tile.setParadox(loadTag.getFloat("paradox"));
		tile.setMaxParadox(loadTag.getFloat("maxParadox"));
	}
	
	public static void initAllCardsCrafts()
	{
		for(int i = 0; i < allRegisteredStacks.size(); ++i)
		{
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ParadoxItems.card,2,i), new ItemStack(ParadoxItems.card,1,i),"ingotIron"));
		}
	}
	

}
