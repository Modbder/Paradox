package com.paradox.blocks;

import java.util.Random;

import DummyCore.Core.CoreInitialiser;
import DummyCore.Utils.DummyPacketIMSG;
import DummyCore.Utils.MiscUtils;

import com.paradox.items.ParadoxItems;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFuturisticDoor extends Block{

	public final String textureGenName;
	
	public IIcon[] doorIcons = new IIcon[2];
	
	public BlockFuturisticDoor(String textureName) 
	{
		super(Material.iron);
		textureGenName = textureName;
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
    {
    	int metadata = p_149673_1_.getBlockMetadata(p_149673_2_, p_149673_3_, p_149673_4_);
    	if(metadata < 8)
    		return doorIcons[0];
    	else
    		return doorIcons[1];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister r)
    {
    	super.registerBlockIcons(r);
    	doorIcons[0] = r.registerIcon("paradoxmod:"+this.textureGenName+"_d");
    	doorIcons[1] = r.registerIcon("paradoxmod:"+this.textureGenName+"_u");
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
    {
    	return false;
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 7;
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
    {
        this.setBlockBoundsBasedOnState(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
        return super.getSelectedBoundingBoxFromPool(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        this.setBlockBoundsBasedOnState(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
    	int metadata = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_);
    	boolean upperDoor = false;
    	if(metadata >= 8)
    	{
    		metadata = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_-1, p_149719_4_);
    		upperDoor = true;
    	}
    	
    	float genericWidth_decrease = 0.45F;
    	float genericWidth_set = 0.55F;
    	float openHeightOffset = 1.01F;
    	
    	if(upperDoor)
    	{
    		openHeightOffset = -0.9F;
    	}
    	
    	switch(metadata)
    	{
	    	case 0:
	    	{
	    		this.setBlockBounds(genericWidth_decrease, 0, 0, genericWidth_set, 1, 1);
	    		break;
	    	}
	    	case 2:
	    	{
	    		this.setBlockBounds(genericWidth_decrease, 0, 0, genericWidth_set, 1, 1);
	    		break;
	    	}
	    	
	    	case 1:
	    	{
	    		this.setBlockBounds(0, 0, genericWidth_decrease, 1, 1, genericWidth_set);
	    		break;
	    	}
	    	case 3:
	    	{
	    		this.setBlockBounds(0, 0, genericWidth_decrease, 1, 1, genericWidth_set);
	    		break;
	    	}
	    	
	    	case 4:
	    	{
	    		this.setBlockBounds(genericWidth_decrease, -openHeightOffset, 0, genericWidth_set, 1-openHeightOffset, 1);
	    		break;
	    	}
	    	case 6:
	    	{
	    		this.setBlockBounds(genericWidth_decrease, -openHeightOffset, 0, genericWidth_set, 1-openHeightOffset, 1);
	    		break;
	    	}
	    	
	    	case 5:
	    	{
	    		this.setBlockBounds(0, -openHeightOffset, genericWidth_decrease, 1, 1-openHeightOffset, genericWidth_set);
	    		break;
	    	}
	    	case 7:
	    	{
	    		this.setBlockBounds(0, -openHeightOffset, genericWidth_decrease, 1, 1-openHeightOffset, genericWidth_set);
	    		break;
	    	}
    	}
    }
    
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	this.triggerDoor(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
    	handleSound(p_149727_1_, p_149727_2_+0.5D, p_149727_3_+0.5D, p_149727_4_+0.5D);
    	if(p_149727_1_.getBlock(p_149727_2_, p_149727_3_-1, p_149727_4_) == this)
    	{
    		this.triggerDoor(p_149727_1_, p_149727_2_, p_149727_3_-1, p_149727_4_);
    	}
    	if(p_149727_1_.getBlock(p_149727_2_, p_149727_3_+1, p_149727_4_) == this)
    	{
    		this.triggerDoor(p_149727_1_, p_149727_2_, p_149727_3_+1, p_149727_4_);
    	}
    	return true;
    }
    
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
    	int metadata = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
    	if(metadata >= 8)
    	{
    		Block b = p_149695_1_.getBlock(p_149695_2_, p_149695_3_-1, p_149695_4_);
    		if(b != this)
    			p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
    	}
    	else
    	{
    		Block b = p_149695_1_.getBlock(p_149695_2_, p_149695_3_+1, p_149695_4_);
    		if(b != this)
    			p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
    	}	
    	
    	boolean doorOpen = (metadata >= 4 && metadata < 8) || metadata == 9;
    	
    	if(doorOpen && !p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
    	{
    		
        	this.triggerDoor(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
        	handleSound(p_149695_1_, p_149695_2_+0.5D, p_149695_3_+0.5D, p_149695_4_+0.5D);
        	if(p_149695_1_.getBlock(p_149695_2_, p_149695_3_-1, p_149695_4_) == this)
        	{
        		this.triggerDoor(p_149695_1_, p_149695_2_, p_149695_3_-1, p_149695_4_);
        	}
        	if(p_149695_1_.getBlock(p_149695_2_, p_149695_3_+1, p_149695_4_) == this)
        	{
        		this.triggerDoor(p_149695_1_, p_149695_2_, p_149695_3_+1, p_149695_4_);
        	}
    	}
    	
    	if(!doorOpen && p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_))
    	{
    		
        	this.triggerDoor(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
        	handleSound(p_149695_1_, p_149695_2_+0.5D, p_149695_3_+0.5D, p_149695_4_+0.5D);
        	if(p_149695_1_.getBlock(p_149695_2_, p_149695_3_-1, p_149695_4_) == this)
        	{
        		this.triggerDoor(p_149695_1_, p_149695_2_, p_149695_3_-1, p_149695_4_);
        	}
        	if(p_149695_1_.getBlock(p_149695_2_, p_149695_3_+1, p_149695_4_) == this)
        	{
        		this.triggerDoor(p_149695_1_, p_149695_2_, p_149695_3_+1, p_149695_4_);
        	}
    	}
    }
    
    public void handleSound(World w, double x, double y, double z)
    {
    	if(!w.isRemote)
    	{
    		String soundString = "||mod:paradoxSound"+"||x:"+x+"||y:"+y+"||z:"+z+"||type:paradoxmod^sound.doorTrigger";
    		
    		DummyPacketIMSG pkt = new DummyPacketIMSG(soundString);
    		
    		CoreInitialiser.network.sendToAllAround(pkt, new TargetPoint(w.provider.dimensionId, x, y, z, 8));
    	}
    }
    
    public MovingObjectPosition collisionRayTrace(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_)
    {
        this.setBlockBoundsBasedOnState(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_);
        return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
    }
    
    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
    {
        return p_149742_3_ >= p_149742_1_.getHeight() - 1 ? false : World.doesBlockHaveSolidTopSurface(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_) && super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_) && super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_ + 1, p_149742_4_);
    }
    
    public int getMobilityFlag()
    {
        return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return ParadoxItems.doorPlacer;
    }
    
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
    	return ParadoxItems.doorPlacer;
    }
    
    public int damageDropped(int p_149692_1_)
    {
        return getDroppedMetadata();
    }
    
    public int getDroppedMetadata()
    {
    	return this.textureGenName.equals("door_generic") ? 0 : this.textureGenName.equals("door_observatory") ? 1 : this.textureGenName.equals("door_technical") ? 2 : this.textureGenName.equals("door_reactor") ? 3 : -1;
    }

    public void triggerDoor(World w, int x, int y, int z)
    {
    	int metadata = w.getBlockMetadata(x, y, z);
    	if(metadata < 8)
    	{
    		if(metadata < 4)
    		{
    			metadata += 4;
    		}else
    		{
    			metadata -= 4;
    		}
    	}else
    	{
    		if(metadata == 8)
    			metadata = 9;
    		else
    			metadata = 8;
    	}
    	w.setBlockMetadataWithNotify(x, y, z, metadata, 2);
    }
    
    public void onBlockHarvested(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_)
    {
        if (p_149681_6_.capabilities.isCreativeMode && (p_149681_5_ & 8) != 0 && p_149681_1_.getBlock(p_149681_2_, p_149681_3_ - 1, p_149681_4_) == this)
        {
            p_149681_1_.setBlockToAir(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
        }
    }

}
