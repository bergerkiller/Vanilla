/*
 * This file is part of Vanilla.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.spout.vanilla.world.generator.nether.structure.fortress;

import java.util.ArrayList;
import java.util.List;

import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.world.generator.structure.PieceCuboidBuilder;
import org.spout.vanilla.world.generator.structure.SimpleBlockMaterialPicker;
import org.spout.vanilla.world.generator.structure.Structure;
import org.spout.vanilla.world.generator.structure.StructurePiece;
import org.spout.vanilla.world.generator.structure.WeightedNextStructurePiece;

public class FortressCorridor extends WeightedNextStructurePiece {
	private static final WeightedNextPieceCache DEFAULT_NEXT = new WeightedNextPieceCache().
			add(FortressBlazeBalcony.class, 1).
			add(FortressNetherWartStairs.class, 1).
			add(FortressStaircase.class, 3).
			add(FortressRoom.class, 3).
			add(FortressStairRoom.class, 4).
			add(FortressBalconyIntersection.class, 4).
			add(FortressGateIntersection.class, 4).
			add(FortressTurn.class, 7).
			add(FortressIntersection.class, 7).
			add(FortressCorridor.class, 12);
	private boolean startOfFortress = false;

	public FortressCorridor(Structure parent) {
		super(parent, DEFAULT_NEXT);
	}

	@Override
	public boolean canPlace() {
		return true;
	}

	@Override
	public void place() {
		// Building objects
		final PieceCuboidBuilder box = new PieceCuboidBuilder(this);
		final SimpleBlockMaterialPicker picker = new SimpleBlockMaterialPicker();
		box.setPicker(picker);
		// Floor
		picker.setOuterInnerMaterials(VanillaMaterials.NETHER_BRICK, VanillaMaterials.NETHER_BRICK);
		box.setMinMax(0, 0, 0, 4, 1, 4).fill();
		// Interior space
		picker.setOuterInnerMaterials(VanillaMaterials.AIR, VanillaMaterials.AIR);
		box.offsetMinMax(0, 2, 0, 0, 4, 0).fill();
		// Right and left walls
		picker.setOuterInnerMaterials(VanillaMaterials.NETHER_BRICK, VanillaMaterials.NETHER_BRICK);
		box.offsetMinMax(0, 0, 0, -4, 0, 0).fill();
		box.offsetMinMax(4, 0, 0, 4, 0, 0).fill();
		// Windows for both walls
		picker.setOuterInnerMaterials(VanillaMaterials.NETHER_BRICK_FENCE, VanillaMaterials.NETHER_BRICK_FENCE);
		box.setMinMax(0, 3, 1, 0, 4, 1).fill();
		box.offsetMinMax(0, 0, 2, 0, 0, 2).fill();
		box.setMinMax(4, 3, 1, 4, 4, 1).fill();
		box.offsetMinMax(0, 0, 2, 0, 0, 2).fill();
		// Roof
		picker.setOuterInnerMaterials(VanillaMaterials.NETHER_BRICK, VanillaMaterials.NETHER_BRICK);
		box.setMinMax(0, 6, 0, 4, 6, 4).fill();
		// Fill down to the ground
		for (int xx = 0; xx <= 4; xx++) {
			for (int zz = 0; zz <= 4; zz++) {
				fillDownwards(xx, -1, zz, 50, VanillaMaterials.NETHER_BRICK);
			}
		}
	}

	@Override
	public void randomize() {
	}

	@Override
	public List<StructurePiece> getNextPieces() {
		final List<StructurePiece> pieces = new ArrayList<StructurePiece>();
		if (startOfFortress) {
			final StructurePiece piece = new FortressEnd(parent);
			piece.setPosition(position.add(rotate(4, 0, -1)));
			piece.setRotation(rotation.rotate(180, 0, 1, 0));
			piece.randomize();
			pieces.add(piece);
		}
		final StructurePiece piece = getNextPiece();
		piece.setPosition(position.add(rotate(0, 0, 5)));
		piece.setRotation(rotation);
		piece.randomize();
		pieces.add(piece);
		return pieces;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(transform(0, 0, 0), transform(4, 6, 4));
	}

	public boolean isStartOfFortress() {
		return startOfFortress;
	}

	public void setStartOfFortress(boolean startOfFortress) {
		this.startOfFortress = startOfFortress;
	}
}
