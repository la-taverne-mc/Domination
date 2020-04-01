package me.lataverne.domination.enums;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Items {
    FIREBALL_WAND("fireball_wand"),
    HEAL_WAND("healing_wand"),
    TELEPORT_WAND("teleport_wand"),
    WING_OUTFIELDER("wing_outfielder"),
    SWORD_OUTFIELDDER("sword_outfielder"),
    SHIELD_OUTFIELDER("shield_outfielder"),
    TROUSSERS_OUTFIELDER("troussers_outfielder"),
    ARC_ARCHER("arc_archer"),
    ARMOR_ARCHER("armor_archer"),
    FEET_ARCHER("feet_archer"),
    GLOWING_ARROW("glowing_arrow"),
    DAMAGE_ARROW("damage_arrow"),
    TNT_ARROW("tnt_arrow"),
    FIREWORK_ROCKET("firework_rocket");

    private final String name;
    private final ItemStack item;

    Items(String name) {
        this.name = name;

        ItemStack item;

        switch (name) {
            case "fireball_wand":
                item = createFireballWand();
                break;
            case "healing_wand":
                item = createHealingWand();
                break;
            case "wing_outfielder":
                item = createWingOutfielder();
                break;
            case "sword_outfielder":
                item = createSwordOutfielder();
                break;
            case "shield_outfielder":
                item = createShieldOutfielder();
                break;
            case "troussers_outfielder":
                item = createTroussersOutfielder();
                break;
            case "arc_archer":
                item = createArcArcher();
                break;
            case "armor_archer":
                item = createArmorArcher();
                break;
            case "feet_archer":
                item = createFeetArcher();
                break;
            case "glowing_arrow":
                item = createGlowingArrow();
                break;
            case "damage_arrow":
                item = createDamageArrow();
                break;
            case "tnt_arrow":
                item = createTntArrow();
                break;
            case "teleport_wand":
                item = createteleportWand();
                break;
            case "firework_rocket":
                item = createFirework();
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

        itemMeta.setDisplayName("§fPardon divin");

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createFireballWand() {
        ItemStack item = new ItemStack(Material.REDSTONE_TORCH);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName("§fJugement enflame");

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createteleportWand() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        ItemMeta = item.set
        item.setItemMeta(itemMeta2)

        itemMeta.setDisplayName("§fBâton de transfert ");

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createWingOutfielder() {
        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 20, true);
        itemMeta.setDisplayName("§fAiles de voltigeur");

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createSwordOutfielder() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 20, true);
        itemMeta.setDisplayName("§fBras droit du voltigeur ");

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createShieldOutfielder() {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.DURABILITY, 20, true);
        itemMeta.setDisplayName("§fBras gauche du Voltigeur");

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createTroussersOutfielder() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createArcArcher() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createFeetArcher() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.DIG_SPEED,2 , true);
        itemMeta.addEnchant(Enchantment.DURABILITY, 20, true);
        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createArmorArcher() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createGlowingArrow() {
        ItemStack item = new ItemStack(Material.SPECTRAL_ARROW);
        ItemMeta itemMeta = item.getItemMeta();

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createDamageArrow() {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = item.getItemMeta();

        item.setItemMeta(itemMeta);
        return item;
    }
    private ItemStack createTntArrow() {
        ItemStack item = new ItemStack(Material.TIPPED_ARROW);
        ItemMeta itemMeta = item.getItemMeta();
//todo tnt explosion
        item.setItemMeta(itemMeta);
        return item;
    }
    private  ItemStack createFirework(){
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET,64);
        ItemMeta itemMeta = item.getItemMeta();
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

        return this.item.getType() == testItem.getType() && this.item.hasItemMeta() == testItem.hasItemMeta() && (!this.item.hasItemMeta() || Bukkit.getItemFactory().equals(this.item.getItemMeta(), testItem.getItemMeta()));
    }

    public static Boolean contains(@NotNull ItemStack testItem) {
        for (Items item : Items.values()) {
            if (item.compareTo(testItem)) return true;
        }
        return false;
    }

    public static Boolean contains(@NotNull String testString) {
        for (Items item : Items.values()) {
            if (item.getName().equalsIgnoreCase(testString)) return true;
        }
        return false;
    }

    public static @Nullable ItemStack getItemNamed(@NotNull String itemName) {
        for (Items item : Items.values()) {
            if (item.getName().equalsIgnoreCase(itemName)) return item.getItem();
        }
        return null;
    }
}
