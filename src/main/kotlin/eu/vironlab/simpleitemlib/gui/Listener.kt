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

package eu.vironlab.simpleitemlib.gui

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent

class GuiInventoryCloseListener(val guiManager: GuiManager): Listener {

    @EventHandler
    fun handleInventoryClose(event: InventoryCloseEvent) {
        if (event.player is Player) {
            if (event.inventory.holder is SimpleInventoryHolder) {
                val key: String = (event.inventory.holder as SimpleInventoryHolder).identifier
                if (this.guiManager.guis.containsKey(key)) {
                    val gui: GUI = this.guiManager.guis.get(key)!!
                    if (gui.getCloseHandler() != null) {
                        gui.getCloseHandler()!!.accept(event.player as Player)
                    }
                }
            }
        }
    }

}