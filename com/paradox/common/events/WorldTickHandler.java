package com.paradox.common.events;

import com.paradox.common.core.ParadoxCore;

import DummyCore.Utils.DummyDataUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

public class WorldTickHandler {
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		if(event.world != null)
		{
			if(event.phase == Phase.START)
			{
				if(event.world.provider.dimensionId == 0)
				{
					
					String worldTimeLeft = DummyDataUtils.getCustomDataForMod("paradoxmod", "timer");
					if(worldTimeLeft == null || worldTimeLeft.equals("no data"))
					{
						DummyDataUtils.writeCustomDataForMod("paradoxmod", "timer", Integer.toString(ParadoxCore.ticksToFinish));
					}else
					{
						int leftTime = Integer.parseInt(DummyDataUtils.getCustomDataForMod("paradoxmod", "timer"));
						DummyDataUtils.writeCustomDataForMod("paradoxmod", "timer", Integer.toString(leftTime-1));
					}
					//System.out.println(worldTimeLeft);
				}
			}
		}
	
	}

}
