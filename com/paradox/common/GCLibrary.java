package com.paradox.common;

import DummyCore.Utils.GuiContainerLibrary;

public class GCLibrary {
	
	public static void register()
	{
		guiIDMRUGen = GuiContainerLibrary.registerGuiContainer("ec3.client.gui.GuiRayTower", "com.paradox.common.inventory.InventoryMRUGenerator");
		guiApperifierID = GuiContainerLibrary.registerGuiContainer("DummyCore.Client.GuiCommon", "com.paradox.common.inventory.InventoryApperifier");
	}
	
	public static int guiIDMRUGen, guiApperifierID;

}
