/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Graphics;

public class LavaLamp 
	extends Appliance {

	/*
	 * Whoa, man... this lava lamp is trippy.
	 */
	
public LavaLamp() {
	
	/*
	 * In all of these, the numbers aren't intrinsically related to the type
	 * of appliance.  They are just arbitrarily chosen.
	 */
	
	amps = 1.9;
	bigPlug = false;
	rentalCost = 39.99;
	hourlyIncome = 7.95;
	reductionFactor = .95;
	}

public void paint (Graphics pane)
{
	/*
	 * This block builds the 'bulb' of the lava lamp.
	 */
	
	pane.setColor(Color.magenta);
	int[] bulbXPoints = {x, x+3, x+17, x+20};
	int[] bulbYPoints = {y+30, y-5, y-5, y+30};
	pane.fillPolygon(bulbXPoints, bulbYPoints, 4);
	
	/*
	 * This block adds a random number of slightly randomly shaped ovals
	 * of random color within the constraints of the bulb.  This also
	 * causes them to change with each drawing, which is pretty nice.
	 */
	
	int i = (int) (4.0 * Math.random());
	while (i < 7)
	{
	Color currentColor = new Color(((int) (255 * Math.random())),
			(int) (255 * Math.random()),
			(int) (255 * Math.random()));
		
	pane.setColor(currentColor);
		
	pane.fillOval(x+3+(2*(int) (6.0 * Math.random())),
			y+(i*(int)(4.0 * Math.random())),
			3+(int)(3.5 * Math.random()),
			4+(int)(3.5 * Math.random()));
	i++;
	}
	
	/*
	 * This block draws the base that the lamp sits on.
	 */
	
	pane.setColor(Color.black);
	int[] baseXPoints = {x, x-5, x+25, x+20};
	int[] baseYPoints = {y+30, y+40, y+40, y+30};
	pane.fillPolygon(baseXPoints, baseYPoints, 4);
	pane.setColor(Color.white);
	
	/*
	 * Each one has to call the inherited drawPlug.  Might as well be here.
	 */
		
	drawPlug(pane);
	
		
}
	
}
