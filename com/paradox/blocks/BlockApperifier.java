package com.paradox.blocks;

import java.util.List;

import DummyCore.Utils.MiscUtils;

import com.paradox.api.IInformationProvider;
import com.paradox.common.GCLibrary;
import com.paradox.tile.TileApperifier;
import com.paradox.tile.TileHandyGenerator;
import com.paradox.tile.TileParadoxLiquifier;
import com.paradox.tile.TileParadoxifier;
import com.paradox.tile.TileTimer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockApperifier extends BlockContainer implements IInformationProvider{
	
	public BlockApperifier()
	{
		super(Material.iron);
	}
	
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return 0;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
    	super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return 986555;
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileApperifier();
	}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	MiscUtils.openGui(par1World, par2, par3, par4, par5EntityPlayer, GCLibrary.guiApperifierID);
    	return true;
    }
    
	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
		MiscUtils.dropItemsOnBlockBreak(par1World, par2, par3, par4, par5, par6);
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }



	@Override
	public void addInformation(ItemStack stk, EntityPlayer p, List lst,
			boolean held) {
		lst.add("Used to apperify blocks using ItemCards");
		lst.add("Inputs:");
		lst.add(" *Paradox:"+EnumChatFormatting.GREEN+" Bottom");
		lst.add("Outputs:");
		lst.add(" *Blocks:"+EnumChatFormatting.GREEN+" Top");
		lst.add("Internal:");
		lst.add(" *Paradox storage:"+EnumChatFormatting.GREEN+" 1000");
	}
}
