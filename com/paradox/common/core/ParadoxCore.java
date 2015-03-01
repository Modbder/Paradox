package com.paradox.common.core;

import net.minecraft.command.CommandHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import DummyCore.Core.Core;
import DummyCore.Utils.IDummyConfig;

import com.paradox.blocks.ParadoxBlocks;
import com.paradox.common.CardLibrary;
import com.paradox.common.GCLibrary;
import com.paradox.common.ParadoxChatCommand;
import com.paradox.common.ParadoxServer;
import com.paradox.common.VoidProviderOverworld;
import com.paradox.common.events.BucketsHandler;
import com.paradox.common.events.PlayerSpawnHandler;
import com.paradox.common.events.WorldTickHandler;
import com.paradox.common.utils.ParadoxUtils;
import com.paradox.items.ParadoxItems;
import com.paradox.tile.TileApperifier;
import com.paradox.tile.TileHandyGenerator;
import com.paradox.tile.TileMRUGenerator;
import com.paradox.tile.TileParadoxLiquifier;
import com.paradox.tile.TileParadoxifier;
import com.paradox.tile.TilePotentiaGenerator;
import com.paradox.tile.TileRFGenerator;
import com.paradox.tile.TileTimer;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.SidedProxy;

@Mod(
	modid = ParadoxCore.modid,
	name = ParadoxCore.name,
	version = ParadoxCore.version
)
public class ParadoxCore
{

	public static Configuration config;
	@EventHandler
	public void beforeMinecraftLoaded(FMLPreInitializationEvent event)
	{
		if(instance == null)
			instance = this;
		try
		{
			Core.registerModAbsolute(getClass(), name, event.getModConfigurationDirectory().getAbsolutePath(),
			//This is why I LOVE java <3
			new IDummyConfig()
			{
				@Override
				public void load(Configuration cfg)
				{
					config=cfg;
					ticksToFinish = cfg.getInt("timeLimit", "GLOBAL", 20*60*60*120, 0, Integer.MAX_VALUE, "");
					generateVoid = cfg.getBoolean("generateVoid", "GLOBAL", true, "");
					
					teleportX = cfg.getFloat("tpX", "SPAWN", 0.0F, Float.MIN_VALUE, Float.MAX_VALUE, "");
					teleportY = cfg.getFloat("tpY", "SPAWN", 64.0F, Float.MIN_VALUE, Float.MAX_VALUE, "");
					teleportZ = cfg.getFloat("tpZ", "SPAWN", 0.0F, Float.MIN_VALUE, Float.MAX_VALUE, "");
					enableWorldSkyChange = cfg.getBoolean("enableSpaceSky", "GLOBAL", true, "");
					enableTeleportation = cfg.getBoolean("allowTeleportation", "SPAWN", true, "");
				}
			});
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		proxy.preInit(event);
		//MinecraftForge.EVENT_BUS.register(new WorldEventListener());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ParadoxBlocks.initBlocks();
		ParadoxItems.initItems();
		GCLibrary.register();
		
		FMLCommonHandler.instance().bus().register(new WorldTickHandler());
		FMLCommonHandler.instance().bus().register(new PlayerSpawnHandler());
		MinecraftForge.EVENT_BUS.register(new BucketsHandler());
		GameRegistry.registerTileEntity(TileTimer.class, "com.paradox.tile.TileTimer");
		GameRegistry.registerTileEntity(TileHandyGenerator.class, "com.paradox.tile.TileHandyGenerator");
		GameRegistry.registerTileEntity(TileParadoxLiquifier.class, "com.paradox.tile.TileParadoxLiquifier");
		GameRegistry.registerTileEntity(TileRFGenerator.class, "com.paradox.tile.TileSmallRFGenerator");
		GameRegistry.registerTileEntity(TileParadoxifier.class, "com.paradox.tile.TileParadoxifier");
		GameRegistry.registerTileEntity(TileMRUGenerator.class, "com.paradox.tile.TileMRUGenerator");
		GameRegistry.registerTileEntity(TilePotentiaGenerator.class, "com.paradox.tile.TilePotentiaGenerator");
		GameRegistry.registerTileEntity(TileApperifier.class, "com.paradox.tile.TileApperifier");
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if(generateVoid)
		{
			DimensionManager.unregisterProviderType(0);
			DimensionManager.registerProviderType(0, VoidProviderOverworld.class, true);
		}
		CardLibrary.load();
		ParadoxUtils.initAllCardsCrafts();
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event)
    {
        MinecraftServer mcserver = event.getServer();
        CommandHandler ch = (CommandHandler) mcserver.getCommandManager();
        ch.registerCommand(new ParadoxChatCommand());
    }
	
	@Instance(ParadoxCore.modid)
	public static ParadoxCore instance;
	
	@SidedProxy(clientSide="com.paradox.client.ParadoxClient",serverSide="com.paradox.common.ParadoxServer")
	public static ParadoxServer proxy;
	
	public static final String modid = "paradoxmod";
	public static final String name = "Paradox";
	public static final String version = "1.0.1710.0";
	
	public static int ticksToFinish;
	public static boolean generateVoid;
	public static boolean enableTeleportation;
	public static boolean enableWorldSkyChange;
	public static float teleportX, teleportY, teleportZ;
}
