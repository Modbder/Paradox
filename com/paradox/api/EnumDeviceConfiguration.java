package com.paradox.api;

/**
 * 
 * @author Modbder
 * @Descriprion used to ease rendering of different devices.
 */
public enum EnumDeviceConfiguration {
	
	ITEMS(0),
	FLUIDS(1),
	PARADOX(2);
	
	EnumDeviceConfiguration(int i)
	{
		id = i;
	}
	
	public int getID()
	{
		return id;
	}
	
	//Not sure, why not just make the variable public?
	int id;

}
