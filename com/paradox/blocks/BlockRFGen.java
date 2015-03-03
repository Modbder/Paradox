package com.paradox.blocks;

import java.util.List;

import com.paradox.api.IInformationProvider;
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
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRFGen extends BlockContainer implements IInformationProvider{
	
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
	
	@Override
	public void addInformation(ItemStack stk, EntityPlayer p, List lst,
			boolean held) {
		int paradoxProduced = 0;
		int cooldown = 0;
		int rftick = 0;
		int pS = 0;
		int rfS = 0;
		switch(stk.getItemDamage())
		{
			case 0:
			{
				paradoxProduced = 1;
				cooldown = 4;
				rftick = 10;
				pS = 100;
				rfS = 32000;
				break;
			}
			case 1:
			{
				paradoxProduced = 3;
				cooldown = 2;
				rftick = 60;
				pS = 250;
				rfS = 64000;
				break;
			}
			case 2:
			{
				paradoxProduced = 8;
				cooldown = 1;
				rftick = 300;
				pS = 500;
				rfS = 128000;
				break;
			}
			case 3:
			{
				paradoxProduced = 40;
				cooldown = 1;
				rftick = 1800;
				pS = 1000;
				rfS = 512000;
				break;
			}
			default:
			{
				break;
			}
		}
		lst.add("Used to convert RF to Paradox");
		lst.add("Inputs:");
		lst.add(" *RF:"+EnumChatFormatting.GREEN+" Any side, but Bottom");
		lst.add("Outputs:");
		lst.add(" *Paradox:"+EnumChatFormatting.GREEN+" Top");
		lst.add("Internal:");
		lst.add(" *Paradox storage:"+EnumChatFormatting.GREEN+" "+pS);
		lst.add(" *RF storage:"+EnumChatFormatting.GREEN+" "+rfS);
		lst.add(" *Uses:"+EnumChatFormatting.GREEN+" "+rftick+"RF/Tick");
		lst.add(" *Generates:"+EnumChatFormatting.GREEN+" "+paradoxProduced+"Paradox/"+cooldown+" Second(s)");
	}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	return false;
    }
}
