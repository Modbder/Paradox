package com.paradox.blocks;

import java.util.List;

import com.paradox.tile.TileHandyGenerator;
import com.paradox.tile.TileParadoxLiquifier;
import com.paradox.tile.TileRFGenerator;
import com.paradox.tile.TileTimer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRFGen extends BlockContainer{
	
	public BlockRFGen()
	{
		super(Material.iron);
	}
	
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return 986555;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
    	for(int i = 0; i < 4; ++i)
    		p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
    }
			
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return meta;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
    	super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
    }

    public int damageDropped(int meta)
    {
        return meta;
    }
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int metadata)
	{
		return new TileRFGenerator(metadata);
	}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	return false;
    }
}
