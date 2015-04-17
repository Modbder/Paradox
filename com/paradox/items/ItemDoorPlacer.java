package com.paradox.items;

import java.util.List;

import com.paradox.blocks.ParadoxBlocks;
import com.paradox.common.utils.ParadoxUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemDoorPlacer extends Item
{
    private Material doorMaterial;
    public IIcon[] icons = new IIcon[4];
    public String[] doorNames = {"door_generic","door_observatory","door_technical","door_reactor"};

    public ItemDoorPlacer(Material p_i45334_1_)
    {
        this.doorMaterial = p_i45334_1_;
        this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.setMaxStackSize(16);
		this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        super.registerIcons(p_94581_1_);
        for(int j = 0; j < 4; ++j)
        	icons[j] = p_94581_1_.registerIcon("paradoxmod:"+doorNames[j]);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
    	return icons[p_77617_1_];
    }
    
    public String getUnlocalizedName(ItemStack p_77667_1_)
    {
    	return super.getUnlocalizedName() + "." + doorNames[p_77667_1_.getItemDamage()];
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item i, CreativeTabs ct, List lst)
    {
    	for(int j = 0; j < 4; ++j)
    		lst.add(new ItemStack(i, 1, j));
    }

    
    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	int meta = p_77648_1_.getItemDamage();
        if (p_77648_7_ != 1)
        {
            return false;
        }
        else
        {
            ++p_77648_5_;
            Block block = Blocks.air;
            
            if (meta==0)
            {
                block = ParadoxBlocks.door;
            }
            if (meta==1)
            {
                block = ParadoxBlocks.door_1;
            }
            if (meta==2)
            {
                block = ParadoxBlocks.door_2;
            }
            if(meta == 3)
            {
                block = ParadoxBlocks.door_3;
            }

            if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_))
            {
                if (!block.canPlaceBlockAt(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_))
                {
                    return false;
                }
                else
                {
                    int i1 = MathHelper.floor_double((double)((p_77648_2_.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, i1, block);
                    --p_77648_1_.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void placeDoorBlock(World p_150924_0_, int p_150924_1_, int p_150924_2_, int p_150924_3_, int p_150924_4_, Block p_150924_5_)
    {
        byte b0 = 0;
        byte b1 = 0;

        if (p_150924_4_ == 0)
        {
            b1 = 1;
        }

        if (p_150924_4_ == 1)
        {
            b0 = -1;
        }

        if (p_150924_4_ == 2)
        {
            b1 = -1;
        }

        if (p_150924_4_ == 3)
        {
            b0 = 1;
        }

        int i1 = (p_150924_0_.getBlock(p_150924_1_ - b0, p_150924_2_, p_150924_3_ - b1).isNormalCube() ? 1 : 0) + (p_150924_0_.getBlock(p_150924_1_ - b0, p_150924_2_ + 1, p_150924_3_ - b1).isNormalCube() ? 1 : 0);
        int j1 = (p_150924_0_.getBlock(p_150924_1_ + b0, p_150924_2_, p_150924_3_ + b1).isNormalCube() ? 1 : 0) + (p_150924_0_.getBlock(p_150924_1_ + b0, p_150924_2_ + 1, p_150924_3_ + b1).isNormalCube() ? 1 : 0);
        boolean flag = p_150924_0_.getBlock(p_150924_1_ - b0, p_150924_2_, p_150924_3_ - b1) == p_150924_5_ || p_150924_0_.getBlock(p_150924_1_ - b0, p_150924_2_ + 1, p_150924_3_ - b1) == p_150924_5_;
        boolean flag1 = p_150924_0_.getBlock(p_150924_1_ + b0, p_150924_2_, p_150924_3_ + b1) == p_150924_5_ || p_150924_0_.getBlock(p_150924_1_ + b0, p_150924_2_ + 1, p_150924_3_ + b1) == p_150924_5_;
        boolean flag2 = false;

        p_150924_0_.setBlock(p_150924_1_, p_150924_2_, p_150924_3_, p_150924_5_, p_150924_4_, 2);
        p_150924_0_.setBlock(p_150924_1_, p_150924_2_ + 1, p_150924_3_, p_150924_5_, 8 | (flag2 ? 1 : 0), 2);
        p_150924_0_.notifyBlocksOfNeighborChange(p_150924_1_, p_150924_2_, p_150924_3_, p_150924_5_);
        p_150924_0_.notifyBlocksOfNeighborChange(p_150924_1_, p_150924_2_ + 1, p_150924_3_, p_150924_5_);
    }
}