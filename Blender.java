/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Graphics;


public class Blender 
		extends Appliance {
	
	/*
	 * Margaritas and Pina Coladas, oh my!
	 */
	
	public Blender() {
		
		/*
		 * In all of these, the numbers aren't intrinsically related to the type
		 * of appliance.  They are just arbitrarily chosen.
		 */
		
		amps = 3.3;
		bigPlug = false;
		rentalCost = 129.99;
		hourlyIncome = 63.95;
		reductionFactor = .75;
	}
	
	public void paint (Graphics pane)
	{
		/*
		 * This block draws the lid.
		 */
		
		pane.setColor(Color.gray);
		pane.fillRect(x+5, y-5, 10, 5);
		pane.setColor(Color.lightGray);
		pane.fillRect(x, y, 20, 10);
		
		/*
		 * This block draws carafe of the blender.
		 */
		
		pane.setColor(Color.blue);
		int[] carafeXPoints = {x+2, x+4, x+16, x+18};
		int[] carafeYPoints = {y+10, y+30, y+30, y+10};
		pane.fillPolygon(carafeXPoints, carafeYPoints, 4);
		pane.setColor(Color.black);
		
		/*
		 * This block draws base of the blender.
		 */
		
		int[] baseXPoints = {x, x-5, x+25, x+20};
		int[] baseYPoints = {y+30, y+45, y+45, y+30};
		pane.fillPolygon(baseXPoints, baseYPoints, 4);
		pane.setColor(Color.white);
		
		/*
		 * This block draws the buttons on the base.
		 */
		
		pane.fillRect(x+2, y+35, 5, 5);
		pane.fillRect(x+8, y+35, 5, 5);
		pane.fillRect(x+14, y+35, 5, 5);
		
		/*
		 * Each one has to call the inherited drawPlug.  Might as well be here.
		 */
		
		drawPlug(pane);		
	}

}
