package com.paradox.common.utils;

import java.util.List;

import org.lwjgl.input.Keyboard;

import thaumcraft.api.aspects.Aspect;
import cofh.api.energy.IEnergyHandler;

import com.paradox.api.IInformationProvider;
import com.paradox.api.IParadoxStorage;
import com.paradox.blocks.BlockApperifier;
import com.paradox.blocks.BlockPinkVine;
import com.paradox.common.core.ParadoxCore;
import com.paradox.items.ItemParadoxCard;
import com.paradox.items.ItemParadoxCard_Entity;
import com.paradox.tile.TileApperifier;
import com.paradox.tile.TileHandyGenerator;
import com.paradox.tile.TilePotentiaGenerator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import ec3.integration.waila.WailaDataProvider;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WAILAHandler implements IWailaDataProvider{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		// TODO Auto-generated method stub
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		if(accessor.getBlock() instanceof BlockPinkVine)
		{
			currenttip.add("Growth: "+MathHelper.floor_float((float)accessor.getMetadata()/8F*100F)+"%");
		}
		if(accessor.getTileEntity() instanceof IParadoxStorage)
		{
			IParadoxStorage storage = (IParadoxStorage)accessor.getTileEntity();
			Side s = FMLCommonHandler.instance().getEffectiveSide();
			if(s == Side.CLIENT)
			{
				int current = MathHelper.floor_float(storage.getParadox());
				int max = accessor.getBlock() instanceof BlockApperifier ? ((TileApperifier)accessor.getTileEntity()).requiredToCreate : MathHelper.floor_float(storage.getMaxParadox());
				if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()))
				{
					
				}else
				{
					currenttip.add("Paradox: "+EnumChatFormatting.GOLD+current+EnumChatFormatting.RESET+"/"+EnumChatFormatting.GOLD+max);
					if(accessor.getBlock() instanceof BlockApperifier)
					{
						TileApperifier a = (TileApperifier)accessor.getTileEntity();
						if(a.getStackInSlot(0) != null && (a.getStackInSlot(0).getItem() instanceof ItemParadoxCard || a.getStackInSlot(0).getItem() instanceof ItemParadoxCard_Entity))
						{
							Item itm = a.getStackInSlot(0).getItem();
							itm.addInformation(a.getStackInSlot(0), Minecraft.getMinecraft().thePlayer, currenttip, false);
						}
						
					}
					if(accessor.getTileEntity() instanceof IFluidHandler)
					{
						IFluidHandler fh = (IFluidHandler)accessor.getTileEntity();
						FluidTankInfo fti = fh.getTankInfo(ForgeDirection.UNKNOWN)[0];
						if(fti.fluid == null)
							currenttip.add("Empty");
						else
						{
							currenttip.add(fti.fluid.getLocalizedName() + ": " + EnumChatFormatting.BLUE + fti.fluid.amount+EnumChatFormatting.RESET+"/"+ EnumChatFormatting.BLUE + fti.capacity);
						}
					}
					if(accessor.getTileEntity() instanceof TileHandyGenerator)
					{
						TileHandyGenerator thd = (TileHandyGenerator) accessor.getTileEntity();
						if(thd.cooldown > 0)
							currenttip.add("Cooldown: "+ EnumChatFormatting.AQUA+thd.cooldown/20+"s");
						else
							currenttip.add("Cooldown: "+ EnumChatFormatting.AQUA+"Ready");
					}
					if(accessor.getTileEntity() instanceof IEnergyHandler)
					{
						IEnergyHandler eh = (IEnergyHandler) accessor.getTileEntity();
						currenttip.add("RF: " + EnumChatFormatting.DARK_RED + eh.getEnergyStored(ForgeDirection.UNKNOWN)+EnumChatFormatting.RESET+"/"+ EnumChatFormatting.DARK_RED + eh.getMaxEnergyStored(ForgeDirection.UNKNOWN));
					}
					if(accessor.getTileEntity() instanceof TilePotentiaGenerator)
					{
						TilePotentiaGenerator tpg = (TilePotentiaGenerator) accessor.getTileEntity();
						currenttip.add("Potentia: " + EnumChatFormatting.LIGHT_PURPLE + tpg.getAspects().getAmount(Aspect.ENERGY)+EnumChatFormatting.RESET+"/"+ EnumChatFormatting.LIGHT_PURPLE + tpg.maxAspects);
					}
				}
			}
		}
		if(accessor.getBlock() instanceof IInformationProvider)
		{
			Side s = FMLCommonHandler.instance().getEffectiveSide();
			if(s == Side.CLIENT)
			{
				if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()))
				{
					IInformationProvider prov = (IInformationProvider) accessor.getBlock();
					prov.addInformation(itemStack, null, currenttip, false);
				}else
				{
					currenttip.add(EnumChatFormatting.BLUE+""+EnumChatFormatting.ITALIC+"Hold "+StatCollector.translateToLocal(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyDescription())+" to view info");
				}
			}
		}

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack,
			List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config) {
		// TODO Auto-generated method stub
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te,
			NBTTagCompound tag, World world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return tag;
	}
	
	public static void callbackRegister(IWailaRegistrar registrar)
	{
		registrar.registerBodyProvider(new WAILAHandler(), Block.class);
	}

}
