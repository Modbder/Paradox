package com.paradox.common;

import java.util.Arrays;

import DummyCore.Core.CoreInitialiser;
import DummyCore.Utils.DummyPacketIMSG;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;

public class ChatCommandFrameFix extends CommandBase  {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "fixFramerate";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
		// TODO Auto-generated method stub
		return "/fixFramerate";
	}

	@Override
	public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
		p_71515_1_.addChatMessage(new ChatComponentText("[Paradox]Starting DecoCraft cleanup..."));
		
		int removedTiles = 0;
		int leftTiles = 0;
		if(hasDecoCraft())
		{
			for(int i = 0; i < p_71515_1_.getEntityWorld().loadedTileEntityList.size(); ++i)
			{
				TileEntity tile = (TileEntity) p_71515_1_.getEntityWorld().loadedTileEntityList.get(i);
				int parseResult = this.parseTile(tile, removedTiles, leftTiles);
				if(parseResult == 0)
				{
					++removedTiles;
				}
				else
				{
					if(parseResult != -1)
					{
						leftTiles = parseResult;
					}
				}
			}
		}else
		{
			p_71515_1_.addChatMessage(new ChatComponentText("[Paradox]DecoCraft not installed, assuming dev tests."));
		}
		p_71515_1_.addChatMessage(new ChatComponentText("[Paradox]Finished DecoCraft cleanup, removed "+removedTiles+" blocks, left "+leftTiles+" blocks."));
	}

    public int getRequiredPermissionLevel()
    {
        return 2;
    }
    
    public int parseTile(TileEntity tile, int removedTiles, int leftTiles)
    {
    	try
    	{
    		Class c = Class.forName("com.mia.props.TileProps");
    		Class lamp = Class.forName("com.mia.props.entities.TileSwitchableLamp");
    		Class miaBlockClass = Class.forName("com.mia.props.BlockProps");
    		Block b = tile.getWorldObj().getBlock(tile.xCoord, tile.yCoord, tile.zCoord);
    		if(c.isAssignableFrom(tile.getClass()) || miaBlockClass.isAssignableFrom(b.getClass()))
    		{
    			
    			if(lamp.isAssignableFrom(tile.getClass()))
    			{
    				//lava lamp orange detected
    				return ++leftTiles;
    			}
    			++removedTiles;
    			tile.getWorldObj().setBlockToAir(tile.xCoord, tile.yCoord, tile.zCoord);
    			return 0;
    		}
    		return -1;
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		return -1;
    	}
    }
    
    public boolean hasDecoCraft()
    {
    	try
    	{
    		Class c = Class.forName("com.mia.props.TileProps");
    		return true;
    	}catch(Exception e)
    	{
    		return false;
    	}
    }

}
