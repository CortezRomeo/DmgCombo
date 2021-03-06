package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.Cortez.Main;

public class combo implements Listener {

	private Main plugin;
	public combo(Main plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	 HashMap<String, Calendar> playerdb = new HashMap<String, Calendar>();
	 HashMap<Player, Double> combo = new HashMap<Player, Double>();
	 List<Player> isCombo = new ArrayList<Player>();
	
	
	public double getDmgCombo(Player p) {
		if (!combo.containsKey(p)) return 0;	
		return combo.get(p);	
	}
	
	public void reset(Player p) {
		combo.put(p, 0.0);
	}
	
	public void addDmg(Player p,double i) {
		combo.put(p, getDmgCombo(p)+i);
	}		
	
	public void Combo(Player p,LivingEntity target, double dmg) {
		String admg = "%."+plugin.getConfig().getString("formatDamage")+"f";
		if (getDmgCombo(p)==0) {
			addDmg(p, dmg);			
			String actionbar = plugin.getConfig().getString("actionBar.noCombo");
			actionbar=actionbar.replace("%dmg%", String.format(admg, getDmgCombo(p)));
			
			for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
				String color = plugin.getConfig().getString("color."+getstring+".return");
				double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
				if(getDmgCombo(p)<getdmg)
					actionbar=actionbar.replace("<color>", color);
				}
			p.sendActionBar(cc.c(actionbar));	
			
		} else {
			if(!isCombo.contains(p)) isCombo.add(p);
			addDmg(p, dmg);			
				String actionbar = plugin.getConfig().getString("actionBar.Combo");
				actionbar=actionbar.replace("%dmg%", String.format(admg, getDmgCombo(p)));
				
					for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
						String color = plugin.getConfig().getString("color."+getstring+".return");
						double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
						if(getDmgCombo(p)<getdmg) 
							actionbar=actionbar.replace("<color>", color);
					}			
			p.sendActionBar(cc.c(actionbar));	
				}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(AA(p)==false) cancel();
					if(target.getHealth()<0.1 || target.isDead()==true) {
						
						// Target là mob
						if(!(target.getType() == EntityType.PLAYER )) {							
							try {
								for(String end : plugin.getConfig().getStringList("end.targetDeathMOB.message")) {
									end = end.replace("%dmg%", String.format(admg, getDmgCombo(p)));
									end=end.replace("%mobName%", target.getName());								
									if(target.getCustomName()==null) {
										end=end.replace("%mobDisplayName%", target.getName());
									} else end=end.replace("%mobDisplayName%", target.getCustomName());
									
									for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
										String color = plugin.getConfig().getString("color."+getstring+".return");
										double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
										if(getDmgCombo(p)<getdmg)
											end=end.replace("<color>", color);
										
									}		
										if(plugin.getConfig().getBoolean("end.targetDeathMOB.actionBarEnable")) {
											
										String actb= plugin.getConfig().getString("end.targetDeathMOB.actionBar");
										actb=actb.replace("%mobName%", target.getName());
										if(target.getCustomName()==null) {
											actb=actb.replace("%mobDisplayName%", target.getName());
										} else actb=actb.replace("%mobDisplayName%", target.getCustomName());
										actb = actb.replace("%dmg%", String.format(admg, getDmgCombo(p)));	
										for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
											String color = plugin.getConfig().getString("color."+getstring+".return");
											double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
											if(getDmgCombo(p)<getdmg) 
												actb=actb.replace("<color>", color);
										} 
										p.sendActionBar(cc.c(actb));
									}
									p.sendMessage(cc.c(end));
								}
									if(plugin.getConfig().getBoolean("end.targetDeathMOB.enableSound")==true) {
									p.playSound(p.getLocation(), Sound.valueOf(plugin.getConfig().getString("end.targetDeathMOB.sound")),
											1, 1);
									}
										} catch (Exception e) {
											
										}							
						} else {
							
						// Target là Player
							for(String end : plugin.getConfig().getStringList("end.targetDeath.message")) {
								end = end.replace("%dmg%", String.format(admg, getDmgCombo(p)));								
								end = end.replace("%target%", target.getName());								
								for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
									String color = plugin.getConfig().getString("color."+getstring+".return");
									double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
									if(getDmgCombo(p)<getdmg) 
										end=end.replace("<color>", color);
								}		
									if(plugin.getConfig().getBoolean("end.targetDeath.actionBarEnable")) {
										
									String actb= plugin.getConfig().getString("end.targetDeath.actionBar");
									actb=actb.replace("%target%", target.getName());
									actb = actb.replace("%dmg%", String.format(admg, getDmgCombo(p)));	
									for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
										String color = plugin.getConfig().getString("color."+getstring+".return");
										double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
										if(getDmgCombo(p)<getdmg) 
											actb=actb.replace("<color>", color);
									} 
									p.sendActionBar(cc.c(actb));
								}
								p.sendMessage(cc.c(end));
							}
								if(plugin.getConfig().getBoolean("end.targetDeath.enableSound")==true) {
								p.playSound(p.getLocation(), Sound.valueOf(plugin.getConfig().getString("end.targetDeath.sound")),
										1, 1);
								}	
						}
						cancel();
							
							// rời khỏi vòng lặp nếu không có ai chết
						} else cancel();
				
			}
		}.runTaskTimerAsynchronously(plugin, 0, 5);
		new BukkitRunnable() {
			@Override
			public void run() {		
				if(AA(p)!=true) {
					if(isCombo.contains(p)) {
						for(String end : plugin.getConfig().getStringList("end.endTime.message")) {
							end = end.replace("%dmg%", String.format(admg, getDmgCombo(p)));
							for(String getstring : plugin.getConfig().getConfigurationSection("color.").getKeys(false)) {
								String color = plugin.getConfig().getString("color."+getstring+".return");
								double getdmg = plugin.getConfig().getDouble("color."+getstring+".dmg");
								if(getDmgCombo(p)<getdmg) 
									end=end.replace("<color>", color);														
							}
							p.sendMessage(cc.c(end));
						}
							if(plugin.getConfig().getBoolean("end.endTime.enableSound")==true) {
							p.playSound(p.getLocation(), Sound.valueOf(plugin.getConfig().getString("end.endTime.sound")),
									1, 1);
							}
					}
					reset(p);
					isCombo.remove(p);
				}
			}
		}.runTaskLater(plugin, 20*plugin.getConfig().getInt("time")+1);
	}
	public boolean AA(Player player)
	{
		Calendar c = playerdb.get(player.getName());
			if (c == null)
				return false;
		return Calendar.getInstance().before(c);
	}

	public void Tag(Player player, int timeout)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, timeout);
		Calendar existing = playerdb.get(player.getName());
		if (existing == null || c.after(existing))
			{
				playerdb.put(player.getName(), c);
			}
	}
	
	@EventHandler
	public void event(EntityDamageByEntityEvent e) {						
		if(e.getDamager() instanceof Player ) {		
			if(e.getEntity().getType() != EntityType.ARMOR_STAND) {
				if(Main.tog.contains(e.getDamager().getUniqueId())) {
		  Tag((Player) e.getDamager(), plugin.getConfig().getInt("time"));
		  Combo((Player) e.getDamager(),(LivingEntity) e.getEntity(), e.getDamage());
				}
			}
		}
	}
	
	@EventHandler
	public void pjoin(PlayerJoinEvent e) {
		Main.tog.add(e.getPlayer().getUniqueId());
	}
}
