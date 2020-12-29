/**
 * Copyright © 2020 | vironlab.eu | All Rights Reserved.
 *
 * ___    _______                        ______         ______
 * __ |  / /___(_)______________ _______ ___  / ______ ____  /_
 * __ | / / __  / __  ___/_  __ \__  __ \__  /  _  __ `/__  __ \
 * __ |/ /  _  /  _  /    / /_/ /_  / / /_  /___/ /_/ / _  /_/ /
 * _____/   /_/   /_/     \____/ /_/ /_/ /_____/\__,_/  /_.___/
 *
 * ____  _______     _______ _     ___  ____  __  __ _____ _   _ _____
 * |  _ \| ____\ \   / / ____| |   / _ \|  _ \|  \/  | ____| \ | |_   _|
 * | | | |  _|  \ \ / /|  _| | |  | | | | |_) | |\/| |  _| |  \| | | |
 * | |_| | |___  \ V / | |___| |__| |_| |  __/| |  | | |___| |\  | | |
 * |____/|_____|  \_/  |_____|_____\___/|_|   |_|  |_|_____|_| \_| |_|
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 *
 * Contact:
 *
 * Discordserver:   https://discord.gg/wvcX92VyEH
 * Website:         https://vironlab.eu/
 * Mail:            contact@vironlab.eu
 *
 */
package eu.vironlab.simpleitemlib.item

import eu.vironlab.simpleitemlib.SimpleItemLib.Companion.instance
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.Material
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.ItemFlag
import org.bukkit.enchantments.Enchantment
import java.util.Arrays
import org.apache.commons.lang.RandomStringUtils
import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.inventory.Inventory
import org.bukkit.persistence.PersistentDataType
import java.util.function.Consumer

class SimpleItemBuilder {
    private var item: ItemStack
    private var meta: ItemMeta?
    private var amount = 0
    private var blockDrop = false
    private var blockInteract = false
    private var blockClick = false
    private var clickHandler: Consumer<InventoryClickEvent>? = null
    private var dropHandler: Consumer<PlayerDropItemEvent>? = null
    private var interactHandler: Consumer<PlayerInteractEvent>? = null

    /**
     * Create a Clickable ItemStack
     *
     * @param material is the Material of the Item wich is required every times
     */
    constructor(material: Material) {
        item = ItemStack(material)
        meta = item.itemMeta
    }

    /**
     * Create a Clickable ItemStack
     *
     * @param material is the Material of the Item wich is required every times
     * @param amount is the amount of the Item
     */
    constructor(material: Material, amount: Int) {
        item = ItemStack(material!!)
        meta = item.itemMeta
        this.amount = amount
    }

    /**
     * Set the Color of the Item if the Material is type of Leather armor
     *
     * @param color is the Color for the Armor
     * @return the current Builder instance
     */
    fun setLeatherArmorColor(color: Color?): SimpleItemBuilder {
        if (meta is LeatherArmorMeta) {
            (meta as LeatherArmorMeta).setColor(color)
        }
        return this
    }

    /**
     * Add an ItemFlag to the Item
     *
     * @param flag is the Flag to add
     * @return the current Builder instance
     */
    fun addItemFlag(flag: ItemFlag?): SimpleItemBuilder {
        meta!!.addItemFlags(flag)
        return this
    }

    /**
     * Set the Handler wich is accepted when interacting with the Item
     *
     * @param interactHandler is the Handler to set
     * @return the current Builder instance
     */
    fun setInteractHandler(interactHandler: Consumer<PlayerInteractEvent>?): SimpleItemBuilder {
        this.interactHandler = interactHandler
        return this
    }

    /**
     * Set the Handler wich is accepted when clicking with the Item
     *
     * @param clickHandler is the Handler to set
     * @return the current Builder instance
     */
    fun setClickHandler(clickHandler: Consumer<InventoryClickEvent>?): SimpleItemBuilder {
        this.clickHandler = clickHandler
        return this
    }

    /**
     * Set the Handler wich is accepted when droping with the Item
     *
     * @param dropHandler is the Handler to set
     * @return the current Builder instance
     */
    fun setDropHandler(dropHandler: Consumer<PlayerDropItemEvent>?): SimpleItemBuilder {
        this.dropHandler = dropHandler
        return this
    }

    /**
     * Set the Item Unbreakable
     *
     * @return the current Builder instance
     */
    fun setUnbreakable(): SimpleItemBuilder {
        meta!!.isUnbreakable = true
        return this
    }

    /**
     * Add an Enchantment to the ItemStack
     *
     * @param enchantment is the Enchantment to add
     * @param value is the strength of the enchantment
     * @return the current Builder instance
     */
    fun addEnchantment(enchantment: Enchantment?, value: Int): SimpleItemBuilder {
        item.addEnchantment(enchantment!!, value)
        return this
    }

    /**
     * Set the name of the Item to [name]
     */
    fun setDisplayName(name: String?): SimpleItemBuilder {
        meta!!.setDisplayName(name)
        return this
    }

    /**
     * Add the [lore] to the Item as Lore
     */
    fun addLore(vararg lore: String?): SimpleItemBuilder {
        meta!!.lore = Arrays.asList(*lore)
        return this
    }

    /**
     * Add the [lore] to the Item as Lore
     */
    fun addLore(lore: List<String?>?): SimpleItemBuilder {
        meta!!.lore = lore
        return this
    }

    /**
     * Set the cancel of the BlockDropEvent to [block]
     */
    fun blockDrop(block: Boolean): SimpleItemBuilder {
        this.blockDrop = blockDrop
        return this
    }

    /**
     * Set the cancel of the InventoryClickEvent to [block]
     */
    fun blockClick(block: Boolean): SimpleItemBuilder {
        this.blockClick = blockClick
        return this
    }

    /**
     * Set the cancel of the PlayerInteractEvent to [block]
     */
    fun blockInteract(block: Boolean): SimpleItemBuilder {
        this.blockInteract = blockInteract
        return this
    }

    /**
     * Build the SimpleItemStack
     */
    fun build(): SimpleItemStack {
        var key = RandomStringUtils.random(64)
        while (instance!!.simpleItemStacks.containsKey(key)) {
            key = RandomStringUtils.random(64)
        }
        meta!!.persistentDataContainer.set(instance!!.key, PersistentDataType.STRING, key)
        item.itemMeta = meta
        val itemStack = SimpleItemStack(item, clickHandler!!, dropHandler!!, interactHandler!!)
        instance!!.simpleItemStacks.put(key, itemStack)
        return itemStack
    }

    /**
     * Build the SimpleItemStack and set a custom [key] for the Item registration
     */
    fun build(key: String): SimpleItemStack {
        meta!!.persistentDataContainer.set(instance!!.key, PersistentDataType.STRING, key)
        item.itemMeta = meta
        val itemStack = SimpleItemStack(item, clickHandler!!, dropHandler!!, interactHandler!!)
        instance!!.simpleItemStacks.put(key, itemStack)
        return itemStack
    }

    companion object {
        /**
         * Create an ItemBuilder with the [material] and block all events
         */
        @JvmStatic
        fun createDefault(material: Material): SimpleItemBuilder {
            val itemBuilder: SimpleItemBuilder = SimpleItemBuilder(material)
            itemBuilder.blockDrop(true)
            itemBuilder.blockInteract(true)
            itemBuilder.blockClick(true)
            return itemBuilder
        }

        /**
         * Create an ItemBuilder with the [material] and the [amount] and block all events
         */
        @JvmStatic
        fun createDefault(material: Material, amount: Int): SimpleItemBuilder {
            val itemBuilder: SimpleItemBuilder = SimpleItemBuilder(material, amount)
            itemBuilder.blockDrop(true)
            itemBuilder.blockInteract(true)
            itemBuilder.blockClick(true)
            return itemBuilder
        }

    }

}