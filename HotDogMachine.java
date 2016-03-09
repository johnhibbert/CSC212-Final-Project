/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Graphics;


public class HotDogMachine 
		extends Appliance {

	/*
	 * Nothing is more American than a hot dog.
	 */
	
	public HotDogMachine() {

		/*
		 * In all of these, the numbers aren't intrinsically related to the type
		 * of appliance.  They are just arbitrarilly chosen.
		 */
		
		amps = 4.5;
		bigPlug = true;
		rentalCost = 199.99;
		hourlyIncome = 149.30;
		reductionFactor = .3;

	}
	
	public void paint (Graphics pane)
	{
		
		/*
		 * This draws the mostly-unseen base for the machine.
		 */
		pane.setColor(Color.lightGray);
		pane.fillRect(x, y+3, 35, 40);
		pane.setColor(Color.gray);
		
		/*
		 * This draws the 7 identical rollers the dogs sit on.
		 */
		
		int i = 1;
		while(i < 8)
		{
		pane.fillRoundRect(x-5, y+(5*i), 45, 4, 5, 5);
		i++;
		}
		
		/*
		 * This draws the hotdogs themselves.
		 */
		
		pane.setColor(Color.red);
		pane.fillRoundRect(x+10, y+6, 15, 4, 3, 3);
		pane.fillRoundRect(x+25, y+11, 15, 4, 3, 3);
		pane.fillRoundRect(x+5, y+16, 15, 4, 3, 3);
		pane.fillRoundRect(x+18, y+21, 15, 4, 3, 3);
		pane.fillRoundRect(x+2, y+26, 15, 4, 3, 3);
		pane.fillRoundRect(x+20, y+26, 15, 4, 3, 3);
		pane.fillRoundRect(x+12, y+31, 15, 4, 3, 3);

		/*
		 * Each one has to call the inherited drawPlug.  Might as well be here.
		 */
		
		drawPlug(pane);
		
	}

	
}
