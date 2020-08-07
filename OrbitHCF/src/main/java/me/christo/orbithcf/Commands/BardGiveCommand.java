package me.christo.orbithcf.Commands;

import me.christo.orbithcf.Main;
import me.christo.orbithcf.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class BardGiveCommand implements CommandExecutor {


    private Main plugin;

    public BardGiveCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("bard").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;


        Player target = Bukkit.getPlayer(args[1]);

        if (args.length == 0) {
            p.sendMessage(Util.chat("&cInvalid Usage. /bard give {player} {amount}"));
            return true;
        }
        if (args.length == 1) {
            p.sendMessage(Util.chat("&cInvalid Usage. /bard give {player} {amount}"));
            return true;
        }
        if (args.length == 2) {
            p.sendMessage(Util.chat("&cInvalid Usage. /bard give {player} {amount}"));
            return true;
        }

        if (args.length == 3) {
            if (p.hasPermission("bard.give")) {
                if(target == null) {
                    p.sendMessage(Util.chat("&cPlayer not found."));
                    return true;
                }

                    int amount = Integer.parseInt(args[2]);

                    ItemStack bardItem = new ItemStack(Material.BLAZE_POWDER);
                    ItemMeta meta = bardItem.getItemMeta();
                    meta.setDisplayName(Util.chat(Main.getInstance().getConfig().getString("Bard.BardItemName")));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(Util.chat(Main.getInstance().getConfig().getString("Bard.BardItemLore")));
                    meta.setLore(lore);
                    bardItem.setAmount(amount);
                    bardItem.setItemMeta(meta);

                    target.getInventory().addItem(bardItem);
                    p.sendMessage(Util.chat(Main.getInstance().getConfig().getString("Bard.SuccessMessage").replace("{player}", target.getName())));

                    return false;
                }
            }
            return false;
        }

}
