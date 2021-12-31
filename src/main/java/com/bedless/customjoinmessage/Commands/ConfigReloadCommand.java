package com.bedless.customjoinmessage.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.bedless.customjoinmessage.CustomJoinMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@CommandAlias("cjmreload")
@CommandPermission("cjm.reload")
public class ConfigReloadCommand extends BaseCommand {
    @Default
    public void onCommand(Player p){
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "CustomJoinMessage" + ChatColor.GRAY + "]" + ChatColor.GREEN + " Configuration File reloaded");
        CustomJoinMessage.getInstance().reloadConfig();
    }
}
