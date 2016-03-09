/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Graphics;


public class PopcornPopper 		
	extends Appliance {
	
	/*
	 * Watch your profits pop!
	 */
	
	public PopcornPopper() {

		/*
		 * In all of these, the numbers aren't intrinsically related to the type
		 * of appliance.  They are just arbitrarilly chosen.
		 */
		
		amps = 4.1;
		bigPlug = true;
		rentalCost = 195.99;
		hourlyIncome = 78.95;
		reductionFactor = .75;
	}
	
	public void paint (Graphics pane)
	{
		/*
		 * This draws the outside frame of the popcorn holding cabinet.
		 */
		
		pane.setColor(Color.red);
		pane.fillRect(x-10, y-10, 50, 60);
		pane.clearRect(x-5, y+5, 40, 40);
		
		/*
		 * This draws the metal popper itself.
		 */
		
		pane.setColor(Color.gray);
		pane.fillRoundRect(x, y+5, 20, 15, 3, 3);
		
		/*
		 * This draws the layer of popped corn at the bottom of the cabinet.
		 */
		
		pane.setColor(Color.yellow);
		int[] cornXPoints = {x-5, x-5, x+35, x+35};
		int[] cornYPoints = {y+20, y+45, y+45, y+30};
		pane.fillPolygon(cornXPoints, cornYPoints, 4);
		
		/*
		 * This draws the body of the tub next to the popper.
		 */
		
		pane.setColor(Color.lightGray);
		int[] tubXPoints = {x+33, x+23, x+21, x+35};
		int[] tubYPoints = {y+30, y+30, y+40, y+40};
		pane.fillPolygon(tubXPoints, tubYPoints, 4);
		
		/*
		 * This draws the multiple rims to give the illusion of a stack of tubs.
		 */
		
		int i = 0;
		while (i < 5)
		{
			pane.setColor(Color.white);
			pane.fillRect(x+21, y+40+(2*i), 14, 2);
			pane.setColor(Color.gray);
			pane.drawRect(x+21, y+40+(2*i), 14, 2);
			i++;
		}
		
		/*
		 * In all of these, the numbers aren't intrinsically related to the type
		 * of appliance.  They are just arbitrarilly chosen.
		 */
		
		drawPlug(pane);
		
	}

}
