package com.paradox.blocks;

import com.paradox.tile.TileTimer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockParadoxSecret extends Block{
	
	public BlockParadoxSecret()
	{
		super(Material.iron);
	}
	
    @Override
    public int onBlockPlaced(World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return side;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
    	int metadata = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_);

    	if(metadata == 2)
    	{
    		this.setBlockBounds(0, 0, 0.999F, 1, 1, 1F);
    	}
    	if(metadata == 3)
    	{
    		this.setBlockBounds(0, 0, 0.0F, 1, 1, 0.001F);
    	}
    	if(metadata == 4)
    	{
    		this.setBlockBounds(0.999F, 0, 0F, 1, 1, 1F);
    	}
    	if(metadata == 5)
    	{
    		this.setBlockBounds(0.0F, 0, 0, 0.001F, 1, 1F);
    	}
    	super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
    
	@Override
    public int getRenderBlockPass()
    {
        return 0;
    }
    

}
