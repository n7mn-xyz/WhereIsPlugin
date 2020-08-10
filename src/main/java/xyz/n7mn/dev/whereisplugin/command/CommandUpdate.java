package xyz.n7mn.dev.whereisplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.n7mn.dev.whereisplugin.WhereIsPlugin;
import xyz.n7mn.dev.whereisplugin.api.WhereIsData;
import xyz.n7mn.dev.whereisplugin.dataSystem.DataSystem;
import xyz.n7mn.dev.whereisplugin.dataSystem.DataSystemResult;
import xyz.n7mn.dev.whereisplugin.event.WhereisCompleteCommandEvent;
import xyz.n7mn.dev.whereisplugin.function.MessageList;

class CommandUpdate {

    private WhereIsPlugin plugin;
    private String[] args;
    private Player player;
    DataSystem system;
    MessageList messageList = new MessageList();
    WhereIsData WhereIsAPI;

    @Deprecated
    public CommandUpdate(WhereIsPlugin p, String[] args, Player player){
        this.plugin = p;
        this.args = args;
        this.player = player;
        this.WhereIsAPI = new WhereIsData();
    }

    public CommandUpdate(WhereIsPlugin p, String[] args, Player player, WhereIsData api){
        this.plugin = p;
        this.args = args;
        this.player = player;
        this.WhereIsAPI = api;
    }

    public boolean run(){

        if (player != null){
            system = new DataSystem(plugin, player);
        }else{
            system = new DataSystem(plugin);
        }

        DataSystemResult result = system.updateData(args[1], args[2]);

        String msg;
        if (result.isError()){
            msg = ChatColor.RED + result.getErrorMessage();
        }else{
            msg = ChatColor.YELLOW + messageList.getUpdateSuccessMessage(args[1], args[2]);
        }

        CommandSender sender = plugin.getServer().getConsoleSender();
        if (player != null){
            sender = player;
        }

        WhereisCompleteCommandEvent event = new WhereisCompleteCommandEvent(sender, msg, result.isError());
        plugin.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()){
            sender.sendMessage(msg);
        }
        return true;
    }
}
