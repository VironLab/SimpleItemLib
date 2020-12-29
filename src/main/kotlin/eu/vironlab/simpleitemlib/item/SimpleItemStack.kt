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

import org.bukkit.inventory.ItemStack
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.util.function.Consumer

class SimpleItemStack {
    var item: ItemStack
        private set
    var clickHandler: Consumer<InventoryClickEvent>? = null
        private set
    var dropHandler: Consumer<PlayerDropItemEvent>? = null
        private set
    var interactHandler: Consumer<PlayerInteractEvent>? = null
        private set

    constructor(item: ItemStack) {
        this.item = item
    }

    constructor(
        item: ItemStack,
        clickHandler: Consumer<InventoryClickEvent>?,
        dropHandler: Consumer<PlayerDropItemEvent>?,
        interactHandler: Consumer<PlayerInteractEvent>?
    ) {
        this.item = item
        this.clickHandler = clickHandler
        this.dropHandler = dropHandler
        this.interactHandler = interactHandler
    }

    fun setItem(item: ItemStack): SimpleItemStack {
        this.item = item
        return this
    }

    fun setClickHandler(clickHandler: Consumer<InventoryClickEvent>?): SimpleItemStack {
        this.clickHandler = clickHandler
        return this
    }

    fun setDropHandler(dropHandler: Consumer<PlayerDropItemEvent>?): SimpleItemStack {
        this.dropHandler = dropHandler
        return this
    }

    fun setInteractHandler(interactHandler: Consumer<PlayerInteractEvent>?): SimpleItemStack {
        this.interactHandler = interactHandler
        return this
    }
}