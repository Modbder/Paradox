package com.paradox.common;

import java.util.Arrays;

import DummyCore.Core.CoreInitialiser;
import DummyCore.Utils.DummyPacketIMSG;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class ParadoxChatCommand extends CommandBase  {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "paradoxEnding";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "/paradoxEnding <endingType>";
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		if(p_71515_2_.length >= 1)
		{
			String st = p_71515_2_[0];
			String dataString = new String();
			dataString += "||mod:paradox";
			dataString += "||type:"+st;
			DummyPacketIMSG pkt = new DummyPacketIMSG(dataString);
			CoreInitialiser.packetHandler.sendToAll(pkt);
		}
	}

    public int getRequiredPermissionLevel()
    {
        return 0;
    }

}
