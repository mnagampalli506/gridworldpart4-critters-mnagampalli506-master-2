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
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class HoarderCritter extends Critter

{
	private Location home;
	private Actor hoarded;
	private boolean hoardedPicked;
	private Location originalhoardedlocation;
	private ArrayList<Actor> noRepeatHoard;

	public HoarderCritter(Location home) {
		this.home = home;
		noRepeatHoard = new ArrayList<Actor>();
		hoardedPicked = false;

	}

	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Integer> noRepeatIndices;

		// Creat a subarray of Indices of the Actor list that's not hoarded
		noRepeatIndices = new ArrayList<Integer>();
		boolean isRepeat = false;
		for (int j = 0; j < actors.size(); j++) {
			Actor a = actors.get(j);
			//
			isRepeat = false;
			for (int i = 0; i < noRepeatHoard.size(); i++) {
				if (a == noRepeatHoard.get(i))
					isRepeat = true;
			}
			if (isRepeat == false && !(a instanceof Rock))
				noRepeatIndices.add(j);

		}

		// Now pick a random index from non hoarded list
		if (noRepeatIndices.size() > 0) {
			int r = (int) (Math.random() * noRepeatIndices.size());

			hoarded = actors.get(noRepeatIndices.get(r));

			originalhoardedlocation = hoarded.getLocation();

			hoardedPicked = true;

			hoarded.removeSelfFromGrid();

		}

	}

	public Location selectMoveLocation(ArrayList<Location> locs) {
		boolean blocked = false;

		// If we are not hoarding, fallback to the default behavior of critter
		if (hoardedPicked == false) {

			return super.selectMoveLocation(getMoveLocations());
		}

		// If adjacent locations is empty, fallback to default behavior
		if (locs.size() == 0) {
			System.out.println("Hoarded but no adjacent locations valid");
			hoardedPicked = false;
			noRepeatHoard.add(hoarded);
			hoarded.putSelfInGrid(getGrid(), originalhoardedlocation);
			return super.selectMoveLocation(getMoveLocations());
		}

		// Hoarding and at Home location
		if (home.getCol() == this.getLocation().getCol() && home.getRow() == this.getLocation().getRow()) {

			hoardedPicked = false;
			noRepeatHoard.add(hoarded);
			Location location = this.getLocation();
			this.moveTo(super.selectMoveLocation(getMoveLocations()));
			hoarded.putSelfInGrid(getGrid(), location);
			return super.selectMoveLocation(getMoveLocations());

		}

		// Return adjacent free location if in same row/column as HOME

		if (this.getLocation().getRow() == home.getRow()) {

			for (int i = 0; i < locs.size(); i++) {
				if (this.getLocation().getDirectionToward(locs.get(i)) == this.getLocation().getDirectionToward(home)
						&& locs.get(i).compareTo(this.getLocation()) > 0) {

					return locs.get(i);
				}
			}
			blocked = true;
		}

		if (!blocked && this.getLocation().getCol() == home.getCol()) {
			for (int i = 0; i < locs.size(); i++) {
				if (this.getLocation().getDirectionToward(locs.get(i)) == this.getLocation().getDirectionToward(home)) {

					return locs.get(i);
				}
			}
			blocked = true;
		}

		if (!blocked) {
			ArrayList<Location> newLocs = new ArrayList<Location>();
			if (this.getLocation().getRow() > home.getRow()) {
				newLocs.add(new Location(this.getLocation().getRow() - 1, this.getLocation().getCol()));
			}
			if (this.getLocation().getRow() < home.getRow()) {
				newLocs.add(new Location(this.getLocation().getRow() + 1, this.getLocation().getCol()));
			}
			if (this.getLocation().getCol() > home.getCol()) {
				newLocs.add(new Location(this.getLocation().getRow(), this.getLocation().getCol() - 1));
			}
			if (this.getLocation().getCol() < home.getCol()) {
				newLocs.add(new Location(this.getLocation().getRow(), this.getLocation().getCol() + 1));
			}

			if (this.getLocation().getRow() > home.getRow() && this.getLocation().getCol() > home.getCol()) {
				newLocs.add(new Location(this.getLocation().getRow() - 1, this.getLocation().getCol() - 1));
			}
			if (this.getLocation().getRow() < home.getRow() && this.getLocation().getCol() < home.getCol()) {
				newLocs.add(new Location(this.getLocation().getRow() + 1, this.getLocation().getCol() - 1));

			}
			if (this.getLocation().getRow() > home.getRow() && this.getLocation().getCol() < home.getCol()) {
				newLocs.add(new Location(this.getLocation().getRow() - 1, this.getLocation().getCol() + 1));
			}
			if (this.getLocation().getRow() < home.getRow() && this.getLocation().getCol() > home.getCol()) {
				newLocs.add(new Location(this.getLocation().getRow() + 1, this.getLocation().getCol() - 1));

			}

			return newLocs.get((int) Math.random() * newLocs.size());

		}

		// At this point, there is no place to go anymore - treat this as blocked

		hoardedPicked = false;
		noRepeatHoard.add(hoarded);
		Location location = this.getLocation();
		this.moveTo(super.selectMoveLocation(getMoveLocations()));
		hoarded.putSelfInGrid(getGrid(), location);
		return super.selectMoveLocation(getMoveLocations());

	}
}
