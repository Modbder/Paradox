package com.paradox.blocks;

import java.util.List;

import com.paradox.api.IInformationProvider;
import com.paradox.tile.TileTimer;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockParadoxTimer extends BlockContainer implements IInformationProvider{
	
	public BlockParadoxTimer()
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
    		this.setBlockBounds(-1, 0, 0.9F, 2, 1, 1F);
    	}
    	if(metadata == 3)
    	{
    		this.setBlockBounds(-1, 0, 0.0F, 2, 1, 0.1F);
    	}
    	if(metadata == 4)
    	{
    		this.setBlockBounds(0.9F, 0, -1.0F, 1, 1, 2F);
    	}
    	if(metadata == 5)
    	{
    		this.setBlockBounds(0.0F, 0, -1.0F, 0.1F, 1, 2F);
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
		return new TileTimer();
	}

	@Override
	public void addInformation(ItemStack stk, EntityPlayer p, List lst,
			boolean held) {
		lst.add("Counts down the time you have left");
		lst.add("No, breaking this block does not stop the countdown");
		
	}

}
