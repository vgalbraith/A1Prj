package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject
{
	/**
	 * Class constructor.
	 * 
	 * @param sz  size
	 * @param loc location
	 * @param c	  color
	 */
	public Fixed(int sz, Point loc, int c)
	{
		super(sz, loc, c);
	}
	
	// location methods
	@Override
	public void setLocation(Point loc)
	{
		// All fixed game objects are not allowed to change location once they are created.
	}
}
