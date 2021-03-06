package core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.Cortez.Main;

public class reloadcmd implements CommandExecutor{
	private Main plugin;
	public reloadcmd(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("comboreload").setExecutor(this);
	}
	
	public static Inventory inv;

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
				if(p.hasPermission("dmgcombo.reload")) {					
					plugin.reloadConfig();
					p.sendMessage("§cDmgCombo §aReload thành công!");
					p.sendTitle("", "§cDmgCombo §aReloaded!", 0, 30, 0);
					
				} else p.sendMessage(cc.c(plugin.getConfig().getString("no-permission")));
		} else {
			plugin.reloadConfig();
			sender.sendMessage("§cDmgCombo §aReloaded!");
		}
		return true;
	}
}
