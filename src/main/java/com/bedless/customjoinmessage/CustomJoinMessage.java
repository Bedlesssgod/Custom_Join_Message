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

    String Enable1 = ChatColor.DARK_GREEN + "++++++++++++++++++++++++++";
    String Disable1 = ChatColor.DARK_RED + "++++++++++++++++++++++++++";
    String PluginName = ChatColor.AQUA + "Custom Join Message";
    String enabled = ChatColor.GREEN + "Enabled";
    String Disabled = ChatColor.RED + "Disabled";

    @Override
    public void onEnable() {
        INSTANCE = this;
        Bukkit.getConsoleSender().sendMessage(Enable1);
        Bukkit.getConsoleSender().sendMessage(PluginName);
        Bukkit.getConsoleSender().sendMessage(enabled);
        Bukkit.getConsoleSender().sendMessage(Enable1);
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
        Bukkit.getConsoleSender().sendMessage(Disable1);
        Bukkit.getConsoleSender().sendMessage(PluginName);
        Bukkit.getConsoleSender().sendMessage(Disabled);
        Bukkit.getConsoleSender().sendMessage(Disable1);
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
