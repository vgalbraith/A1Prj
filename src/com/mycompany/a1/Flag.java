package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed
{
	private static int sequenceNumber = 1;

	/**
	 * Class constructor.
	 * 
	 * @param loc location
	 */
	public Flag(Point loc)
	{
		super(10,			   // size:		   	  10
			  loc,			   // location:	   	  loc
			  ColorUtil.BLUE); // color:		  blue
		this.sequenceNumber++; // sequenceNumber: previous Flag + 1
	}
	
	// color methods
	@Override
	public void setColor(int c)
	{
		// Flags are not allowed to change color once they are created
	}
	
	@Override
	public String toString()
	{
		String s = "Flag: loc=" + Math.round(getLocation().getX()*10.0)/10.0 + ","
				   				+ Math.round(getLocation().getY()*10.0)/10.0 +
				   " color=[" 	+ ColorUtil.red(getColor())+ ","
			 	 			    + ColorUtil.green(getColor())+ ","
				 			    + ColorUtil.blue(getColor()) + "]" +
				   " size="  	+ getSize() +
				   " seqNum=" 	+ this.sequenceNumber + "\n";
		return s ;
	}
}
