package com.paradox.common;

import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import DummyCore.Utils.UnformedItemStack;

import com.paradox.common.utils.ParadoxUtils;

public class CardLibrary {
	
	public static void load()
	{
		OreDictionary.registerOre("dirt", Blocks.dirt);
		OreDictionary.registerOre("blockLava", Blocks.lava);
		OreDictionary.registerOre("blockGlowstone", Blocks.glowstone);
		OreDictionary.registerOre("blockNetherrack", Blocks.netherrack);
		OreDictionary.registerOre("blockEndstone", Blocks.end_stone);
		OreDictionary.registerOre("blockSoulSand", Blocks.soul_sand);
		ParadoxUtils.registerParadoxValueFor("dirt", 2);
		ParadoxUtils.registerParadoxValueFor("cobblestone", 3);
		ParadoxUtils.registerParadoxValueFor("marble", 5);
		ParadoxUtils.registerParadoxValueFor("basalt", 5);
		ParadoxUtils.registerParadoxValueFor("oreCoal", 60);
		ParadoxUtils.registerParadoxValueFor("oreCopper", 70);
		ParadoxUtils.registerParadoxValueFor("oreCertusQuartz", 100);
		ParadoxUtils.registerParadoxValueFor("orePoorIron", 9);
		ParadoxUtils.registerParadoxValueFor("orePoorCopper", 7);
		ParadoxUtils.registerParadoxValueFor("orePoorTin", 10);
		ParadoxUtils.registerParadoxValueFor("orePoorGold", 15);
		ParadoxUtils.registerParadoxValueFor("orePoorLead", 9);
		ParadoxUtils.registerParadoxValueFor("oreIron", 90);
		ParadoxUtils.registerParadoxValueFor("oreCinnabar", 100);
		ParadoxUtils.registerParadoxValueFor("oreAmber", 110);
		ParadoxUtils.registerParadoxValueFor("oreZinc", 110);
		ParadoxUtils.registerParadoxValueFor("oreLead", 90);
		ParadoxUtils.registerParadoxValueFor("oreTin", 100);
		ParadoxUtils.registerParadoxValueFor("oreQuartz", 150);
		ParadoxUtils.registerParadoxValueFor("oreInfusedFire", 150);
		ParadoxUtils.registerParadoxValueFor("oreInfusedWater", 150);
		ParadoxUtils.registerParadoxValueFor("oreInfusedEarth", 150);
		ParadoxUtils.registerParadoxValueFor("oreInfusedAir", 150);
		ParadoxUtils.registerParadoxValueFor("oreInfusedOrder", 150);
		ParadoxUtils.registerParadoxValueFor("oreInfusedEntropy", 150);
		ParadoxUtils.registerParadoxValueFor("oreGold", 150);
		ParadoxUtils.registerParadoxValueFor("blockLava", 30, new FluidStack(FluidRegistry.LAVA,1000));
		ParadoxUtils.registerParadoxValueFor("oreSilver", 155);
		ParadoxUtils.registerParadoxValueFor("oreSulfur", 130);
		ParadoxUtils.registerParadoxValueFor("oreSaltpeter", 130);
		ParadoxUtils.registerParadoxValueFor("oreLapis", 200);
		ParadoxUtils.registerParadoxValueFor("oreYellorium", 400);
		ParadoxUtils.registerParadoxValueFor("oreNickel", 180);
		ParadoxUtils.registerParadoxValueFor("oreRedstone", 50);
		ParadoxUtils.registerParadoxValueFor("blockGlowstone", 150);
		ParadoxUtils.registerParadoxValueFor("oreDiamond", 350);
		ParadoxUtils.registerParadoxValueFor("oreAmethyst", 350);
		ParadoxUtils.registerParadoxValueFor("oreRuby", 350);
		ParadoxUtils.registerParadoxValueFor("oreSapphire", 350);
		ParadoxUtils.registerParadoxValueFor("oreTungsten", 400);
		ParadoxUtils.registerParadoxValueFor("oreEmerald", 410);
		ParadoxUtils.registerParadoxValueFor("oreFirestone", 800);
		ParadoxUtils.registerParadoxValueFor("blockNetherrack", 10);
		ParadoxUtils.registerParadoxValueFor("blockEndstone", 100);
		ParadoxUtils.registerParadoxValueFor("blockSoulSand", 30);
		ParadoxUtils.registerParadoxValueFor("oreEnderPearl", 35);
	}

}
