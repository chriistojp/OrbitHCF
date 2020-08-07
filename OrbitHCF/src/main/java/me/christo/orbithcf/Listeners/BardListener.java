package me.christo.orbithcf.Listeners;

import me.christo.orbithcf.Main;
import me.christo.orbithcf.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class BardListener implements Listener {


    @EventHandler
    public void onBard(PlayerItemHeldEvent e) {

        int teir = 0;
        ItemStack item = null;
        Player p = e.getPlayer();
        int itemSlot = e.getNewSlot();
        item = p.getInventory().getItem(itemSlot);


        if (item == null) {
            for (PotionEffect effect : p.getActivePotionEffects()) {
                p.removePotionEffect(effect.getType());
            }
        }
        if (item != null) {
            try {
                if (item.getItemMeta().getDisplayName().equals(Util.chat(Main.getInstance().getConfig().getString("Bard.BardItemName")))) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Main.getInstance().getConfig().getInt("Bard.StrengthDuration"), Main.getInstance().getConfig().getInt("Bard.StrengthAmplifer")));
                } else {
                    return;
                }
            } catch (NullPointerException exception) {
            }
        }
    }
}
