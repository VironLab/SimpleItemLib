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

import eu.vironlab.simpleitemlib.gui.GuiEntry
import eu.vironlab.simpleitemlib.gui.GuiManager
import eu.vironlab.simpleitemlib.gui.SimpleGUI
import eu.vironlab.simpleitemlib.gui.factory.SimpleGUIFactory
import eu.vironlab.simpleitemlib.item.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class SimpleItemLib : JavaPlugin() {
    /**
     * INFORMATION IF YOU WANT TO SET A NEW NAMESPACEDKEY
     *
     * It must be ensured that the key is always set at the start of the plugin for items that are to be used permanently.
     * Otherwise, previously created items will no longer work.
     * It is not recommended to use this method if you are not familiar with the topic.
     */
    var key = NamespacedKey(this, "FUST4H61ML8Qx0gP53CAFQX97YE2BIaK")
    var simpleItemStacks: MutableMap<String?, SimpleItemStack> = mutableMapOf()
    val guiManager: GuiManager = GuiManager()

    override fun onEnable() {
        instance = this
        this.guiManager.init(this)
        Bukkit.getPluginManager().registerEvents(InteractListener(this), this)
        Bukkit.getPluginManager().registerEvents(ClickListener(this), this)
        Bukkit.getPluginManager().registerEvents(DropListener(this), this)
    }

    companion object {
        @JvmStatic
        var instance: SimpleItemLib? = null
            private set
    }
}