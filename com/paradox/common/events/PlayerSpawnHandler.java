package com.paradox.common.events;

import com.paradox.common.core.ParadoxCore;

import DummyCore.Utils.DummyDataUtils;
import DummyCore.Utils.Notifier;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class PlayerSpawnHandler {
	

	@SubscribeEvent
	public void onPlayerFirstJoin(PlayerEvent.PlayerLoggedInEvent event)
	{
		String loggedInData = DummyDataUtils.getDataForPlayer(event.player.getCommandSenderName(), "paradoxmod", "loggedIn");
		if(loggedInData == null || loggedInData.isEmpty() || loggedInData.equals("no data"))
		{
			Notifier.notifyCustomMod("paradoxmod", "Player server data not found, assuming first logged in?");
			if(ParadoxCore.enableTeleportation)
			{
				event.player.setPositionAndUpdate(ParadoxCore.teleportX, ParadoxCore.teleportY, ParadoxCore.teleportZ);
				Notifier.notifyCustomMod("paradoxmod", "Successfully teleported player "+event.player.getCommandSenderName()+"!");
				DummyDataUtils.setDataForPlayer(event.player.getCommandSenderName(), "paradoxmod", "loggedIn", "true");
			}
		}else
		{
			try
			{
				boolean logged = Boolean.parseBoolean(loggedInData);
				if(!logged)
				{
					if(ParadoxCore.enableTeleportation)
					{
						event.player.setPositionAndUpdate(ParadoxCore.teleportX, ParadoxCore.teleportY, ParadoxCore.teleportZ);
						Notifier.notifyCustomMod("paradoxmod", "Successfully teleported player "+event.player.getCommandSenderName()+"!");
						DummyDataUtils.setDataForPlayer(event.player.getCommandSenderName(), "paradoxmod", "loggedIn", "true");
					}
				}
			}catch(Exception e)
			{
				Notifier.notifyCustomMod("paradoxmod", "[ERROR] unable to parse player logging data!");
				e.printStackTrace();
			}
			
		}
	}
}
