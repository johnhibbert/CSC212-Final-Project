/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Graphics;


public abstract class Appliance {
	
	/*
	 * Brandon was very helpful in explaining what I was doing wrong when attempting
	 * to have all the specific appliances inherit these classes.  Abstract classes
	 * are a little fiddly, but it gets rid of so much repetitive code.
	 */
	
	/*
	 * Below are the variables that are common to each appliance.  These are assigned
	 * in the constructors.  Except for x and y, the variables could be finals, as they
	 * don't have to change.
	 * 
	 * As of this moment (3PM 12/2/14), I have not implemented the 'reductionFactor.'
	 * This was meant to reduce the income from multiple versions of the same machine.  
	 * It was always a "could have" in the old MoSCoW method, and really only serves to make
	 * the program operate slightly more like a game so it's not so boring.  I left the
	 * references to it in there, as it could be eventually implemented, and maybe I'll
	 * even manage to get to it myself before this is due.
	 * 
	 * Another thing to keep in mind is that the amps, rentalCost and hourlyIncome are
	 * essentially limited to 2 decimal places because of the way the bigDecimals are
	 * rounded in the DataOrganizer.  I made it a final variable called ROUNDINGFACTOR,
	 * so it would be easier to change later.  It doesn't really matter, since costs
	 * and profits won't be in fractions of pennies and it's probably not necessary
	 * to consider amps down to the thousandth.  Still, this is better software
	 * engineering, and I should get into the habit of it.
	 */
	
	protected double amps;
	protected double rentalCost;
	protected double hourlyIncome;
	protected double reductionFactor;
	protected boolean bigPlug;
	protected int x;
	protected int y;
	
	/*
	 * These are the methods that all the appliances need in order to properly interact
	 * with the program at large.  They do not change based on the appliance at all, so
	 * inheritance is a good idea here.
	 */
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getAmps(){
		return amps;
	}
	
	public double getRentalCost() {
		return rentalCost;
	}
	
	public double getHourlyIncome() {
		return hourlyIncome;
	}
	
	public boolean hasBigPlug()
	{
		return bigPlug;
	}
	
	/*
	 * We can have all the appliances inherit this to save us the trouble to telling
	 * it to paint the plug a particular way in each.
	 */
	
	public void drawPlug(Graphics pane)
	{
			if (this.hasBigPlug())
			{
				pane.setColor(Color.gray);
				pane.fillRect(x-35, y+215, 70, 30);
			}
			else
			{
				pane.setColor(Color.gray);
				pane.fillRect(x+15, y+215, 20, 40);
			}
		
	}
	
	/*
	 * Each appliance is drawn differently, but we know that every appliance must be drawn.
	 * So we can force it to have a paint method, but we will override it in the child class.
	 * 
	 * I remember from 211 that paint methods are somehow unusual, and should be given empty
	 * method bodies instead of being an abstract class, but I might be mis-remembering it.
	 * This seems to work, so I wasn't going to sweat it too much.
	 */
	
	public void paint(Graphics pane){};
	
}
