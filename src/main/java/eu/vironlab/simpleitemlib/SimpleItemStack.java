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

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class SimpleItemStack {
    private ItemStack item;
    private Consumer<InventoryClickEvent> clickHandler;
    private Consumer<PlayerDropItemEvent> dropHandler;
    private Consumer<PlayerInteractEvent> interactHandler;

    public SimpleItemStack(ItemStack item) {
        this.item = item;
    }

    public SimpleItemStack(ItemStack item, Consumer<InventoryClickEvent> clickHandler, Consumer<PlayerDropItemEvent> dropHandler, Consumer<PlayerInteractEvent> interactHandler) {
        this.item = item;
        this.clickHandler = clickHandler;
        this.dropHandler = dropHandler;
        this.interactHandler = interactHandler;
    }

    public ItemStack getItem() {
        return item;
    }

    public SimpleItemStack setItem(ItemStack item) {
        this.item = item;
        return this;
    }

    public Consumer<InventoryClickEvent> getClickHandler() {
        return clickHandler;
    }

    public SimpleItemStack setClickHandler(Consumer<InventoryClickEvent> clickHandler) {
        this.clickHandler = clickHandler;
        return this;
    }

    public Consumer<PlayerDropItemEvent> getDropHandler() {
        return dropHandler;
    }

    public SimpleItemStack setDropHandler(Consumer<PlayerDropItemEvent> dropHandler) {
        this.dropHandler = dropHandler;
        return this;
    }

    public Consumer<PlayerInteractEvent> getInteractHandler() {
        return interactHandler;
    }

    public SimpleItemStack setInteractHandler(Consumer<PlayerInteractEvent> interactHandler) {
        this.interactHandler = interactHandler;
        return this;
    }
}
