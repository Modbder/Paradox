package com.paradox.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;

public class ParadoxSkyRenderer extends IRenderHandler{

    private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
    private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
    private static final ResourceLocation locationMoonPhasesPng = new ResourceLocation("paradoxmod","textures/celestial/paradox.png");
    private static final ResourceLocation locationSunPng = new ResourceLocation("paradoxmod","textures/celestial/sun.png");
    private static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
    
    public static ParadoxSkyRenderer instance;
    
    public static ParadoxSkyRenderer getRenderer()
    {
    	if(instance == null)
    		return new ParadoxSkyRenderer();
    	else
    		return instance;
    }
    
    public ParadoxSkyRenderer()
    {
    	instance = this;
    }
    
	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) 
	{
		mc.renderEngine.bindTexture(field_147526_d);
		Tessellator tessellator = Tessellator.instance;

         Vec3 vec3 = world.getSkyColor(mc.renderViewEntity, partialTicks);
         float f1 = (float)vec3.xCoord;
         float f2 = (float)vec3.yCoord;
         float f3 = (float)vec3.zCoord;
         float f6;
         GL11.glColor3f(f1, f2, f3);
         Tessellator tessellator1 = Tessellator.instance;
         GL11.glDepthMask(false);
         
         for (int i = 0; i < 6; ++i)
         {
             GL11.glPushMatrix();

             if (i == 1)
             {
                 GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
             }

             if (i == 2)
             {
                 GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
             }

             if (i == 3)
             {
                 GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
             }

             if (i == 4)
             {
                 GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
             }

             if (i == 5)
             {
                 GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
             }
             double textureSlitch = (world.getWorldTime()+partialTicks)/1000;
             tessellator.startDrawingQuads();
             tessellator.setColorOpaque_I(0x2d99ff);
             tessellator.addVertexWithUV(-100.0D, -100.0D, -100.0D, 0.0D+textureSlitch, 0.0D+textureSlitch);
             tessellator.addVertexWithUV(-100.0D, -100.0D, 100.0D, 0.0D+textureSlitch, 4.0D+textureSlitch);
             tessellator.addVertexWithUV(100.0D, -100.0D, 100.0D, 4.0D+textureSlitch, 4.0D+textureSlitch);
             tessellator.addVertexWithUV(100.0D, -100.0D, -100.0D, 4.0D+textureSlitch, 0.0D+textureSlitch);
             tessellator.draw();
             GL11.glPopMatrix();
         }
         
         GL11.glEnable(GL11.GL_FOG);
         GL11.glColor3f(f1, f2, f3);
         //GL11.glCallList(this.glSkyList);
         GL11.glDisable(GL11.GL_FOG);
         GL11.glDisable(GL11.GL_ALPHA_TEST);
         GL11.glEnable(GL11.GL_BLEND);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         RenderHelper.disableStandardItemLighting();
         float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);
         float f7;
         float f8;
         float f9;
         float f10;

         GL11.glEnable(GL11.GL_TEXTURE_2D);
         OpenGlHelper.glBlendFunc(770, 1, 1, 0);
         GL11.glPushMatrix();
         f6 = 1.0F - world.getRainStrength(partialTicks);
         f7 = 0.0F;
         f8 = 0.0F;
         f9 = 0.0F;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, f6);
         GL11.glTranslatef(f7, f8, f9);
         GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(world.getCelestialAngle(partialTicks)*360, 1.0F, 0.0F, 0.0F);
         f10 = 10.0F;
         mc.renderEngine.bindTexture(locationSunPng);
         tessellator1.startDrawingQuads();
         tessellator1.addVertexWithUV((double)(-f10), 100.0D, (double)(-f10), 0.0D, 0.0D);
         tessellator1.addVertexWithUV((double)f10, 100.0D, (double)(-f10), 1.0D, 0.0D);
         tessellator1.addVertexWithUV((double)f10, 100.0D, (double)f10, 1.0D, 1.0D);
         tessellator1.addVertexWithUV((double)(-f10), 100.0D, (double)f10, 0.0D, 1.0D);
         tessellator1.draw();
         f10 = 50.0F;
         mc.renderEngine.bindTexture(locationMoonPhasesPng);
         int k = 0;
         int l = k % 4;
         int i1 = k / 4 % 2;
         float f14 = (float)(l + 0) / 4.0F;
         float f15 = (float)(i1 + 0) / 2.0F;
         float f16 = (float)(l + 1) / 4.0F;
         float f17 = (float)(i1 + 1) / 2.0F;
         tessellator1.startDrawingQuads();
         tessellator1.addVertexWithUV((double)(-f10), -100.0D, (double)f10, (double)0, (double)0);
         tessellator1.addVertexWithUV((double)f10, -100.0D, (double)f10, (double)1, (double)0);
         tessellator1.addVertexWithUV((double)f10, -100.0D, (double)(-f10), (double)1, (double)1);
         tessellator1.addVertexWithUV((double)(-f10), -100.0D, (double)(-f10), (double)0, (double)1);
         tessellator1.draw();
         GL11.glDisable(GL11.GL_TEXTURE_2D);
         float f18 = world.getStarBrightness(partialTicks) * f6;

         if (f18 > 0.0F)
         {
             GL11.glColor4f(f18, f18, f18, f18);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(GL11.GL_BLEND);
         GL11.glEnable(GL11.GL_ALPHA_TEST);
         GL11.glEnable(GL11.GL_FOG);
         GL11.glPopMatrix();
         GL11.glDisable(GL11.GL_TEXTURE_2D);
         GL11.glColor3f(0.0F, 0.0F, 0.0F);
         double d0 = mc.thePlayer.getPosition(partialTicks).yCoord - world.getHorizon();

         GL11.glColor3f(f1, f2, f3);
         GL11.glPushMatrix();
         
         GL11.glPopMatrix();
         GL11.glEnable(GL11.GL_TEXTURE_2D);
         GL11.glDepthMask(true);
	}

}
