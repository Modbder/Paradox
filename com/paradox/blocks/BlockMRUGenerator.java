package com.paradox.blocks;

import java.util.List;

import DummyCore.Utils.MiscUtils;

import com.paradox.api.IInformationProvider;
import com.paradox.common.GCLibrary;
import com.paradox.tile.TileHandyGenerator;
import com.paradox.tile.TileMRUGenerator;
import com.paradox.tile.TileParadoxLiquifier;
import com.paradox.tile.TileTimer;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMRUGenerator extends BlockContainer implements IInformationProvider{
	
	public BlockMRUGenerator()
	{
		super(Material.iron);
	}
	
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return 0;
    }
    
	@Override
	public void addInformation(ItemStack stk, EntityPlayer p, List lst,
			boolean held) {
		lst.add("Used to generate Paradox using MRU");
		lst.add("Inputs:");
		lst.add(" *MRU:"+EnumChatFormatting.GREEN+" Any Side");
		lst.add("Outputs:");
		lst.add(" *Paradox:"+EnumChatFormatting.GREEN+" Top");
		lst.add("Internal:");
		lst.add(" *Paradox storage:"+EnumChatFormatting.GREEN+" 100");
		lst.add(" *MRU storage:"+EnumChatFormatting.GREEN+" 5000");
		lst.add(" *Uses:"+EnumChatFormatting.GREEN+"100MRU/tick");
		lst.add(" *Generates:"+EnumChatFormatting.GREEN+"1Paradox/Second");
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
		return new TileMRUGenerator();
	}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	MiscUtils.openGui(par1World, par2, par3, par4, par5EntityPlayer, GCLibrary.guiIDMRUGen);
    	return true;
    }
}
