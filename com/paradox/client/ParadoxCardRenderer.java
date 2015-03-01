package com.paradox.client;

import org.lwjgl.opengl.GL11;

import com.paradox.common.utils.ParadoxUtils;

import DummyCore.Utils.MiscUtils;
import DummyCore.Utils.UnformedItemStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ParadoxCardRenderer implements IItemRenderer{

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
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
					GL11.glScalef(0.5F, 0.5F, 0.5F);
					GL11.glRotatef(45, 0, 1, 0);
				}
				RenderBlocks rb = (RenderBlocks) data[0];
				ItemRenderer defaultRenderer = (ItemRenderer) Minecraft.getMinecraft().entityRenderer.itemRenderer;
				defaultRenderer.renderItem(Minecraft.getMinecraft().thePlayer, toDraw, toDraw.getItemDamage(), type);
				GL11.glPopMatrix();
			}
			
		}
	}

}
