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

package eu.vironlab.simpleitemlib

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType


class InteractListener(val itemLib: SimpleItemLib) : Listener {
    @EventHandler
    fun handleInteract(e: PlayerInteractEvent) {
        if (e.item != null) {
            if (e.item!!.hasItemMeta()) {
                if (e.item!!.itemMeta!!
                        .persistentDataContainer.has(itemLib.key, PersistentDataType.STRING)
                ) {
                    val k = e.item!!.itemMeta!!
                        .persistentDataContainer.get(itemLib.key, PersistentDataType.STRING)
                    if (itemLib.simpleItemStacks.containsKey(k)) {
                        val item = itemLib.simpleItemStacks[k]
                        if (item!!.interactHandler != null) {
                            item.interactHandler!!.accept(e)
                        }
                    }
                }
            }
        }
    }
}

class ClickListener(val itemLib: SimpleItemLib) : Listener {
    @EventHandler
    fun handleClick(e: InventoryClickEvent) {
        if (e.whoClicked is Player) {
            if (e.currentItem != null) {
                if (e.currentItem!!.hasItemMeta()) {
                    if (e.currentItem!!.itemMeta!!
                            .persistentDataContainer.has(itemLib.key, PersistentDataType.STRING)
                    ) {
                        val k = e.currentItem!!.itemMeta!!.persistentDataContainer.get(itemLib.key, PersistentDataType.STRING)
                        if (itemLib.simpleItemStacks.containsKey(k)) {
                            val item = itemLib.simpleItemStacks[k]
                            if (item!!.clickHandler != null) {
                                item.clickHandler!!.accept(e)
                            }
                        }
                    }
                }
            }
        }
    }
}

class DropListener(val itemLib: SimpleItemLib) : Listener {

    @EventHandler
    fun handleDrop(e: PlayerDropItemEvent) {
        if (e.itemDrop != null) {
            if (e.itemDrop.itemStack.hasItemMeta()) {
                if (e.itemDrop.itemStack.itemMeta!!
                        .persistentDataContainer.has(itemLib.key, PersistentDataType.STRING)
                ) {
                    val k = e.itemDrop.itemStack.itemMeta!!.persistentDataContainer.get(itemLib.key, PersistentDataType.STRING)
                    if (itemLib.simpleItemStacks.containsKey(k)) {
                        val item = itemLib.simpleItemStacks[k]
                        if (item!!.dropHandler != null) {
                            item.dropHandler!!.accept(e)
                        }
                    }
                }
            }
        }
    }

}