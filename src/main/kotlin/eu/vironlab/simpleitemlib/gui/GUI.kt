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

import eu.vironlab.simpleitemlib.item.SimpleItemStack
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer


interface GUI : Openable {
    fun getTitle(): String

    fun getKey(): String

}

interface MultiPageGUI : GUI, PageSwitchable {
    fun getPages(): Map<Int, Page>
    fun getPage(index: Int): Page?
    fun setPage(index: Int, page: Page)
}

interface SimpleGUI : GUI, Page

interface Page {

    fun getGuiPosition(): Int

    fun getRows(): Int

    fun getEntries(): MutableMap<Int, GuiEntry>

    fun setEntry(slot: Int, entry: GuiEntry)

    fun removeEntry(slot: Int)
}

interface PageSwitchable {
    fun getPageSwitchHandler(): PageSwitchHandler?

    fun setPageSwitchHandler(handler: PageSwitchHandler?)

    fun getPageSwitchButtons(): Pair<ItemStack, ItemStack>

}

interface PageSwitchHandler {
    fun handle(player: Player, previousPage: Page, previousPageIndex: Int, page: Page, pageIndex: Int)
}

class GuiEntry(val item: SimpleItemStack, val permission: String? = null) {}

interface Openable {

    fun open(player: Player)

    fun open(players: MutableCollection<out Player>) {
        players.forEach {
            open(it)
        }
    }

    fun open() {
        open(Bukkit.getOnlinePlayers())
    }

    fun open(permission: String) {
        open(Bukkit.getOnlinePlayers(), permission)
    }

    fun open(players: MutableCollection<out Player>, permission: String) {
        players.forEach {
            if (it.hasPermission(permission)) {
                open(it)
            }
        }
    }

    fun getOpenHandler(): Consumer<Player>?

    fun setOpenHandler(handler: Consumer<Player>?)

    fun getCloseHandler(): Consumer<Player>?

    fun setCloseHandler(handler: Consumer<Player>?)

}