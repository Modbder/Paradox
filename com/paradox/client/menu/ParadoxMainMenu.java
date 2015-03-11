package com.paradox.client.menu;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import DummyCore.Utils.IMainMenu;

public class ParadoxMainMenu extends GuiMainMenu implements IMainMenu{

    private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
    private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
    private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
    private static final Random field_147527_e = new Random(31100L);
    FloatBuffer field_147528_b = GLAllocation.createDirectFloatBuffer(16);
    float sd;
    
    
    private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_)
    {
        this.field_147528_b.clear();
        this.field_147528_b.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
        this.field_147528_b.flip();
        return this.field_147528_b;
    }

	
	@Override
    public void drawScreen(int c, int p_73863_2_, float p_73863_3_)
    {
		//TODO Draw main menu
		if(this.mc == null)this.mc = Minecraft.getMinecraft();
		
    	if(sd == 0)sd = 42;
    	sd += 0.01F;
    	if(sd > 100)sd = 42;
    	
        GL11.glPushMatrix();
        GL11.glColor4f(0.2F, 0.6F, 1.0F, 1);
        
        Minecraft.getMinecraft().renderEngine.bindTexture(field_147529_c);
        drawTexturedModalRect(0, 0, c, p_73863_2_, 3000, 3000);
        GL11.glPopMatrix();

        for(int i = 1; i < 10; ++i)
        {
	        GL11.glPushMatrix();
	        GL11.glColor4f(0.2F, 0.6F, 1.0F, 0.5F);
	        GL11.glScalef(sd/(i*10), sd/(i*10), 1);
	        Minecraft.getMinecraft().renderEngine.bindTexture(field_147526_d);
	        drawTexturedModalRect((int)0, (int) 0, (int)i*128,(int) i*128, 3000, 3000);
	        GL11.glPopMatrix();
        }
        
        
        //TODO text
        String s = "Minecraft 1.7.10";
        List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));
        for (int i = 0; i < brandings.size(); i++)
        {
            String brd = brandings.get(i);
            if (!Strings.isNullOrEmpty(brd))
            {
                this.drawString(this.fontRendererObj, brd, 2, this.height - ( 10 + i * (this.fontRendererObj.FONT_HEIGHT + 1)), 16777215);
            }
        }
        
        ForgeHooksClient.renderMainMenu(this, fontRendererObj, width, height);
      
        String s1 = "Copyright Mojang AB. Do not distribute!";
        this.drawString(this.fontRendererObj, s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);
        String s2 = "Using Paradox main menu.";
        this.drawString(this.fontRendererObj, s2, this.width - this.fontRendererObj.getStringWidth(s2) - 2, this.height - 30, -1);
        String s3 = "Paradox version: 1.3";
        this.drawString(this.fontRendererObj, s3, this.width - this.fontRendererObj.getStringWidth(s3) - 2, this.height - 40, -1);
        short short1 = 274;
        int k = this.width / 2 - short1 / 2;
        
        //TODO MCLogo
        byte b0 = 30;
        
        int f = (int) (System.currentTimeMillis()/100%40);
        if(f > 20)
        	f = 40-(int) (System.currentTimeMillis()/100%40);
        this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
        if ((double)sd < 1.0E-4D)
        {
            this.drawTexturedModalRect(k + 0, b0 + f, 0, 0, 99, 44);
            this.drawTexturedModalRect(k + 99, b0 + f, 129, 0, 27, 44);
            this.drawTexturedModalRect(k + 99 + 26, b0 + f, 126, 0, 3, 44);
            this.drawTexturedModalRect(k + 99 + 26 + 3, b0 + f, 99, 0, 26, 44);
            this.drawTexturedModalRect(k + 155, b0 + f, 0, 45, 155, 44);
        }
        else
        {
            this.drawTexturedModalRect(k + 0, b0 + f, 0, 0, 155, 44);
            this.drawTexturedModalRect(k + 155, b0 + f, 0, 45, 155, 44);
        }

        //TODO splash
        String splashText = "Time to save the world!";
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
        GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
        float f1 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * (float)Math.PI * 2.0F) * 0.1F);
        f1 = f1 * 100.0F / (float)(this.fontRendererObj.getStringWidth(splashText) + 32);
        GL11.glScalef(f1, f1, f1);
        this.drawCenteredString(this.fontRendererObj, splashText, 0, -8+f, 0x8800ff);
        GL11.glPopMatrix(); 
        
        //TODO Buttons
        int k1;

        for (k1 = 0; k1 < this.buttonList.size(); ++k1)
        {
            ((GuiButton)this.buttonList.get(k1)).drawButton(this.mc, c, p_73863_2_);
        }

        for (k1 = 0; k1 < this.labelList.size(); ++k1)
        {
            ((GuiLabel)this.labelList.get(k1)).func_146159_a(this.mc, c, p_73863_2_);
        }
    }
}
