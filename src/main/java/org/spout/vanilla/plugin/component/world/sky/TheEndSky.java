/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * Vanilla is licensed under the Spout License Version 1.
 *
 * Vanilla is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Vanilla is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.vanilla.plugin.component.world.sky;

import org.spout.api.entity.Player;

import org.spout.vanilla.plugin.component.world.VanillaSky;
import org.spout.vanilla.plugin.data.Weather;

public class TheEndSky extends VanillaSky {
	public TheEndSky() {
		super();
		setHasWeather(false);
	}

	@Override
	public void onAttached() {
		super.onAttached();
		getWorld().setSkyLight(MIN_SKY_LIGHT);
	}

	@Override
	public void updateTime(long time) {
	}

	@Override
	public void updateWeather(Weather oldWeather, Weather newWeather) {
	}

	@Override
	public void updatePlayer(Player player) {
	}
}
