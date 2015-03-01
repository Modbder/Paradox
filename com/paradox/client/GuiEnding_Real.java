package com.paradox.client;

import java.lang.reflect.Field;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.paradox.common.VoidProviderOverworld;
import com.paradox.common.core.ParadoxCore;

import DummyCore.Utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;

public class GuiEnding_Real extends GuiScreen{
	
	public float ticksPassed;
	public int timeBeginning;
	
	public boolean isPlaying = false;
	
	public static final ResourceLocation black = new ResourceLocation("paradoxmod","textures/ending/real/blackscreen.png");
	public static final ResourceLocation l0 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_0.png");
	public static final ResourceLocation l1 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_1.png");
	public static final ResourceLocation l2 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_2.png");
	public static final ResourceLocation l3 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_3.png");
	public static final ResourceLocation l4 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_4.png");
	public static final ResourceLocation l5 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_5.png");
	public static final ResourceLocation l6 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_6.png");
	public static final ResourceLocation l7 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_7.png");
	public static final ResourceLocation l8 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_8.png");
	public static final ResourceLocation l9 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_9.png");
	public static final ResourceLocation l10 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_10.png");
	public static final ResourceLocation l11 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_11.png");
	public static final ResourceLocation l12 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_12.png");
	public static final ResourceLocation l13 = new ResourceLocation("paradoxmod","textures/ending/real/sky_view_frame_13.png");

	public GuiEnding_Real()
	{
		super();
		Minecraft.getMinecraft().getSoundHandler().stopSounds();
		Minecraft.getMinecraft().theWorld.playSound(Minecraft.getMinecraft().thePlayer.posX,
				Minecraft.getMinecraft().thePlayer.posY,
				Minecraft.getMinecraft().thePlayer.posZ,
				"paradoxmod:sound.realending", 100, 1, true);
		timeBeginning = (int) Minecraft.getMinecraft().theWorld.getTotalWorldTime();
	}
	
	@Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
    }
	
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
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
    		Minecraft.getMinecraft().renderEngine.bindTexture(l5);
    		GL11.glColor4f(1, 1, 1, (float)(780 - this.ticksPassed)/80F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(780 - this.ticksPassed)/80F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l6);
    	}
    	if(this.ticksPassed > 780 && this.ticksPassed <= 900)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l6);
    		GL11.glColor4f(1, 1, 1, (float)(900 - this.ticksPassed)/120F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(900 - this.ticksPassed)/120F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l7);

    	}
    	if(this.ticksPassed > 900 && this.ticksPassed <= 1100)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l7);
    		GL11.glColor4f(1, 1, 1, (float)(1100 - this.ticksPassed)/200F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(1100 - this.ticksPassed)/200F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l8);

    	}
    	if(this.ticksPassed > 1100 && this.ticksPassed <= 1400)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l8);
    		GL11.glColor4f(1, 1, 1, (float)(1400 - this.ticksPassed)/300F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(1400 - this.ticksPassed)/300F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l9);

    	}
    	if(this.ticksPassed > 1400 && this.ticksPassed <= 1600)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l9);
    		GL11.glColor4f(1, 1, 1, (float)(1600 - this.ticksPassed)/200F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(1600 - this.ticksPassed)/200F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l10);

    	}
    	if(this.ticksPassed > 1600 && this.ticksPassed <= 1800)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l10);
    		GL11.glColor4f(1, 1, 1, (float)(1800 - this.ticksPassed)/200F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(1800 - this.ticksPassed)/200F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l11);
    	}
    	if(this.ticksPassed > 1800 && this.ticksPassed <= 2000)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l12);
    		GL11.glColor4f(1, 1, 1, (float)(2000 - this.ticksPassed)/200F);
        	tec.startDrawingQuads();
        	tec.setColorRGBA_F(1, 1, 1, (float)(2000 - this.ticksPassed)/200F);
        	tec.addVertexWithUV(0, 0, 0, 1, 1);
        	tec.addVertexWithUV(0, res.getScaledHeight_double(), 0, 1, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double() , res.getScaledHeight_double(), 0, 0, 0);
        	tec.addVertexWithUV(res.getScaledWidth_double(), 0, 0, 0, 1);
        	
        	tec.draw();
        	GL11.glColor4f(1, 1, 1, 1);
    		Minecraft.getMinecraft().renderEngine.bindTexture(l12);
    	}
    	if(this.ticksPassed > 2000)
    	{
    		Minecraft.getMinecraft().renderEngine.bindTexture(l13);
    	}
    	
    	if(this.ticksPassed > 3380)
    	{
    		this.mc = Minecraft.getMinecraft();
            this.mc.theWorld.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient)null);
            this.mc.displayGuiScreen(new GuiMainMenu());
            
            ParadoxCore.enableTeleportation = false;
            ParadoxCore.enableWorldSkyChange = false;
            ParadoxCore.ticksToFinish = Integer.MAX_VALUE;
            ParadoxCore.config.load();
            ParadoxCore.config.get("GLOBAL", "timeLimit", 20*60*60*120).set(Integer.MAX_VALUE);
            ParadoxCore.config.get("GLOBAL", "enableSpaceSky", true).set(false);
            ParadoxCore.config.get("GLOBAL", "generateVoid", true).set(false);
            ParadoxCore.config.get("SPAWN", "allowTeleportation", true).set(false);
            ParadoxCore.config.save();
			DimensionManager.unregisterProviderType(0);
			DimensionManager.registerProviderType(0, WorldProviderSurface.class, true);
            this.mc.displayGuiScreen((GuiScreen)null);
            long i = (new Random()).nextLong();
            String s = Long.toString(i);

            if (!MathHelper.stringNullOrLengthZero(s))
            {
                try
                {
                    long j = Long.parseLong(s);

                    if (j != 0L)
                    {
                        i = j;
                    }
                }
                catch (NumberFormatException numberformatexception)
                {
                    i = (long)s.hashCode();
                }
            }

            WorldType.worldTypes[0].onGUICreateWorldPress();

            WorldSettings.GameType gametype = WorldSettings.GameType.getByName("survival");
            WorldSettings worldsettings = new WorldSettings(i, gametype, true, false, WorldType.worldTypes[0]);
            worldsettings.func_82750_a("ParadoxAfterworld");

            if (true)
            {
                worldsettings.enableCommands();
            }

            this.mc.launchIntegratedServer("ParadoxAfterworld", "ParadoxAfterworld".trim(), worldsettings);
            
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
    		this.fontRendererObj.drawString("You have made... something?", k/2, (this.height-(int) ticksPassed)/2, 0xffffff, false);
    		
    	GL11.glPopMatrix();
    	this.fontRendererObj.drawString("You have decided to believe the advice you got through time", k, (this.height-(int) ticksPassed)+20, 0xffffff, false);
    	this.fontRendererObj.drawString("You have set everything up correctry", k, (this.height-(int) ticksPassed)+30, 0xffffff, false);
    	this.fontRendererObj.drawString("And shot the space near the paradox from the PRLG", k, (this.height-(int) ticksPassed)+40, 0xffffff, false); 
    	
    	if(this.ticksPassed > 2000)
    	{
	    	GL11.glPushMatrix();
				GL11.glScalef(3, 3, 3);
				this.fontRendererObj.drawString("The End?..", k/3, (this.height-((int) ticksPassed - 2000))/3, 0x000000, true);
			GL11.glPopMatrix();
    	}
    	
    	if(this.ticksPassed > 2400)
    	{
        	this.fontRendererObj.drawString("You have seen beyound time", k, (this.height-((int) ticksPassed-2400))+10, 0x000000, false);
        	this.fontRendererObj.drawString("All timelines flew in front of your eyes", k, (this.height-((int) ticksPassed-2400))+20, 0x000000, false);
        	this.fontRendererObj.drawString("You could've sworn, that you saw Jade somewhere around there!", k, (this.height-((int) ticksPassed-2400))+30, 0x000000, false); 
        	this.fontRendererObj.drawString("now you wait...", k, (this.height-((int) ticksPassed-2400))+40, 0x000000, false);
    	}
    	if(this.ticksPassed > 2800)
    	{
    		this.fontRendererObj.drawString("Something changed before your eyes!", k, (this.height-((int) ticksPassed-2800))+10, 0x000000, false);
    		this.fontRendererObj.drawString("Is this the same white space as before, or is it different?..", k, (this.height-((int) ticksPassed-2800))+20, 0x000000, false);
    	}
    	
    	if(this.ticksPassed > 3100)
    	{
	    	GL11.glPushMatrix();
				GL11.glScalef(3, 3, 3);
				this.fontRendererObj.drawString("Paradox", k/3, (this.height-((int) ticksPassed - 3100))/3, 0x000000, false);
			GL11.glPopMatrix();
    		this.fontRendererObj.drawString("THX for playing!", k, (this.height-((int) ticksPassed-3100))+30, 0x000000, false);
    	}
    	
    	//1200 frames;
    	GL11.glDisable(GL11.GL_BLEND);
    }
    
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
