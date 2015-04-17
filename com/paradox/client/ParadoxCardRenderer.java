package com.paradox.client;

import java.util.Hashtable;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.paradox.common.utils.ParadoxUtils;
import com.paradox.items.ItemParadoxCard;
import com.paradox.items.ItemParadoxCard_Entity;

import DummyCore.Utils.MiscUtils;
import DummyCore.Utils.UnformedItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

public class ParadoxCardRenderer implements IItemRenderer{

	public Hashtable<Integer, Entity> renderedEntitiesTable = new Hashtable<Integer, Entity>();
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		if(item.getItem() instanceof ItemParadoxCard)
		{
			if(type == ItemRenderType.INVENTORY)
			{
				GL11.glPushMatrix();
				GL11.glRotatef(225, 0, 1, 0);
				float s = 1F/9F;
				GL11.glScalef(s, s, s);
				GL11.glTranslatef(-8, -8, 0);
				MiscUtils.drawTexture_Items(0, 0, item.getIconIndex(), 16, 16, 0);
				
				GL11.glPopMatrix();
				
				if(ParadoxUtils.allRegisteredStacks.size() > item.getItemDamage())
				{
					UnformedItemStack drawStk = ParadoxUtils.allRegisteredStacks.get(item.getItemDamage());
					ItemStack toDraw = drawStk.getISToDraw(Minecraft.getMinecraft().theWorld.getTotalWorldTime());
					if(toDraw != null)
					{
						GL11.glPushMatrix();
						Item toDrawItem = toDraw.getItem();
						if(toDrawItem instanceof ItemBlock)
						{
							Block b = ((ItemBlock)toDrawItem).field_150939_a;
							if(b == null || !(b instanceof BlockLiquid))
							{
								GL11.glScalef(0.5F, 0.5F, 0.5F);
								GL11.glRotatef(45, 0, 1, 0);
							}
							if(b instanceof BlockLiquid)
							{
								GL11.glScalef(0.5F, 0.5F, 0.5F);
								GL11.glTranslatef(2F, 0.9F, 1F);
								
								GL11.glRotatef(25, 1, 0, 1);
							}
						}
						ItemRenderer defaultRenderer = (ItemRenderer) Minecraft.getMinecraft().entityRenderer.itemRenderer;
						defaultRenderer.renderItem(Minecraft.getMinecraft().thePlayer, toDraw, toDraw.getItemDamage(), type);
						GL11.glPopMatrix();
					}
					
				}
			}else
			{
				GL11.glPushMatrix();
				//GL11.glRotatef(225, 0, 1, 0);
				float s = 1F/16F;
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glScalef(s, s, s);
				GL11.glTranslatef(-8, -8, 0);
				
				MiscUtils.drawTexture_Items(0, 0, item.getIconIndex(), 16, 16, 0);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-16, 1, 0);
				MiscUtils.drawTexture_Items(0, 0, item.getIconIndex(), 16, 16, 0);
				GL11.glPopMatrix();
				
				if(ParadoxUtils.allRegisteredStacks.size() > item.getItemDamage())
				{
					GL11.glRotatef(90, 0, 1, 0);
					UnformedItemStack drawStk = ParadoxUtils.allRegisteredStacks.get(item.getItemDamage());
					ItemStack toDraw = drawStk.getISToDraw(Minecraft.getMinecraft().theWorld.getTotalWorldTime());
					if(toDraw != null)
					{
						GL11.glPushMatrix();
						Item toDrawItem = toDraw.getItem();
						if(toDrawItem instanceof ItemBlock)
						{
							Block b = ((ItemBlock)toDrawItem).field_150939_a;
							if(b == null || !(b instanceof BlockLiquid))
							{
								GL11.glScalef(0.5F, 0.5F, 0.01F);
								GL11.glTranslatef(0, 0.1F, 1F);
								GL11.glRotatef(0, 0, 1, 0);
							}
							if(b instanceof BlockLiquid)
							{
								GL11.glScalef(0.5F, 0.25F, 0.01F);
								GL11.glTranslatef(0.4F, 0.0F, 1F);
								
								GL11.glRotatef(25, 1, 0, 1);
							}
							if(b.getClass().getName().contains("railcraft"))
							{
								IIcon icon = Blocks.stone.getBlockTextureFromSide(0);
								GL11.glPushMatrix();
									GL11.glRotatef(180, 0, 1, 0);
									GL11.glScalef(s, s, s);
									GL11.glTranslatef(-8, -8, 0);
									MiscUtils.drawTexture(0, 0, icon, 16, 16, 0);
									icon = toDraw.getItem().getIconFromDamageForRenderPass(toDraw.getItemDamage(), 0);
									MiscUtils.drawTexture(0, 0, icon, 16, 16, 0);
								GL11.glPopMatrix();
							}else
							{
								ItemRenderer defaultRenderer = (ItemRenderer) Minecraft.getMinecraft().entityRenderer.itemRenderer;
								defaultRenderer.renderItem(Minecraft.getMinecraft().thePlayer, toDraw, toDraw.getItemDamage(), type);
							}

						}else
						{
							ItemRenderer defaultRenderer = (ItemRenderer) Minecraft.getMinecraft().entityRenderer.itemRenderer;
							defaultRenderer.renderItem(Minecraft.getMinecraft().thePlayer, toDraw, toDraw.getItemDamage(), type);
						}
						
						GL11.glPopMatrix();
					}
					
				}
			}
		}
		if(item.getItem() instanceof ItemParadoxCard_Entity)
		{
			if(type == ItemRenderType.INVENTORY)
			{
				GL11.glPushMatrix();
					GL11.glRotatef(225, 0, 1, 0);
					float s = 1F/9F;
					GL11.glScalef(s, s, s);
					GL11.glTranslatef(-8, -8, 0);
					MiscUtils.drawTexture_Items(0, 0, item.getIconIndex(), 16, 16, 0);
				GL11.glPopMatrix();
			
				Class<?> clazz = ParadoxUtils.allRegisteredEntities.get(item.getItemDamage());
				if(clazz != null)
				{
					try
					{
						Entity e = null;
						if(this.renderedEntitiesTable.containsKey(item.getItemDamage()))
							e = this.renderedEntitiesTable.get(item.getItemDamage());
						else	
						{
							e = (Entity) clazz.getConstructor(World.class).newInstance(Minecraft.getMinecraft().theWorld);
							e.setWorld(Minecraft.getMinecraft().theWorld);
							this.renderedEntitiesTable.put(item.getItemDamage(), e);
						}
						
						
						float f1 = 0.4375F;
						float renderIndexY = 0;
						float renderIndexX = 0;
						float renderIndexZ = 0;
						if(type == ItemRenderType.INVENTORY)
						{
						if(e instanceof EntityBlaze || e instanceof EntitySlime)
						{
							renderIndexY += 2.4F;
							renderIndexX += 3;
							renderIndexZ += 3;
						}
						
						if(e instanceof EntityMagmaCube)
						{
							renderIndexY += 0.5F;
						}
						
						if(e instanceof EntitySlime && !(e instanceof EntityMagmaCube))
						{
							renderIndexY += 0.8F;
						}
						
						if(e instanceof EntityEnderman)
						{
							f1 /= 1.4F;
							renderIndexY += 2;
							renderIndexX += 3;
							renderIndexZ += 3;
						}
						
						if(e instanceof EntityGhast)
						{
							f1 /= 4;
							renderIndexY += 2.4F;
							renderIndexX += 3;
							renderIndexZ += 3;
						}
						
						if(e instanceof EntitySquid)
						{
							renderIndexY = 1.3F;
						}
						
						if(e.getClass().getName().equalsIgnoreCase("thaumcraft.common.entities.monster.EntityTaintacle"))
						{
							renderIndexY = 3;
						}
						
						if(e.getClass().getName().equalsIgnoreCase("thaumcraft.common.entities.monster.EntityTaintacleSmall"))
						{
							renderIndexY = 2;
						}
						
						
						GL11.glPushMatrix();
				            GL11.glScalef(f1, f1, f1);
				            GL11.glTranslatef(renderIndexX, -1+renderIndexY, renderIndexZ);
				            e.setLocationAndAngles(0, 0, 0, 0.0F, 0.0F);
				            RenderManager.instance.renderEntityWithPosYaw(e, 0.0D, 0.0D, 0.0D, 0.0F, 0);
			            GL11.glPopMatrix();
			            
			            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			            GL11.glDisable(GL11.GL_TEXTURE_2D);
			            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
			}
		}else
		{
			GL11.glPushMatrix();
			//GL11.glRotatef(225, 0, 1, 0);
			float s = 1F/16F;
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glScalef(s, s, s);
			GL11.glTranslatef(-8, -8, 0);
			
			MiscUtils.drawTexture_Items(0, 0, item.getIconIndex(), 16, 16, 0);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glTranslatef(-16, 1, 0);
			MiscUtils.drawTexture_Items(0, 0, item.getIconIndex(), 16, 16, 0);
			GL11.glPopMatrix();
			
			Class<?> clazz = ParadoxUtils.allRegisteredEntities.get(item.getItemDamage());
			if(clazz != null)
			{
				try
				{
					Entity e = null;
					if(this.renderedEntitiesTable.containsKey(item.getItemDamage()))
						e = this.renderedEntitiesTable.get(item.getItemDamage());
					else	
					{
						e = (Entity) clazz.getConstructor(World.class).newInstance(Minecraft.getMinecraft().theWorld);
						e.setWorld(Minecraft.getMinecraft().theWorld);
						this.renderedEntitiesTable.put(item.getItemDamage(), e);
					}
					float f1 = 0.4375F;
					float renderIndexY = 0;
					float renderIndexX = 0;
					float renderIndexZ = 0;
					if(e instanceof EntityBlaze || e instanceof EntitySlime)
					{
					}
					if(e instanceof EntitySlime)
					{
						renderIndexY -= 0.5F;
					}
					if(e instanceof EntityMagmaCube)
					{
						renderIndexY += 1F;
					}
					
					if(e instanceof EntitySlime && !(e instanceof EntityMagmaCube))
					{
						renderIndexY += 1F;
					}
					
					if(e instanceof EntityEnderman)
					{
						f1 /= 1.4F;
					}
					
					if(e instanceof EntityGhast)
					{
						f1 /= 4;
					}
					
					if(e instanceof EntitySquid)
					{
						renderIndexY = 0.6F;
					}
					
					if(e.getClass().getName().equalsIgnoreCase("thaumcraft.common.entities.monster.EntityTaintacle"))
					{
						f1 /= 2;
						renderIndexY = 1;
					}
					
					if(e.getClass().getName().equalsIgnoreCase("thaumcraft.common.entities.monster.EntityTaintacleSmall"))
					{
						renderIndexY = 1;
					}
					
					GL11.glPushMatrix();
			            GL11.glScalef(f1, f1, f1);
			            GL11.glTranslatef(renderIndexX, -0.5F+renderIndexY, renderIndexZ);
			            e.setLocationAndAngles(0, 0, 0, 90.0F, 0.0F);
			            GL11.glRotatef(90, 0, 1, 0);
			            GL11.glScalef(0.5F, 0.5F, 0.01F);
			            RenderManager.instance.renderEntityWithPosYaw(e, 0D, 0.0D, 0.6D, 0, 0);
		            GL11.glPopMatrix();
		            
		            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		            GL11.glDisable(GL11.GL_TEXTURE_2D);
		            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
			}
		}
	}
}

}
