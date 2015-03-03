package com.paradox.api;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Modbder
 * @Description Used to add information for blocks, that use the {@link com.paradox.blocks.ItemBlockHasMetadata} as their ItemBlocks.
 */
public interface IInformationProvider {
	
	public void addInformation(ItemStack stk, EntityPlayer p, List lst, boolean held);

}
