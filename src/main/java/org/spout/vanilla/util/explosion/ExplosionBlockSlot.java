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
package org.spout.vanilla.util.explosion;

import org.spout.api.material.BlockMaterial;
import org.spout.api.math.Vector3;
import org.spout.vanilla.material.VanillaMaterials;

/**
 * This is a permanent block slot whose information is loaded from a world dynamically
 * It is used to load block information all at once
 */
public class ExplosionBlockSlot {
	public ExplosionBlockSlot(final Vector3 pos) {
		this.pos = pos;
	}
	public final Vector3 pos;
	public boolean isSet = false; //whether the information has been loaded
	public boolean destroy = false; //whether the block got destroyed
	public BlockMaterial material = VanillaMaterials.AIR; //the mat loaded from the world
	public float damageFactor;
	public int realx, realy, realz;
}