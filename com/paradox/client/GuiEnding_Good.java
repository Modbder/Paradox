package com.paradox.client;

import java.lang.reflect.Field;

import org.lwjgl.opengl.GL11;

import DummyCore.Utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.WorldInfo;

public class GuiEnding_Good extends GuiScreen{
	
	public float ticksPassed;
	public int timeBeginning;
	
	public boolean isPlaying = false;
	
	public static final ResourceLocation black = new ResourceLocation("paradoxmod","textures/ending/good/blackscreen.png");
	public static final ResourceLocation l0 = new ResourceLocation("paradoxmod","textures/ending/good/sky_view_frame_0.png");
	public static final ResourceLocation l1 = new ResourceLocation("paradoxmod","textures/ending/good/sky_view_frame_1.png");
	public static final ResourceLocation l2 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_0.png");
	public static final ResourceLocation l3 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_1.png");
	public static final ResourceLocation l4 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_2.png");
	public static final ResourceLocation l5 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_3.png");
	public static final ResourceLocation l6 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_4.png");
	public static final ResourceLocation l7 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_5.png");
	public static final ResourceLocation l8 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_6.png");
	public static final ResourceLocation l9 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_7.png");
	public static final ResourceLocation l10 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_8.png");
	public static final ResourceLocation l11 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_9.png");
	public static final ResourceLocation l12 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_10.png");
	public static final ResourceLocation l13 = new ResourceLocation("paradoxmod","textures/ending/good/sky_shoot_frame_11.png");

	public GuiEnding_Good()
	{
		super();
		Minecraft.getMinecraft().getSoundHandler().stopSounds();
		timeBeginning = (int) Minecraft.getMinecraft().theWorld.getTotalWorldTime();
	}
	
	@Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
    }
	
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        int k = (this.width - 256) / 2;
        int l = (this.height - 256) / 2;
        ticksPassed = Minecraft.getMinecraft().theWorld.getTotalWorldTime() - timeBeginning + p_73863_3_;
    	Minecraft.getMinecraft().renderEngine.bindTexture(black);
    	ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
    	Tessellator tec = Tessellator.instance;
    	
    	tec.startDrawingQuads();
    	
    	tec.addVertexWithUV(0, 0, 0, 1, 1);
    	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
    	tec.addVertexWithUV(res.getScaledWidth_double(), res.getScaledHeight_double(), 0, 0, 0);
    	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
    	
    	tec.draw();
    	
    	if(this.ticksPassed > 370 && this.ticksPassed <= 630)
    	{
    		if(this.ticksPassed % 10 < 5)
    			Minecraft.getMinecraft().renderEngine.bindTexture(l0);
    		else
    			Minecraft.getMinecraft().renderEngine.bindTexture(l1);
    	}
    	if(this.ticksPassed > 630 && this.ticksPassed <= 670)
    	{
    		if(this.ticksPassed % 10 < 5)
    			Minecraft.getMinecraft().renderEngine.bindTexture(l2);
    		else
    			Minecraft.getMinecraft().renderEngine.bindTexture(l3);
    	}
    	if(this.ticksPassed > 670 && this.ticksPassed <= 700)
    	{
    		if(this.ticksPassed % 10 < 5)
    			Minecraft.getMinecraft().renderEngine.bindTexture(l4);
    		else
    			Minecraft.getMinecraft().renderEngine.bindTexture(l5);
    	}
    	if(this.ticksPassed > 700 && this.ticksPassed <= 780)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l6);
    	}
    	if(this.ticksPassed > 780 && this.ticksPassed <= 900)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l7);
    		if(this.ticksPassed % 10 <= 1)
    		Minecraft.getMinecraft().theWorld.playSound(Minecraft.getMinecraft().thePlayer.posX+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posY+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posZ+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				"portal.trigger", 0.01F, Minecraft.getMinecraft().theWorld.rand.nextFloat()*2, true);
    	}
    	if(this.ticksPassed > 900 && this.ticksPassed <= 1100)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l8);
    		if(this.ticksPassed % 8 <= 1)
    		Minecraft.getMinecraft().theWorld.playSound(Minecraft.getMinecraft().thePlayer.posX+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posY+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posZ+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				"portal.trigger", 0.05F, Minecraft.getMinecraft().theWorld.rand.nextFloat()*2, true);
    	}
    	if(this.ticksPassed > 1100 && this.ticksPassed <= 1400)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l9);
    		if(this.ticksPassed % 4 <= 1)
    		Minecraft.getMinecraft().theWorld.playSound(Minecraft.getMinecraft().thePlayer.posX+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posY+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posZ+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				"portal.trigger", 0.1F, Minecraft.getMinecraft().theWorld.rand.nextFloat()*2, true);
    	}
    	if(this.ticksPassed > 1400 && this.ticksPassed <= 1600)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l10);
    		if(this.ticksPassed % 2 < 1)
    		Minecraft.getMinecraft().theWorld.playSound(Minecraft.getMinecraft().thePlayer.posX+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posY+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				Minecraft.getMinecraft().thePlayer.posZ+MathUtils.randomDouble(Minecraft.getMinecraft().theWorld.rand),
    				"portal.trigger", 0.2F, Minecraft.getMinecraft().theWorld.rand.nextFloat()*2, true);
    	}
    	if(this.ticksPassed > 1600 && this.ticksPassed <= 1800)
    	{
    		if(!isPlaying)
    		{
    			Minecraft.getMinecraft().getSoundHandler().stopSounds();
    			Minecraft.getMinecraft().theWorld.playSound(Minecraft.getMinecraft().thePlayer.posX,
    					Minecraft.getMinecraft().thePlayer.posY,
    					Minecraft.getMinecraft().thePlayer.posZ,
    					"paradoxmod:sound.goodending", 100, 1, true);
    			isPlaying = true;
    		}
    		Minecraft.getMinecraft().renderEngine.bindTexture(l11);
    	}
    	if(this.ticksPassed > 1800 && this.ticksPassed <= 2000)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l12);
    	}
    	if(this.ticksPassed > 2000)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l13);
    	}
    	
    	if(this.ticksPassed > 2400)
    	{
    		try
    		{
    			Class worldInfoClass = WorldInfo.class;
    			Field hardcore = worldInfoClass.getDeclaredFields()[20];
    			hardcore.setAccessible(true);
    			hardcore.setBoolean(Minecraft.getMinecraft().theWorld.getWorldInfo(), true);
    		}catch(Exception e)
    		{
    			e.printStackTrace();
    			return;
    		}
    		Minecraft.getMinecraft().displayGuiScreen(new GuiGameOver());
    	}
    	
    	double scaledHeight = res.getScaledHeight_double();
    	double scaledWidth = res.getScaledWidth_double();
    	
    	if(scaledHeight > scaledWidth)scaledHeight = scaledWidth;else scaledWidth = scaledHeight;
    	
    	tec.startDrawingQuads();
    	
    	tec.addVertexWithUV(0, 0, 0, 1, 1);
    	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
    	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
    	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
    	
    	tec.draw();
    	
    	GL11.glPushMatrix();
    		GL11.glScalef(2, 2, 2);
    		this.fontRendererObj.drawString("You have made it!", k/2, (this.height-(int) ticksPassed)/2, 0xffffff, false);
    		
    	GL11.glPopMatrix();
    	this.fontRendererObj.drawString("You were able to gather all the required items!", k, (this.height-(int) ticksPassed)+20, 0xffffff, false);
    	this.fontRendererObj.drawString("You then shot the PRLG right into the paradox!", k, (this.height-(int) ticksPassed)+30, 0xffffff, false);
    	this.fontRendererObj.drawString("That then resulted in the Paradox Singularity and universe reset", k, (this.height-(int) ticksPassed)+40, 0xffffff, false);
    	this.fontRendererObj.drawString("You have died, but restored the timeline and all universes!", k, (this.height-(int) ticksPassed)+50, 0xffffff, false);
    	if(this.ticksPassed > 2000 && ticksPassed < 2300)
    	{
	    	GL11.glPushMatrix();
				GL11.glScalef(3, 3, 3);
				this.fontRendererObj.drawString("Game Over.", k/3, l, 0x00ff00, true);
			GL11.glPopMatrix();
    	}
    	if(this.ticksPassed > 2300)
    	{
	    	GL11.glPushMatrix();
				GL11.glScalef(3, 3, 3);
				this.fontRendererObj.drawString("Game Over?", k/3, l, 0xaaffaa, true);
			GL11.glPopMatrix();
    	}
    	//1200 frames;
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
