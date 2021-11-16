package shelby.gridworld.testers;

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

import info.gridworld.actor.ActorWorld;	
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.critters.CellCritter;
import info.gridworld.critters.HoarderCritter;
import info.gridworld.critters.ZoolandCritter;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ZoolanderRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();

		world.add(new Location(1, 2), new Bug());
		world.add(new Location(2, 2), new ZoolandCritter());
		world.add(new Location(2, 1), new Bug());
		world.add(new Location(2, 3), new Bug());

		world.show();
	}

}
