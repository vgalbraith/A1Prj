package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Ant extends Movable implements ISteerable
{
	private int maximumSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;

	/**
	 * Class constructor.
	 * 
	 * @param loc location
	 */
	public Ant(Point loc)
	{
		super(15,						// size:	 			15
			  loc,						// location: 			loc
			  ColorUtil.rgb(255, 0, 0),	// color: 	 			red
			  0,						// heading:	 			0
			  30);						// speed:	 			30
		this.maximumSpeed = 50;			// maximumSpeed: 		50
		this.foodLevel = 10;			// foodLevel: 			10
		this.foodConsumptionRate = 2;	// foodConsumptionRate: 2
		this.healthLevel = 10;			// healthLevel: 		10
		this.lastFlagReached = 1;		// lastFlagReached: 	1
	}

	// heading methods
	public void changeHeading(int h)
	{
		// Change heading by +h
		super.setHeading(getHeading() + h);
	}

	// speed methods
	public void changeSpeed(int sp)
	{
		// Change speed by +sp
		super.setSpeed(getSpeed() + sp);
		
		// Enforce speed-limitation rule
		if (getSpeed() < 0) super.setSpeed(0);
		if (getSpeed() > (maximumSpeed * healthLevel) / 10)
		{
			super.setSpeed((maximumSpeed * healthLevel) / 10);
		}
	}

	// foodLevel methods
	public int getFoodLevel()
	{
		return foodLevel;
	}
	public void changeFoodLevel(int f)
	{
		// Change foodLevel by +f
		this.foodLevel += f;
		
		if (foodLevel <= 0)
		{
			this.foodLevel = 0;
			super.setSpeed(0);
		}
	}
	
	// foodConsumptionRate methods
	public int getFoodConsumptionRate()
	{
		return foodConsumptionRate;
	}

	// healthLevel methods
	public int getHealthLevel()
	{
		return healthLevel;
	}
	public void changeHealthLevel(int hp)
	{
		// Change healthLevel by +hp
		this.healthLevel += hp;
		
		// Enforce speed-limitation rule
		if (getSpeed() > (maximumSpeed * healthLevel) / 10)
		{
			super.setSpeed((maximumSpeed * healthLevel) / 10);
		}
	}

	// lastFlagReached methods
	public int getLastFlagReached()
	{
		return lastFlagReached;
	}
	public void setLastFlagReached(int sNum)
	{
		this.lastFlagReached = sNum;
	}

	@Override
	public String toString()
	{
		String s = "Ant: loc="	  		   + Math.round(getLocation().getX()*10.0)/10.0 + ","
										   + Math.round(getLocation().getY()*10.0)/10.0 +
				   " color=["   		   + ColorUtil.red(getColor()) + ","
				 			      		   + ColorUtil.green(getColor()) + ","
				 			      		   + ColorUtil.blue(getColor()) + "]" +
	 			   " heading="  		   + getHeading() +
	 			   " speed="    		   + getSpeed() +
				   " size="     		   + getSize() +
		   		   " maxSpeed=" 		   + maximumSpeed +
		   		   " foodConsumptionRate=" + foodConsumptionRate + "\n";
		return s;
	}
}
