/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Graphics;


public class Microwave 
		extends Appliance {
	
	/*
	 * The power of radiation!
	 */
	
	public Microwave() {
		
		/*
		 * In all of these, the numbers aren't intrinsically related to the type
		 * of appliance.  They are just arbitrarilly chosen.
		 */
		
		amps = 4.4;
		bigPlug = true;
		rentalCost = 65.99;
		hourlyIncome = 28.95;
		reductionFactor = .6;
	}
	
	public void paint (Graphics pane)
	{
		
		/*
		 * This block draws the cabinet of the microwave.
		 */
		
		pane.setColor(Color.darkGray);
		pane.fillRect(x-10, y, 50, 30);
		
		/*
		 * This draws the viewing window of the microwave.
		 */
		
		pane.setColor(Color.lightGray);
		pane.fillRoundRect(x-5, y+5, 25, 20, 5, 5);
		
		/*
		 * This draws the bay of buttons on the microwave.
		 */
		
		pane.setColor(Color.white);
		pane.fillRect(x+25, y+5, 10, 20);
		pane.setColor(Color.black);
		pane.drawLine(x+30, y+5, x+30, y+25);
		pane.drawLine(x+25, y+15, x+35, y+15);
		pane.drawLine(x+25, y+10, x+35, y+10);
		pane.drawLine(x+25, y+20, x+35, y+20);
				
		/*
		 * In all of these, the numbers aren't intrinsically related to the type
		 * of appliance.  They are just arbitrarilly chosen.
		 */
		
		drawPlug(pane);
	
	}



}
