package com.paradox.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.client.IRenderHandler;

public class ParadoxCloudRenderer extends IRenderHandler{

	public static ParadoxCloudRenderer instance;
	
    public static ParadoxCloudRenderer getRenderer()
    {
    	if(instance == null)
    		return new ParadoxCloudRenderer();
    	else
    		return instance;
    }
    
    public ParadoxCloudRenderer()
    {
    	instance = this;
    }
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		// TODO Auto-generated method stub
		
	}

}
