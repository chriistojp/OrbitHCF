package me.christo.orbithcf.Listeners;

import me.christo.orbithcf.Main;
import me.christo.orbithcf.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class SwitcherListener implements Listener {

    @EventHandler
    public void switcher(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Snowball) {
            if (e.getEntity() instanceof Player) {
                Snowball snowball = (Snowball) e.getDamager();
                Player shooter = (Player) snowball.getShooter();
                shooter.sendMessage("t");
                try {
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(Util.chat(Main.getInstance().getConfig().getString("Switcher.SwitcherName")))) {

                        if (shooter.getLocation().distance(e.getEntity().getLocation()) > 12) {
                            shooter.sendMessage(Util.chat("&cPlayer too far away!"));
                            Main.getInstance().getSnowHash().put(shooter, shooter.getUniqueId());
                            e.setCancelled(true);
                            return;
                        }

                        ArrayList<UUID> ballID = new ArrayList<UUID>();
                        ballID.add(snowball.getUniqueId());
                            if (e.getDamager().getUniqueId().equals(ballID.contains(e.getDamager().getUniqueId()))) {
                                Location shooterLoc = shooter.getLocation();
                                Location hitterLoc = e.getEntity().getLocation();
                                e.setCancelled(true);
                                shooter.getWorld().playSound(shooter.getLocation(), Sound.ANVIL_LAND, 2, 1);
                                System.out.println(shooter.getLocation().distance(e.getEntity().getLocation()));
                                shooter.teleport(hitterLoc);
                                e.getEntity().teleport(shooterLoc);
                                ballID.remove(snowball.getUniqueId());


                                Main.getInstance().getSnowHash().put(shooter, shooter.getUniqueId());

                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {

                                    public void run() {
                                        Main.getInstance().getSnowHash().remove(shooter);
                                    }

                                }, 100L);


                            }
                        }


                    } catch(NullPointerException error){
                        return;
                    }
                }


        }
    }
    @EventHandler
    public void onSwitcherThrow(ProjectileLaunchEvent e) {
        if(e.getEntity() instanceof Snowball) {
            if (e.getEntity().getShooter() instanceof Player) {

                if(Main.getInstance().getSnowHash().values().contains(((Player) e.getEntity().getShooter()).getUniqueId())) {
                    ((Player) e.getEntity().getShooter()).sendMessage(Util.chat("&cYou are still on cooldown!"));
                    e.setCancelled(true);
                }
            }
        }
    }
}
