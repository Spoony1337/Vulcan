package dev.astro.net.abilities;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.concurrent.TimeUnit;

public class FastPearl extends Ability implements Listener {

    private String name;
    private String lore;

    public FastPearl() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("AutoBow.Options.Cooldown")));
        this.name = CC.translate("&6&lFastPearl");
        this.lore = CC.translate("&cTemporaly Disabled");
    }

    public ItemStack getItem() {
        return new ItemBuilder(new ItemStack(Material.ENDER_PEARL))
                .setAmount(1).setName(CC.translate("&6&lFastPearl"))
                .setLore(CC.translate("&cTemporaly Disabled")).build();
    }
}