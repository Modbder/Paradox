package com.paradox.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPinkVine extends Block implements IGrowable{

	public IIcon[] icons = new IIcon[8];
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
    	super.registerBlockIcons(p_149651_1_);
    	for(int i = 0; i < 8; ++i)
    	{
    		icons[i] = p_149651_1_.registerIcon("paradoxmod:pinkVine_"+i);
    	}
    }
	
	public BlockPinkVine() {
		super(Material.leaves);
		this.setTickRandomly(true);
		this.disableStats();
		
	}
	
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) 
	{
		if(!p_149695_1_.getBlock(p_149695_2_, p_149695_3_+1, p_149695_4_).isSideSolid(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_,ForgeDirection.DOWN))
		{
			p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
		}
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
	}
	
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	return true;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
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
	
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        super.updateTick(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);

        if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) <= 12)
        {
            int l = p_149674_1_.getBlockMetadata(p_149674_2_, p_149674_3_, p_149674_4_);

            if (l < 7)
            {
                float f = this.func_149864_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);

                if (p_149674_5_.nextInt((int)(10.0F / f) + 1) == 0)
                {
                    ++l;
                    p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
                }
            }else
            {
            	l = 3;
            	ItemStack pinkDye = new ItemStack(Items.dye,1+p_149674_1_.rand.nextInt(3),9);
            	EntityItem pinkDyeEntity = new EntityItem(p_149674_1_, p_149674_2_+0.5D, p_149674_3_, p_149674_4_+0.5D, pinkDye);
            	if(!p_149674_1_.isRemote)
            	{
            		p_149674_1_.spawnEntityInWorld(pinkDyeEntity);
            	}
                    ++l;
                    p_149674_1_.setBlockMetadataWithNotify(p_149674_2_, p_149674_3_, p_149674_4_, 3, 2);
            }
        }
    }
    
    private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_)
    {
        float f = 1.0F;
        return f;
    }
    
    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return p_149851_1_.getBlockMetadata(p_149851_2_, p_149851_3_, p_149851_4_) != 7;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }
    
    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.func_149863_m(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_);
    }
    
    public void func_149863_m(World p_149863_1_, int p_149863_2_, int p_149863_3_, int p_149863_4_)
    {
        int l = p_149863_1_.getBlockMetadata(p_149863_2_, p_149863_3_, p_149863_4_) + MathHelper.getRandomIntegerInRange(p_149863_1_.rand, 2, 5);

        if (l > 7)
        {
            l = 7;
        }

        p_149863_1_.setBlockMetadataWithNotify(p_149863_2_, p_149863_3_, p_149863_4_, l, 2);
    }

}
