package com.mikle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    public boolean playercomand=false;

    Logger log = getLogger();
    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents( this,this);

        for (int i=0;i<10;i++) {

            log.info("Plugin is work!");

        }
        super.onEnable();
        saveDefaultConfig();
        reloadConfig();
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){

        if (event.getAction()== Action.RIGHT_CLICK_BLOCK && playercomand==true){
                Block targerBlock =  event.getPlayer().getTargetBlock(null,10);
              //  event.getPlayer().sendMessage(ChatColor.RED+"block position in x,y,z"+targerBlock.getX()+targerBlock.getY()+targerBlock.getZ());
            //event.getPlayer().sendMessage("type of block"+targerBlock.getType().toString());
                float x = targerBlock.getX();
                float y =targerBlock.getY();
                float z =targerBlock.getZ();
                Location loc = new Location(event.getPlayer().getWorld(),x,y,z);
                if (loc.getBlock().getType()==Material.PODZOL) {
                    loc.getBlock().setType(Material.GRASS_BLOCK);

                }
                else
                {
                    //event.getPlayer().sendMessage("is not podzol");
                }
            }
    if(event.getPlayer().isDead()) {

        event.getPlayer().sendMessage("You dead in the " +
                "x:"+event.getPlayer().getLocation().getX() +
                "z:"+event.getPlayer().getLocation().getZ() +
                "y:"+event.getPlayer().getLocation().getY() );
    }


    }

    public void OnPlayerJoin (PlayerJoinEvent p){
        Player pl = p.getPlayer();
        pl.sendMessage(ChatColor.BLUE + "Welcome to the server!");
        log.info(pl.getLocation().toString());

        }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("teston")) {
            sender.sendMessage(ChatColor.GREEN+"AntiPodzol is ACTIVE");
            playercomand=true;
            return true;
        }
        if(command.getName().equalsIgnoreCase("testoff")) {
            sender.sendMessage(ChatColor.RED+"AntiPodzol is NOT active");
        playercomand=false;
            return true;
        }
    return false;
        //return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}

