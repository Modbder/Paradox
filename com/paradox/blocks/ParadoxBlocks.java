package com.paradox.blocks;

import com.paradox.common.core.ParadoxCore;

import DummyCore.Blocks.BlocksRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ParadoxBlocks {
	
	public static void initBlocks()
	{
		timer = new BlockParadoxTimer().setBlockName("paradox.timer").setBlockTextureName("paradoxmod:clock").setHardness(10.0F).setLightOpacity(0).setLightLevel(8).setResistance(10).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(timer, "Timer", ParadoxCore.class, ItemBlock.class);
		handyGen = new BlockParadoxHandyGen().setBlockName("paradox.handyGen").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(handyGen, "handyGen", ParadoxCore.class, ItemBlock.class);
		pinkVine = new BlockPinkVine().setBlockName("paradox.pink.pinkvine.pink.pink.pink").setBlockTextureName("paradoxmod:pinkVine").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeGrass);
		BlocksRegistry.registerBlock(pinkVine, "pinkVine", ParadoxCore.class, ItemBlock.class);
		paradox = new Fluid("paradoxalFluid").setDensity(10000).setLuminosity(15).setTemperature(290).setViscosity(10000);
		FluidRegistry.registerFluid(paradox);
		paradoxLiquid = new BlockParadoxalLiquid(paradox, Material.water).setBlockName("paradox.fluid.paradox").setBlockTextureName("paradoxmod:paradox").setBlockUnbreakable().setLightLevel(15F).setTickRandomly(true);
		BlocksRegistry.registerBlock(paradoxLiquid, "paradoxLiquid", ParadoxCore.class, ItemBlock.class);
		paradox.setBlock(paradoxLiquid);
		liquifier = new BlockParadoxLiquifier().setBlockName("paradox.liquifier").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(liquifier, "liquifier", ParadoxCore.class, ItemBlock.class);
		rFGen = new BlockRFGen().setBlockName("paradox.rFGen").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(rFGen, "rFGen", ParadoxCore.class, ItemBlockHasMetadata.class);
		paradoxifier = new BlockParadoxifier().setBlockName("paradox.paradoxifier").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(paradoxifier, "paradoxifier", ParadoxCore.class, ItemBlock.class);
		mruGen = new BlockMRUGenerator().setBlockName("paradox.mruGen").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(mruGen, "mruGen", ParadoxCore.class, ItemBlock.class);
		potentiaGenerator = new BlockPotentiaGen().setBlockName("paradox.potentiaGenerator").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(potentiaGenerator, "potentiaGenerator", ParadoxCore.class, ItemBlock.class);
		secret = new BlockParadoxSecret().setBlockName("paradox.secret").setBlockTextureName("paradoxmod:secret").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(secret, "secret", ParadoxCore.class, ItemBlock.class);
		apperifier = new BlockApperifier().setBlockName("paradox.apperifier").setBlockTextureName("paradoxmod:clock").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeMetal);
		BlocksRegistry.registerBlock(apperifier, "apperifier", ParadoxCore.class, ItemBlock.class);
		endore = new BlockEndOre().setBlockName("paradox.endore").setBlockTextureName("paradoxmod:end_ore").setHardness(1.0F).setLightOpacity(1).setLightLevel(0).setResistance(1).setStepSound(Block.soundTypeStone);
		BlocksRegistry.registerBlock(endore, "endore", ParadoxCore.class, ItemBlock.class);
		endore.setHarvestLevel("pickaxe", 1);
		OreDictionary.registerOre("oreEnderPearl", endore);
	}
	

	
	public static Block 
	timer,
	handyGen,
	pinkVine,
	paradoxLiquid,
	liquifier,
	rFGen,
	paradoxifier,
	mruGen,
	potentiaGenerator,
	secret,
	apperifier,
	endore;

	public static Fluid paradox;
}
