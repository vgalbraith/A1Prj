package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject
{
	private int heading;
	private int speed;

	/**
	 * Class constructor.
	 * 
	 * @param sz  size
	 * @param loc location
	 * @param c	  color
	 * @param h	  heading
	 * @param sp  speed
	 */
	public Movable(int sz, Point loc, int c, int h, int sp)
	{
		super(sz, loc, c);
		this.heading = h;
		this.speed = sp;
	}

	public void move()
	{
		// Updatelocation based on current heading and speed
		this.setLocation(new Point(
				getLocation().getX() + (float)Math.cos(Math.toRadians(90 - heading)) * speed,
				getLocation().getY() + (float)Math.sin(Math.toRadians(90 - heading)) * speed));
		
		// Constrict Movable object to the defined game grid
		if (getLocation().getX() < 0)
		{
			setLocation(new Point(0, getLocation().getY()));
		}
		else if (getLocation().getX() > 1000)
		{
			setLocation(new Point(1000, getLocation().getY()));
		}
		if (getLocation().getY() < 0)
		{
			setLocation(new Point(getLocation().getX(), 0));
		}
		else if (getLocation().getY() > 1000)
		{
			setLocation(new Point(getLocation().getX(), 1000));
		}
	}

	// heading methods
	public int getHeading()
	{
		return heading;
	}
	public void setHeading(int h)
	{
		this.heading = h;
	}

	// speed methods
	public int getSpeed()
	{
		return speed;
	}
	public void setSpeed(int sp)
	{
		this.speed = sp;
	}
}
