package com.paradox.items;

import DummyCore.Items.ItemRegistry;

import com.paradox.blocks.ParadoxBlocks;
import com.paradox.common.core.ParadoxCore;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ParadoxItems {
	
	public static void initItems()
	{
		paradoxBucket = new ItemBucketParadox(ParadoxBlocks.paradoxLiquid).setUnlocalizedName("paradox.bucketParadox").setTextureName("paradoxmod:bucket_paradox");
		ItemRegistry.registerItem(paradoxBucket, "paradoxBucket", ParadoxCore.class);
		FluidContainerRegistry.registerFluidContainer(new FluidStack(ParadoxBlocks.paradox, 1000), new ItemStack(paradoxBucket),new ItemStack(Items.bucket));
		card = new ItemParadoxCard().setUnlocalizedName("paradox.card").setTextureName("paradoxmod:card");
		ItemRegistry.registerItem(card, "paradoxCard", ParadoxCore.class);
		extender = new ItemTimeExtender().setUnlocalizedName("paradox.extender").setTextureName("paradoxmod:timeExtender").setMaxStackSize(1);
		ItemRegistry.registerItem(extender, "extender", ParadoxCore.class);
	}
	
	public static Item
	paradoxBucket,
	card,
	extender;

}
