package com.paradox.client;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.DummyDataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderTileTimer extends TileEntitySpecialRenderer{
	
	public static FontRenderer renderer;

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_,
			double p_147500_4_, double p_147500_6_, float p_147500_8_)
	{
		int x = p_147500_1_.xCoord;
		int y = p_147500_1_.yCoord;
		int z = p_147500_1_.zCoord;
		World w = p_147500_1_.getWorldObj();
		int metadata = w.getBlockMetadata(x, y, z);
		
		if(renderer == null)
		{
			renderer = new FontRenderer(Minecraft.getMinecraft().gameSettings, new ResourceLocation("paradoxmod","textures/font/electronic.png"), Minecraft.getMinecraft().renderEngine, false);
			renderer.setUnicodeFlag(false);
			renderer.setBidiFlag(true);
	    	((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(renderer);
		}
			
		GL11.glPushMatrix();
			GL11.glTranslated(p_147500_2_, p_147500_4_, p_147500_6_);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glScalef(0.04F, 0.04F, 0.04F);
			if(metadata == 2)
			{
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-46.5F, -17, 22F);
			}
			if(metadata == 3)
			{
				GL11.glTranslatef(-21, -17, -3F);
			}
			if(metadata == 4)
			{
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(-21, -17, 22F);
			}
			if(metadata == 5)
			{
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-46.5F, -17, -3F);
			}
			int timer = Integer.parseInt(DummyDataUtils.getCustomDataForMod("paradoxmod", "timer"));
			int daysLeft = timer / (20*60*60*24);
			String displayDays = "00";
			if(daysLeft >= 10)
				displayDays = Integer.toString(daysLeft);
			else
				displayDays = "0"+Integer.toString(daysLeft);
			
			int hoursLeft = timer / (20*60*60) - daysLeft*24;
			String displayHours = "00";
			if(hoursLeft >= 10)
				displayHours = Integer.toString(hoursLeft);
			else
				displayHours = "0"+Integer.toString(hoursLeft);
			
			int minutesLeft = timer / (20*60) - (daysLeft*24*60 + hoursLeft*60);
			String displayMinutes = "00";
			if(minutesLeft >= 10)
				displayMinutes = Integer.toString(minutesLeft);
			else
				displayMinutes = "0"+Integer.toString(minutesLeft);
			
			int secondsLeft = timer / (20) - (daysLeft*24*60*60 + hoursLeft*60*60 + minutesLeft*60);
			String displaySeconds = "00";
			if(secondsLeft >= 10)
				displaySeconds = Integer.toString(secondsLeft);
			else
				displaySeconds = "0"+Integer.toString(secondsLeft);
			
			int ticksLeft = timer - (daysLeft*24*60*60*20 + hoursLeft*60*60*20 + minutesLeft*60*20 + secondsLeft*20);
			String displayTicks = "00";
			if(ticksLeft >= 10)
				displayTicks = Integer.toString(ticksLeft);
			else
				displayTicks = "0"+Integer.toString(ticksLeft);
			
			
			renderer.drawString(displayDays+":"+displayHours+":"+minutesLeft+":"+displaySeconds+":"+displayTicks, 0, 0, 0x00ff00);
			GL11.glColor3f(1, 1, 1);
		GL11.glPopMatrix();
	}

}
