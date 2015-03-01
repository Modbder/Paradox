package com.paradox.client;

import com.paradox.common.core.ParadoxCore;

import DummyCore.Events.DummyEvent_OnPacketRecieved;
import DummyCore.Utils.DataStorage;
import DummyCore.Utils.DummyData;
import DummyCore.Utils.DummyDataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class ParadoxClientEventHandler {
	
	@SubscribeEvent
	public void clientTick(ClientTickEvent event)
	{
		World w = Minecraft.getMinecraft().theWorld;
		if(w != null)
		{
			if(w.provider.getSkyRenderer() == null && ParadoxCore.enableWorldSkyChange)
			{
				w.provider.setSkyRenderer(ParadoxSkyRenderer.getRenderer());
				w.provider.setCloudRenderer(ParadoxCloudRenderer.getRenderer());
				w.provider.setWeatherRenderer(ParadoxWeatherRenderer.getRenderer());
			}else
			{
				if(w.provider.dimensionId == 0 && w.provider.getSkyRenderer() != null && !ParadoxCore.enableWorldSkyChange)
				{
					w.provider.setSkyRenderer(null);
					w.provider.setCloudRenderer(null);
					w.provider.setWeatherRenderer(null);
				}
			}
			String worldTimeLeft = DummyDataUtils.getCustomDataForMod("paradoxmod", "timer");
			if(worldTimeLeft == null || worldTimeLeft.equals("no data"))
			{
				
			}else
			{
				int leftTime = Integer.parseInt(DummyDataUtils.getCustomDataForMod("paradoxmod", "timer"));
				if(leftTime <= 0 && (Minecraft.getMinecraft().currentScreen == null || !(Minecraft.getMinecraft().currentScreen instanceof GuiEnding_Bad) && !(Minecraft.getMinecraft().currentScreen instanceof GuiGameOver) && !(Minecraft.getMinecraft().currentScreen instanceof GuiYesNo)))
				{
					Minecraft.getMinecraft().displayGuiScreen(new GuiEnding_Bad());
				}
			}
			if(event.phase == Phase.START)
			{
				if(worldTimeLeft == null || worldTimeLeft.equals("no data"))
				{
					DummyDataUtils.writeCustomDataForMod("paradoxmod", "timer", Integer.toString(ParadoxCore.ticksToFinish));
				}else
				{
					int leftTime = Integer.parseInt(DummyDataUtils.getCustomDataForMod("paradoxmod", "timer"));
					DummyDataUtils.writeCustomDataForMod("paradoxmod", "timer", Integer.toString(leftTime-1));
				}
			}
		}
	}
	
	@SubscribeEvent
	public void renderHUD(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event)
	{
		if(Minecraft.getMinecraft().currentScreen != null && Minecraft.getMinecraft().currentScreen instanceof GuiEnding_Bad)  
		{
			//event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void packetRecieved(DummyEvent_OnPacketRecieved event)
	{
		DummyData[] packetData = DataStorage.parseData(event.recievedData);
		if(packetData != null && packetData.length > 0)
		{
			try 
			{
				DummyData modData = packetData[0];
				if(modData.fieldName.equalsIgnoreCase("mod") && modData.fieldValue.equalsIgnoreCase("paradox"))
				{
					String type = packetData[1].fieldValue;
					if(type.equalsIgnoreCase("bad"))
						Minecraft.getMinecraft().displayGuiScreen(new GuiEnding_Bad());
					if(type.equalsIgnoreCase("good"))
						Minecraft.getMinecraft().displayGuiScreen(new GuiEnding_Good());
					if(type.equalsIgnoreCase("real"))
						Minecraft.getMinecraft().displayGuiScreen(new GuiEnding_Real());
				}
			}catch(Exception e)
			{
				
			}
		}
	}

}
