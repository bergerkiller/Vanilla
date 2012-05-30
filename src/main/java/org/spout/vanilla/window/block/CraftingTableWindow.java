/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * Vanilla is licensed under the SpoutDev License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.window.block;

import org.spout.vanilla.controller.living.player.VanillaPlayer;
import org.spout.vanilla.inventory.block.CraftingTableInventory;
import org.spout.vanilla.window.CraftingWindow;

public class CraftingTableWindow extends CraftingWindow {
	private static final int SLOTS[] = {37, 38, 39, 40, 41, 42, 43, 44, 45, 28, 29, 30, 31, 32, 33, 34, 35, 36, 19, 20, 21, 22, 23, 24, 25, 26, 27, 10, 11, 12, 13, 14, 15, 16, 17, 18, 7, 8, 9, 4, 5, 6, 1, 2, 3, 0};
	
	public CraftingTableWindow(VanillaPlayer owner, CraftingTableInventory craftingInventory) {
		super(1, "Crafting", owner, craftingInventory);
		this.setInventory(owner.getInventory().getItems(), craftingInventory);
		this.setSlotConversionArray(SLOTS);
	}
	
	@Override
	public boolean onClick(int clickedSlot, boolean rightClick, boolean shift) {
		super.onClick(clickedSlot, rightClick, shift);
		if (clickedSlot == 43) {
			if (itemOnCursor != null) {
				return false;
			} else {
				setItemOnCursor(inventory.getItem(clickedSlot));
				return true;
			}
		}
		return true;
	}
}