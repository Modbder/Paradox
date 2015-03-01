package com.paradox.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TileHandyGenerator extends TileParadoxCommon{
	public int cooldown = 0;
	
	public TileHandyGenerator()
	{
		this.setEjectParadox(true);
		this.setAcceptParadox(false);
		this.setMaxParadox(40);
	}
	
	public void updateEntity()
	{
		if(cooldown > 0)--cooldown;
		super.updateEntity();
	}
	
	public void click(EntityPlayer player)
	{
		if(cooldown <= 0)
		{
			if(player.getFoodStats().getFoodLevel() > 3.0F && this.increaseParadoxBy(1))
			{
				cooldown = 100;
				player.addExhaustion(10F);
				player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 300, 3, true));
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300, 3, true));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 300, 3, true));
				
				for(int i = 0; i < 20; ++i)
					player.worldObj.playSound(player.posX, player.posY, player.posZ, "random.door_open", 1, i/10F, true);
			}
		}
	}
}
