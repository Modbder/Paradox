package com.paradox.api;

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
	
	int id;

}
