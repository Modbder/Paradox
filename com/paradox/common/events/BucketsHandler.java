package com.paradox.common.events;

import com.paradox.blocks.BlockParadoxalLiquid;
import com.paradox.items.ParadoxItems;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;

public class BucketsHandler {
	
	@SubscribeEvent
	public void fillBucket(FillBucketEvent event)
	{
		Block b = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
		if(b instanceof BlockParadoxalLiquid && event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ) == 0)
		{
			ItemStack paradoxBucket = new ItemStack(ParadoxItems.paradoxBucket,1,0);
			event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
			event.result = paradoxBucket;
			event.setResult(Result.ALLOW);
		}
	}

}
