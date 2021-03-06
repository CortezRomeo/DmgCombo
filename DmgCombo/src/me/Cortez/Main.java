package me.Cortez;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import core.combo;
import core.reloadcmd;
import core.togcombo;


public class Main extends JavaPlugin{
	
	public static List<UUID> tog = new ArrayList<UUID>();
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§aEnable Plug-in §cDmgCombo");
		Bukkit.getConsoleSender().sendMessage("§aVersion: §e"+getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§aAuthor: §eCortez Romeo");
			loadConfig();	
			if(getConfig().getDouble("config-version")!=1.2) {
				Bukkit.getServer().getConsoleSender().sendMessage("§c§lCÓ VẺ NHƯ BẠN ĐANG SỬ DỤNG BẢN CONFIG CŨ, XÓA FILE CONFIG CŨ VÀ CHỜ LOAD LẠI ĐỂ XEM CÓ GÌ MỚI NHÉ!");
					
			}		
		new combo(this);
		new reloadcmd(this);
		new togcombo(this);
		}	
	public void loadConfig() {		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();		
	}	
	
}
