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

package eu.vironlab.simpleitemlib.gui.factory

import eu.vironlab.simpleitemlib.SimpleItemLib
import eu.vironlab.simpleitemlib.gui.GuiEntry
import eu.vironlab.simpleitemlib.gui.SimpleGUI
import org.apache.commons.lang.RandomStringUtils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.function.Consumer

class SimpleGUIFactory(val title: String, val rows: Int) : Factory<SimpleGUI> {
    private var openHandler: Consumer<Player>? = null
    private var closeHandler: Consumer<Player>? = null
    private val entries: MutableMap<Int, GuiEntry> = mutableMapOf()

    fun addEntry(slot: Int, item: GuiEntry): SimpleGUIFactory {
        this.entries.put(slot, item)
        return this;
    }

    fun setOpenHandler(handler: Consumer<Player>?) {
        this.openHandler = handler;
    }

    fun setCloseHandler(handler: Consumer<Player>?) {
        this.closeHandler = handler
    }

    override fun build(register: Boolean): SimpleGUI {
        var key: String = RandomStringUtils.random(64, true, false)
        while (SimpleItemLib.instance!!.guiManager.guis.containsKey(key)) {
            key = RandomStringUtils.random(64, true, false)
        }
        val gui: SimpleGUI = SimpleGUIImpl(
            title,
            key,
            this.openHandler,
            this.closeHandler,
            rows,
            entries
        )
        if (register) {
            SimpleItemLib.instance!!.guiManager.register(gui)
        }
        return gui
    }


}

internal class SimpleGUIImpl(
    private val title: String,
    private val key: String,
    private var openHandler: Consumer<Player>?,
    private var closeHandler: Consumer<Player>?,
    private val rows: Int,
    private val entries: MutableMap<Int, GuiEntry>
) : SimpleGUI {
    override fun getTitle(): String {
        return this.title
    }

    override fun getKey(): String {
        return this.key
    }

    override fun open(player: Player) {
        val inv: Inventory = Bukkit.createInventory(null, rows)
        this.entries.forEach {
            if (it.value.permission != null) {
                if (!player.hasPermission(it.value.permission!!)) {
                    return@forEach
                }
            }
            inv.setItem(it.key, it.value.item.item)
        }
    }

    override fun getOpenHandler(): Consumer<Player>? {
        return this.openHandler
    }

    override fun setOpenHandler(handler: Consumer<Player>?) {
        this.openHandler = handler
    }

    override fun getCloseHandler(): Consumer<Player>? {
        return this.closeHandler
    }

    override fun setCloseHandler(handler: Consumer<Player>?) {
        this.closeHandler = closeHandler
    }

    override fun getGuiPosition(): Int {
        return 1
    }

    override fun getRows(): Int {
        return rows
    }

    override fun getEntries(): MutableMap<Int, GuiEntry> {
        return this.entries
    }

    override fun setEntry(slot: Int, entry: GuiEntry) {
        if (this.entries.containsKey(slot)) {
            this.entries.remove(slot)
        }
        this.entries.put(slot, entry)
    }

    override fun removeEntry(slot: Int) {
        this.entries.remove(slot)
    }
}