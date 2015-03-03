package com.paradox.api;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * @author Modbder
 * @Description Implement this in your TileEntity class in order for it to be able to accept and eject paradox.
 *
 */
public interface IParadoxStorage {
	
	//Should return the amount of paradox in the device
	public float getParadox();
	
	//If the paradox can be ejected from the given side.
	public boolean canEjectParadoxFrom(ForgeDirection dir);
	
	//If the paradox can be inserted in the given side.
	public boolean canInsertParadoxInto(ForgeDirection dir);
	
	//If the paradox can be set to the new value. You should check, that newAmount is <= than Max, and >= than 0
	public boolean setParadox(float newAmount);
	
	//If the MaxParadox can be set to the given value. 
	public boolean setMaxParadox(float newAmount);
	
	//If the paradox can be increased by the given number. The same as in setParadox, but this time, check for yourParadox+increasedBy
	public boolean increaseParadoxBy(float increasedBy);
	
	//Should return the max amount of paradox the device can hold.
	public float getMaxParadox();

}
