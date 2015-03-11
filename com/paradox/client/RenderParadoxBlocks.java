package com.paradox.client;

import org.lwjgl.opengl.GL11;

import com.paradox.blocks.BlockApperifier;
import com.paradox.blocks.BlockMRUGenerator;
import com.paradox.blocks.BlockParadoxHandyGen;
import com.paradox.blocks.BlockParadoxLiquifier;
import com.paradox.blocks.BlockParadoxTimer;
import com.paradox.blocks.BlockParadoxifier;
import com.paradox.blocks.BlockPinkVine;
import com.paradox.blocks.BlockPotentiaGen;
import com.paradox.blocks.BlockRFGen;
import com.paradox.blocks.ParadoxBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderParadoxBlocks implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer)
	{
		if(block instanceof BlockParadoxTimer)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			
			GL11.glScalef(2, 1, 0.1F);
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			
			renderer.clearOverrideBlockTexture();
		}
		if(block instanceof BlockParadoxHandyGen)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				ParadoxClient.drawBlueBar(0);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 2);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockApperifier)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 0);
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.DOWN, false, 2);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockParadoxLiquifier)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				ParadoxClient.drawFluid(null);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 1);
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.DOWN, false, 2);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockPotentiaGen)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				ParadoxClient.drawAspectContainer(null, null);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 2);
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.DOWN, false, 4);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockParadoxifier)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				ParadoxClient.drawFluid(null);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 2);
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.DOWN, false, 1);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockRFGen)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				ParadoxClient.drawRFEnergy(null);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 2);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockMRUGenerator)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderTileHandyGen.generatorTextures);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			
			for(int i = 0; i < 4; ++i)
			{
				GL11.glPushMatrix();
				GL11.glColor3f(1, 1, 1);
				ParadoxClient.glRotatefBasedOnLoop(i);
				
				ParadoxClient.drawParadoxEnergy(null);
				
				ParadoxClient.drawMRU(null);
				
				GL11.glPopMatrix();
			}
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, true, 2);
			ParadoxClient.drawDeviceConfiguration(ForgeDirection.UP, false, 3);
			renderer.clearOverrideBlockTexture();
			
		}
		if(block instanceof BlockPinkVine)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			float s = 0.7F;
			GL11.glScalef(s, s, s);
			renderer.renderBlockAsItem(Blocks.glass, 1, 1);
			renderer.clearOverrideBlockTexture();
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if(block instanceof BlockParadoxTimer)
		{
			renderer.renderAllFaces = true;
			int metadata = world.getBlockMetadata(x, y, z); 
			if(metadata == 2)
			{
				renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
				renderer.setRenderBounds(-1, 0, 0.9D, 2, 1, 1D);
				renderer.renderStandardBlock(Blocks.glass, x, y, z);
			}
			if(metadata == 3)
			{
				renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
				renderer.setRenderBounds(-1, 0, 0D, 2, 1, 0.1D);
				renderer.renderStandardBlock(Blocks.glass, x, y, z);
			}
			if(metadata == 5)
			{
				renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
				renderer.setRenderBounds(0, 0, -1D, 0.1D, 1, 2D);
				renderer.renderStandardBlock(Blocks.glass, x, y, z);
			}
			if(metadata == 4)
			{
				renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
				renderer.setRenderBounds(0.9D, 0, -1D, 1D, 1, 2D);
				renderer.renderStandardBlock(Blocks.glass, x, y, z);
			}
			renderer.clearOverrideBlockTexture();
			renderer.renderAllFaces = false;
			return true;
		}
		if(block instanceof BlockParadoxHandyGen
		|| block instanceof BlockRFGen
		|| block instanceof BlockParadoxLiquifier
		|| block instanceof BlockParadoxifier
		|| block instanceof BlockMRUGenerator
		|| block instanceof BlockPotentiaGen
		|| block instanceof BlockApperifier
		)
		{
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.renderStandardBlock(Blocks.glass, x, y, z);
			renderer.clearOverrideBlockTexture();
			return true;
		}
		if(block instanceof BlockPinkVine)
		{
			BlockPinkVine b = (BlockPinkVine) block;
			int metadata = world.getBlockMetadata(x, y, z);
			if(metadata > 7)metadata = 7;
			renderer.setOverrideBlockTexture(block.getBlockTextureFromSide(0));
			renderer.setRenderBounds(0, 0.99F, 0, 1, 1, 1);
			renderer.renderStandardBlock(Blocks.glass, x, y, z);
			renderer.setOverrideBlockTexture(b.icons[metadata]);
			renderer.renderCrossedSquares(Blocks.glass, x, y, z);
			renderer.clearOverrideBlockTexture();
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 986555;
	}

}
