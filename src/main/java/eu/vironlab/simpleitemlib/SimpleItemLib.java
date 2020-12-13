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
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class SimpleItemLib extends JavaPlugin implements Listener {


    private static SimpleItemLib instance;
    public final String keyString = RandomStringUtils.random(64);
    public final NamespacedKey key = new NamespacedKey(this, keyString);
    public Map<String, SimpleItemStack> simpleItemStacks = new HashMap<>();

    public static SimpleItemLib getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (e.getItem().hasItemMeta()) {
                if (e.getItem().getItemMeta().getPersistentDataContainer().has(this.key, PersistentDataType.STRING)) {
                    String k = e.getItem().getItemMeta().getPersistentDataContainer().get(this.key, PersistentDataType.STRING);
                    if (this.simpleItemStacks.containsKey(k)) {
                        SimpleItemStack is = this.simpleItemStacks.get(k);
                        if (is.getInteractHandler() != null) {
                            is.getInteractHandler().accept(e);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void handleClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().getPersistentDataContainer().has(this.key, PersistentDataType.STRING)) {
                        String k = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(this.key, PersistentDataType.STRING);
                        if (this.simpleItemStacks.containsKey(k)) {
                            SimpleItemStack is = this.simpleItemStacks.get(k);
                            if (is.getClickHandler() != null) {
                                is.getClickHandler().accept(e);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void handleDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop() != null) {
            if (e.getItemDrop().getItemStack().hasItemMeta()) {
                if (e.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().has(this.key, PersistentDataType.STRING)) {
                    String k = e.getItemDrop().getItemStack().getItemMeta().getPersistentDataContainer().get(this.key, PersistentDataType.STRING);
                    if (this.simpleItemStacks.containsKey(k)) {
                        SimpleItemStack is = this.simpleItemStacks.get(k);
                        if (is.getDropHandler() != null) {
                            is.getDropHandler().accept(e);
                        }
                    }
                }
            }
        }
    }
}
