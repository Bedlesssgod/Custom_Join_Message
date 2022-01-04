package com.bedless.customjoinmessage;

import co.aikar.commands.PaperCommandManager;;
import com.bedless.customjoinmessage.Commands.ConfigReloadCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinMessage extends JavaPlugin implements Listener {
    private static CustomJoinMessage INSTANCE;
    public static CustomJoinMessage getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        String version1 = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3].split("_")[1];
        if(version1.equals(18)){
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Join Message Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(line1);
        } else {
            String line1 = ChatColor.GREEN + "===================";
            Bukkit.getConsoleSender().sendMessage(line1);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Join Message Plugin");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Enabled");
            Bukkit.getConsoleSender().sendMessage("Running on " + Bukkit.getBukkitVersion());
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "This Plugin is not running on the intended Spigot Version!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Plugin may not behave as expected!");
            Bukkit.getConsoleSender().sendMessage(line1);
        }
        registerEvents();
        registerCommands();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void registerCommands(){
        PaperCommandManager cdm = new PaperCommandManager(this);
        cdm.registerCommand(new ConfigReloadCommand());
    }

    @Override
    public void onDisable() {
        String line2 = ChatColor.RED + "===================";
        Bukkit.getConsoleSender().sendMessage(line2);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Custom Join Message Plugin");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Disabled");
        Bukkit.getConsoleSender().sendMessage(line2);
        saveDefaultConfig();
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if(this.getConfig().getBoolean("Join_message_enabled")) {
            if(this.getConfig().getString("Join_message").contains("default")){
            }
            String joinraw = this.getConfig().getString("Join_message");
            String joinmsg = joinraw.replaceAll("%player%", e.getPlayer().getName());
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', joinmsg));
        } else {
            e.setJoinMessage("");
        }
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e){
        if(this.getConfig().getBoolean("Quit_message_enabled")) {
            if(this.getConfig().getString("Quit_message").contains("default")){
            }
            String joinraw = this.getConfig().getString("Quit_message");
            String joinmsg = joinraw.replaceAll("%player%", e.getPlayer().getName());
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', joinmsg));
        }else {
            e.setQuitMessage("");
        }
    }
}
