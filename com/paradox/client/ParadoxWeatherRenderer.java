package com.paradox.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.client.IRenderHandler;

public class ParadoxWeatherRenderer extends IRenderHandler{

	public static ParadoxWeatherRenderer instance;
	
    public static ParadoxWeatherRenderer getRenderer()
    {
    	if(instance == null)
    		return new ParadoxWeatherRenderer();
    	else
    		return instance;
    }
    
    public ParadoxWeatherRenderer()
    {
    	instance = this;
    }
	
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		// TODO Auto-generated method stub
		
	}

}
