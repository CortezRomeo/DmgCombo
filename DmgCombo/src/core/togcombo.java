package core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Cortez.Main;

public class togcombo implements CommandExecutor{
	private Main plugin;
	public togcombo(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("togcombo").setExecutor(this);
	}


	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
				
			if(!(Main.tog.contains(p.getUniqueId()))) {
				Main.tog.add(p.getUniqueId());
				p.sendMessage(cc.c(plugin.getConfig().getString("togcombo.enable")));				
			} else {
				Main.tog.remove(p.getUniqueId());
				p.sendMessage(cc.c(plugin.getConfig().getString("togcombo.disable")));					
			}
			
		}
		return true;
	}
	

}
