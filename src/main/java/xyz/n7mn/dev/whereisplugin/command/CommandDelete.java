package xyz.n7mn.dev.whereisplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.n7mn.dev.whereisplugin.WhereIsPlugin;
import xyz.n7mn.dev.whereisplugin.api.WhereIsData;
import xyz.n7mn.dev.whereisplugin.event.WhereisCompleteCommandEvent;
import xyz.n7mn.dev.whereisplugin.function.MessageList;

class CommandDelete {

    private WhereIsPlugin plugin;
    private String[] args;
    private Player player;
    private WhereIsData WhereIsAPI;

    public CommandDelete(WhereIsPlugin p, String[] args, Player player, WhereIsData api) {
        this.plugin = p;
        this.args = args;
        this.player = player;
        this.WhereIsAPI = api;
    }

    public boolean run(){

        boolean b = false;
        if (player != null){
            int whereDataID = WhereIsAPI.getWhereDataID(args[1], player.getUniqueId());
            if (whereDataID != -1){
                b = WhereIsAPI.deleteWhereData(whereDataID);
            }
        }

        String msg = ChatColor.YELLOW + new MessageList().getDelSuccess(args[1]);
        if (!b){
            msg = ChatColor.RED + WhereIsAPI.getErrorMessage();
        }

        CommandSender sender = plugin.getServer().getConsoleSender();
        if (player != null){
            sender = player;
        }

        WhereisCompleteCommandEvent event = new WhereisCompleteCommandEvent(sender, msg, !b);
        plugin.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()){
            sender.sendMessage(msg);
        }

        return true;
    }
}
