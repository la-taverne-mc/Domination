package me.lataverne.domination;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

public enum Items {
    FIREBALL_WAND("fireball_wand");

    private final String name;
    private final ItemStack item;

    private Items(String name) {
        this.name = name;

        ItemStack item;

        switch (name) {
            case "fireball_wand":
                item = createHealingWand();
                break;
            
            default:
                item = new ItemStack(Material.AIR);
                break;
        }

        this.item = item;
    }

    private ItemStack createHealingWand() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("§fBoule de feu");

        item.setItemMeta(itemMeta);
        return item;
    }

    public String getName() { return this.name; }
    public ItemStack getItem() { return this.item.clone(); }

    public Boolean compareTo(@NotNull ItemStack testItem) {
        testItem = testItem.clone();

        if (testItem.getItemMeta() instanceof LeatherArmorMeta) {
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) testItem.getItemMeta();
            itemMeta.setColor(Bukkit.getItemFactory().getDefaultLeatherColor());
            testItem.setItemMeta(itemMeta);
        }

        return this.item.getType() == testItem.getType() && this.item.hasItemMeta() == testItem.hasItemMeta() && (this.item.hasItemMeta() ? Bukkit.getItemFactory().equals(this.item.getItemMeta(), testItem.getItemMeta()) : true);
    
    }
}
