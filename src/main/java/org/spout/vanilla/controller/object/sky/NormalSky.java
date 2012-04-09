/*
 * This file is part of Vanilla (http://www.spout.org/).
 *
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
package org.spout.vanilla.controller.object.sky;

import java.util.Random;
import java.util.Set;

import org.spout.api.Spout;
import org.spout.api.entity.type.ControllerType;
import org.spout.api.entity.type.EmptyConstructorControllerType;
import org.spout.api.geo.World;
import org.spout.api.player.Player;

import org.spout.vanilla.controller.object.VanillaSky;
import org.spout.vanilla.event.world.WeatherChangeEvent;
import org.spout.vanilla.protocol.event.TimeUpdateProtocolEvent;
import org.spout.vanilla.protocol.event.WeatherChangeProtocolEvent;
import org.spout.vanilla.world.Weather;

public class NormalSky extends VanillaSky {
	public static final ControllerType TYPE = new EmptyConstructorControllerType(NormalSky.class, "Normal Sky");

	public NormalSky() {
		super(TYPE, true);
	}

	@Override
	public void onAttached() {
	}

	@Override
	public void updateTime(long time) {
		Set<Player> players = getParent().getWorld().getPlayers();
		TimeUpdateProtocolEvent event = new TimeUpdateProtocolEvent(time);
		for (Player player : players) {
			player.getNetworkSynchronizer().callProtocolEvent(event);
		}
	}

	@Override
	public void updateWeather(Weather oldWeather, Weather newWeather) {
		WeatherChangeEvent event = Spout.getEventManager().callEvent(new WeatherChangeEvent(this, oldWeather, newWeather));
		if (event.isCancelled()) {
			return;
		}

		WeatherChangeProtocolEvent protocolEvent = new WeatherChangeProtocolEvent(newWeather);
		for (Player player : getParent().getWorld().getPlayers()) {
			if (player.getNetworkSynchronizer() != null) {
				player.getNetworkSynchronizer().callProtocolEvent(protocolEvent);
			}
		}
	}
}
