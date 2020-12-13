/**
 *   Copyright © 2020 | vironlab.eu | All Rights Reserved.
 *
 *      ___    _______                        ______         ______
 *      __ |  / /___(_)______________ _______ ___  / ______ ____  /_
 *      __ | / / __  / __  ___/_  __ \__  __ \__  /  _  __ `/__  __ \
 *      __ |/ /  _  /  _  /    / /_/ /_  / / /_  /___/ /_/ / _  /_/ /
 *      _____/   /_/   /_/     \____/ /_/ /_/ /_____/\__,_/  /_.___/
 *
 *    ____  _______     _______ _     ___  ____  __  __ _____ _   _ _____
 *   |  _ \| ____\ \   / / ____| |   / _ \|  _ \|  \/  | ____| \ | |_   _|
 *   | | | |  _|  \ \ / /|  _| | |  | | | | |_) | |\/| |  _| |  \| | | |
 *   | |_| | |___  \ V / | |___| |__| |_| |  __/| |  | | |___| |\  | | |
 *   |____/|_____|  \_/  |_____|_____\___/|_|   |_|  |_|_____|_| \_| |_|
 *
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   Contact:
 *
 *     Discordserver:   https://discord.gg/wvcX92VyEH
 *     Website:         https://vironlab.eu/
 *     Mail:            contact@vironlab.eu
 *
 */

package eu.vironlab.simpleitemlib;

import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SimpleItemBuilder {
    private ItemStack item;
    private ItemMeta meta;
    private int amount;
    private Consumer<InventoryClickEvent> clickHandler;
    private Consumer<PlayerDropItemEvent> dropHandler;
    private Consumer<PlayerInteractEvent> interactHandler;

    /**
     * Create a Clickable ItemStack
     *
     * @param material is the Material of the Item wich is required every times
     */
    public SimpleItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Create a Clickable ItemStack
     *
     * @param material is the Material of the Item wich is required every times
     * @param amount is the amount of the Item
     */
    public SimpleItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
        this.amount = amount;
    }

    /**
     * Set the Color of the Item if the Material is type of Leather armor
     *
     * @param color is the Color for the Armor
     * @return the current Builder instance
     */
    public SimpleItemBuilder setLeatherArmorColor(Color color) {
        if (meta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) meta).setColor(color);
        }
        return this;
    }

    /**
     * Add an ItemFlag to the Item
     *
     * @param flag is the Flag to add
     * @return the current Builder instance
     */
    public SimpleItemBuilder addItemFlag(ItemFlag flag) {
        this.meta.addItemFlags(flag);
        return this;
    }

    /**
     * Set the Handler wich is accepted when interacting with the Item
     *
     * @param interactHandler is the Handler to set
     * @return the current Builder instance
     */
    public SimpleItemBuilder setInteractHandler(Consumer<PlayerInteractEvent> interactHandler) {
        this.interactHandler = interactHandler;
        return this;
    }

    /**
     * Set the Handler wich is accepted when clicking with the Item
     *
     * @param clickHandler is the Handler to set
     * @return the current Builder instance
     */
    public SimpleItemBuilder setClickHandler(Consumer<InventoryClickEvent> clickHandler) {
        this.clickHandler = clickHandler;
        return this;
    }

    /**
     * Set the Handler wich is accepted when droping with the Item
     *
     * @param dropHandler is the Handler to set
     * @return the current Builder instance
     */
    public SimpleItemBuilder setDropHandler(Consumer<PlayerDropItemEvent> dropHandler) {
        this.dropHandler = dropHandler;
        return this;
    }

    /**
     * Set the Item Unbreakable
     *
     * @return the current Builder instance
     */
    public SimpleItemBuilder setUnbreakable() {
        this.meta.setUnbreakable(true);
        return this;
    }

    /**
     * Add an Enchantment to the ItemStack
     *
     * @param enchantment is the Enchantment to add
     * @param value is the strength of the enchantment
     * @return the current Builder instance
     */
    public SimpleItemBuilder addEnchantment(Enchantment enchantment, int value) {
        this.item.addEnchantment(enchantment, value);
        return this;
    }

    /**
     * Set the Name of the Item
     *
     * @param name is the DisplayName
     * @return the current Builder instance
     */
    public SimpleItemBuilder setDisplayName(String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    /**
     * Add a Lore as Array to the Item
     *
     * @param lore are the lines of lore to add
     * @return the current Builder instance
     */
    public SimpleItemBuilder addLore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }


    /**
     * Add a Lore as List to the Item
     *
     * @param lore are the lines of lore to add
     * @return the current Builder instance
     */
    public SimpleItemBuilder addLore(List<String> lore) {
        this.meta.setLore(lore);
        return this;
    }

    /**
     * Build the Item
     *
     * @return the builded Item
     */
    public SimpleItemStack build() {
        String key = RandomStringUtils.random(64);
        while (SimpleItemLib.getInstance().simpleItemStacks.containsKey(key)) {
            key = RandomStringUtils.random(64);
        }
        this.meta.getPersistentDataContainer().set(SimpleItemLib.getInstance().key, PersistentDataType.STRING, key);
        this.item.setItemMeta(this.meta);
        SimpleItemStack itemStack = new SimpleItemStack(item, clickHandler, dropHandler, interactHandler);
        SimpleItemLib.getInstance().simpleItemStacks.put(key, itemStack);
        return itemStack;
    }
}
