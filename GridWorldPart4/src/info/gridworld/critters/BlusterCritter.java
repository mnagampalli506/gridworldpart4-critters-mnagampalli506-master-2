package info.gridworld.critters;
/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter {
	private int courage = 0;
	private static final double DARKENING_FACTOR = 5;

	public BlusterCritter(int courage) {
		super();
		this.courage = courage;
	}

	public ArrayList<Actor> getActors()

	{
		ArrayList<Actor> actor = new ArrayList<Actor>();
		Location location = getLocation();

		for (int row = location.getRow() - 2; row <= location.getRow() + 2; row++) {
			for (int column = location.getCol() - 2; column <= location.getCol() + 2; column++) {
				Location loc = new Location(row, column);
				if (getGrid().isValid(loc)) {
					Actor neighbor = getGrid().get(loc);
					if (neighbor != null && neighbor != this) {
						actor.add(neighbor);
						

					}
				}

			}
		}
		return actor;
	}

	public void processActors(ArrayList<Actor> actors) {
		int n = 0;
		for (Actor neighbor : actors)
			if (neighbor instanceof Critter) {
				n++;
			}

		if (courage < n) {
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

			setColor(new Color(red, green, blue));
			return;
		}
		if (courage >= n) {
			Color c = getColor();
			int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));

			setColor(new Color(red, green, blue));
			return;
		}
		int r = (int) (Math.random() * n);

		Actor other = actors.get(r);
		setColor(other.getColor());
	}

}
