package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;

// For now, assume that FlagByFlag game world size is fixed and
// covers 1000 (width) x 1000 (height) area.
// The origin of the “world” (location (0,0)) is the lower left hand corner.
public class GameWorld
{
	private int clock;
	private int lives;
	private ArrayList<GameObject> gameObjectList;

	/**
	 * Class constructor.
	 */
	public GameWorld()
	{
		// Instantiate variables
		gameObjectList = new ArrayList<>();
		this.clock = 0;
		this.lives = 3;
	}
	
	public void init()
	{
		if (lives == 0)
		{
			// No more lives, game is lost
			System.out.println("Game over, you failed!");
			System.exit(0);
		}
		
		// Start with empty gameObjectList
		gameObjectList.clear();
		
		// Add 1 Ant at index 0 of gameObjectList
		Point antStart = new Point(200, 200);
		gameObjectList.add(new Ant(antStart));
		
		// Add 4-9 Flags starting at index 1 of gameObjectList,
		// first flag starts at the location of the Ant
		gameObjectList.add(new Flag(antStart));
		gameObjectList.add(new Flag(new Point(200, 800)));
		gameObjectList.add(new Flag(new Point(700, 800)));
		gameObjectList.add(new Flag(new Point(900, 400)));
				
		// Add at least 2 Spiders
		int totalSpiders = 2;
		for (int i=0; i<totalSpiders; i++) gameObjectList.add(new Spider());

		// Add at least 2 FoodStations
		int totalFoodStations = 2;
		for (int i=0; i<totalFoodStations; i++) gameObjectList.add(new FoodStation());
	}
	
	public void accelerate()
	{
		// Increase the Ant's speed by 5
		((Ant)gameObjectList.get(0)).changeSpeed(5);
		System.out.println("+5 speed (accelerated)");
	}

	public void brake()
	{
		// Deccrease the Ant's speed by 5
		((Ant)gameObjectList.get(0)).changeSpeed(-5);
		System.out.println("-5 speed (applied brakes)");
	}

	public void turnLeft()
	{
		// Adjust the Ant's heading by -5 degrees
		((Ant)gameObjectList.get(0)).changeHeading(-5);
		System.out.println("-5 heading (turned left)");
	}

	public void turnRight()
	{
		// Adjust the Ant's heading by +5 degrees
		((Ant)gameObjectList.get(0)).changeHeading(5);
		System.out.println("+5 heading (turned right)");
	}

	public void hitFlag(int sNum) // PRETEND the ant collided with a Flag
	{
		// Check which Flag the Ant last hit
		int lastFlag = ((Ant)gameObjectList.get(0)).getLastFlagReached();

		// Since Flags are added to gameObjectList starting at index 1, sNum == Flag index
		if (sNum == lastFlag + 1)
		{
			// This is the next Flag in the sequence, update lastFlagReached for the Ant
			((Ant)gameObjectList.get(0)).setLastFlagReached(sNum);
			System.out.println("Hit flag " + sNum);
			
			if (!(gameObjectList.get(lastFlag + 1) instanceof Flag))
			{
				// There are no more Flags, game is won
				System.out.println("Game over, you win! Total time: " + clock);
				System.exit(0);
			}
		}
	}
	
	public void hitFood() // PRETEND the ant collided with a FoodStation
	{
		int cap = 0;
		
		// Find a FoodStation
		for (int i=0; i<gameObjectList.size(); i++)
		{
			if (gameObjectList.get(i) instanceof FoodStation
					&& ((FoodStation)gameObjectList.get(i)).getCapacity() > 0)
			{				
				// Reduce FoodStation's capacity to 0
				cap = ((FoodStation)gameObjectList.get(i)).getCapacity();
				((FoodStation)gameObjectList.get(i)).setCapacity(0);
				
				// Fade the color of the FoodStation to light green
				((FoodStation)gameObjectList.get(i)).setColor(ColorUtil.rgb(140, 239, 117));
				
				// Add a new FoodStation
				gameObjectList.add(new FoodStation());
				break; // No need to find another FoodStation
			}
		}
		
		// Increase Ant's foodLevel by the hit FoodStation's capacity
		((Ant)gameObjectList.get(0)).changeFoodLevel(cap);
		System.out.println("+" + cap + " food (hit FoodStation)");
	}

	public void hitSpider() // PRETEND the ant collided with a Spider
	{
		// Reduce Ant's health by 1
		((Ant)gameObjectList.get(0)).changeHealthLevel(-1);
		System.out.println("-1 health (hit spider)");
		
		// Fade Ant color
		int green = ColorUtil.green(((Ant)gameObjectList.get(0)).getColor());
		int blue = ColorUtil.blue(((Ant)gameObjectList.get(0)).getColor());
		((Ant)gameObjectList.get(0)).setColor(ColorUtil.rgb(255, green + 25, blue + 25));
		
		if (((Ant)gameObjectList.get(0)).getHealthLevel() == 0)
		{
			// Ant's health is 0, life lost
			this.lives--;
			System.out.println("-1 life (0 health)");
			this.init();
		}
	}

	public void clockTick()
	{
		for (int i=0; i<gameObjectList.size(); i++)
		{
			// Spiders update their heading
			if (gameObjectList.get(i) instanceof Spider)
			{
				((Spider)gameObjectList.get(i)).updateHeading();
			}
			
			// Movables update their location
			if (gameObjectList.get(i) instanceof  Movable)
			{
				((Movable)gameObjectList.get(i)).move();
			}
		}

		// Reduce Ant's foodLevel by foodConsumptionRate
		int consumptionRate = ((Ant)gameObjectList.get(0)).getFoodConsumptionRate();
		((Ant)gameObjectList.get(0)).changeFoodLevel(-consumptionRate);

		// Increment game clock
		this.clock++;
		System.out.println("+1 time (clock ticked)");
		
		if (((Ant)gameObjectList.get(0)).getFoodLevel() == 0)
		{
			// Ant's food is 0, life lost
			this.lives--;
			System.out.println("-1 life (0 food)");
			this.init();
		}
	}

	public void display()
	{
		// Generate a "display" describing the current game/ant state values
		System.out.println("Lives="  	+ this.lives +
						   " Clock=" 	+ this.clock +
						   " lastFlag=" + ((Ant)gameObjectList.get(0)).getLastFlagReached() +
						   " Food=" 	+ ((Ant)gameObjectList.get(0)).getFoodLevel() +
						   " Health=" 	+ ((Ant)gameObjectList.get(0)).getHealthLevel());
	}

	public void map()
	{
		// Output a "map" showing the current world
		for (int i=0; i<gameObjectList.size(); i++)
		{
			System.out.print(gameObjectList.get(i).toString());
		}
	}
}
