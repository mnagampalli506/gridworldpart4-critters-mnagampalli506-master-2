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
 * @author Cay Horstmann
 */

package info.gridworld.critters;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class CellCritter extends Critter

{
	private boolean critterDead = false;

	public void processActors(ArrayList<Actor> actors) {
		int n=0;
		for (Actor a : actors)
        {
            if ((a instanceof CellCritter ))
                n++;
        }
		
		if (n == 0 || n >= 5) {
			critterDead = true;
		} else if (n < 4) {
			ArrayList<Location> goodSpots = this.getGrid().getEmptyAdjacentLocations(this.getLocation());
			if (goodSpots.size() > 0) {
				int r = (int) (Math.random() * goodSpots.size());
				CellCritter duplicate = new CellCritter();
				duplicate.putSelfInGrid(this.getGrid(), goodSpots.get(r));

			}
		}

	}

	public Location selectMoveLocation(ArrayList<Location> locs) {
		if (critterDead == true) {
			return null;
		} else
			return this.getLocation();
	}
}
