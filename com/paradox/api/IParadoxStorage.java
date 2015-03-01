package com.paradox.api;

import net.minecraftforge.common.util.ForgeDirection;

public interface IParadoxStorage {
	
	public float getParadox();
	
	public boolean canEjectParadoxFrom(ForgeDirection dir);
	
	public boolean canInsertParadoxInto(ForgeDirection dir);
	
	public boolean setParadox(float newAmount);
	
	public boolean setMaxParadox(float newAmount);
	
	public boolean increaseParadoxBy(float increasedBy);
	
	public float getMaxParadox();

}
