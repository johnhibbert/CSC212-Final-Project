/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class BoothOutletManager
				extends Frame
				implements MouseListener {

	/*
	 * This is the main crux of the program.
	 */
	
	/*
	 * MouseListener needs these.
	 */
	private int lastX, lastY;
	
	/*
	 * These are all the buttons we need.
	 */
	
	private Abutton blenderButton, microButton, hotDogButton, lavaLampButton, popcornButton,
				removeAmpsButton, removeIncomeButton;
	
	/*
	 * This is where we create the data organizer.
	 */
	
	private DataOrganizer myDataOrganizer = new DataOrganizer();

	/*
	 * I have both x & y and applianceX and applianceY because I
	 * wanted to be sure that the things that were already using x
	 * and y wouldn't mess with what I needed to place the appliances
	 * where they had to go.
	 */
	
	int x;
	int y;
	
	/*
	 * Note These variables need to be the same value as setX and SetY
	 * in DataOrganizer's updateLocations().  I could probably have
	 * them be the same, but I'm afraid to break it.
	 */
	
	int applianceX = 50;
	int applianceY = 250;
	
	/*
	 * These are three constants.  The programmer could change them if
	 * they wished, but they won't be changed in the runtime environment.
	 */
	
	final int HOURSBOOTHWILLBEOPEN = 8;
	final double MAXIMUMAMPS = 16.2;
	final double BOOTHRENTALCOST = 1450;
	final int ROUNDINGFACTOR = 2;
	/*
	 * This is a boolean to see if we have too much plugged in right now.
	 */
	
	boolean overloaded = false;
	
	public BoothOutletManager() {
		
		/*
		 * This is the constructor.  We do a lot of work in here.
		 * Mostly, we create the buttons and do some decorative
		 * stuff. 
		 */
		
		
		/*
		 * Some basic housekeeping to make the window pretty...
		 */
		
		setTitle("Booth Outlet Manager!");
		setLocation(50, 50);					
		setSize(600, 600);
		Color bgColor = new Color(0, 215, 215);
		setBackground(bgColor);			
		setVisible(true);					

		UneFenetre myWindow = new UneFenetre();
		
		addWindowListener(myWindow);		
		addMouseListener(this);				
		
		/*
		 * This is where we create the button objects.
		 */
		
		x = 50;
		y = 50;

		blenderButton = new Abutton("Blender", Color.cyan, x, y);
		y += 1.1*Abutton.BUTTON_HEIGHT;
		microButton = new Abutton("Microwave", Color.cyan, x, y);
		
		x = (int) ((int) 50 + 1.1*Abutton.BUTTON_WIDTH);
		y = 50;
		hotDogButton = new Abutton("Hot Dogs", Color.cyan, x, y);
		y += 1.1*Abutton.BUTTON_HEIGHT;
		lavaLampButton = new Abutton("Lava Lamp", Color.cyan, x, y);
		
		
		x = (int) ((int) 50 + (2 * 1.1*Abutton.BUTTON_WIDTH));
		y = 50;
		popcornButton = new Abutton("Popcorn", Color.cyan, x, y);
		
		x = 335;
		y = 50;	
		
		removeAmpsButton = new Abutton("Rmv Amps", Color.yellow, x, y);
		y += 1.1*Abutton.BUTTON_HEIGHT;
		removeIncomeButton = new Abutton("Rmv Income", Color.yellow, x, y);
	
	}
	
	
	public void paint(Graphics pane)
	{
		/*
		 * This is the paint method.  It's full of null checks.
		 */
	
		if (blenderButton != null)
			blenderButton.paint(pane);
		
		if (microButton != null)
			microButton.paint(pane);
		
		if (hotDogButton != null)
			hotDogButton.paint(pane);
		
		if (lavaLampButton != null)
			lavaLampButton.paint(pane);
		
		if (popcornButton != null)
			popcornButton.paint(pane);
		
		if (removeAmpsButton != null)
			removeAmpsButton.paint(pane);
		
		if (removeIncomeButton != null)
			removeIncomeButton.paint(pane);
		
		/*
		 * This is the first step in painting the actual appliances.
		 * It calls to the Data Organizer's paint function.
		 */
		
		if (myDataOrganizer != null)
			myDataOrganizer.paint(pane);
		
		/*
		 * These three methods aren't related to any objects
		 * so they don't need null checks, and they'll always
		 * want to be visible.
		 */
		
		drawPowerStrip(pane);
		drawTotals(pane);
		drawAmps(pane);

	}
	
	public void drawPowerStrip(Graphics pane)
	{	
		/*
		 * This draws the main body of the power strip.
		 */
		
		x = 55;
		y = 495;
		
		pane.setColor(Color.lightGray);
		
		/*
		 * This loop draws the 10 outlets.
		 */
		
		for(int i = 0; i < 10; i++)
		{
			drawOutlet(pane, x, y);
			x = x + 50;
		}
		
		/*
		 * This loop draws the body and the cord.
		 */
		
		pane.setColor(Color.gray);
		pane.fillRect(450, 525, 200, 20);
		pane.fillRect(50, 500, 500, 50);
		
		/*
		 * I was trying to figure out how to make this in a different
		 * font but it seemed like a real nuisance, so I left it the 
		 * way it was.
		 */
		
		pane.setColor(Color.yellow);
		pane.drawString("Booth Outlet Manager", 250, 525);
	}
	
	
	public void drawOutlet(Graphics pane, int x, int y)
	{
		pane.fillRoundRect(x, y, 40, 10, 4, 4);
	}
	
	
	public void drawTotals(Graphics pane)
	{
		/*
		 * This method draws the table that lists the condition of the money.
		 * It displays the cash spent on rentals, cash gained from using
		 * the appliances, and the net gain.
		 * 
		 * It does this by calling the getTotalHourlyIncome() and
		 * getTotalRentalCost() methods.
		 * 
		 * One slight problem with this implementation is that some of the 
		 * calculations end up not being as round as you might think because
		 * of strangeness of java math.  I couldn't work out a solution myself,
		 * but I found a solution listed here:
		 *  
		 * http://stackoverflow.com/questions/15643280/rounding-bigdecimal-to-always-have-two-decimal-places
		 * 
		 * That is implemented in drawTotals() and draw amps() because they
		 * are the only places that need to worry about that.  Since every
		 * appliance's profits and amperages are to the tenth or hundredth, 
		 * there should be no meaningful calculation errors anywhere else.
		 * Even so, I made the rounding factor a final of 2 instead of
		 * leaving it a hardcoded 2.
		 */
		
		BigDecimal incomeBD = new BigDecimal(myDataOrganizer.getTotalHourlyIncome()
														* HOURSBOOTHWILLBEOPEN);
		incomeBD = incomeBD.setScale(ROUNDINGFACTOR, RoundingMode.CEILING);
		
		BigDecimal outlayBD = new BigDecimal(myDataOrganizer.getTotalRentalCost()
															+ BOOTHRENTALCOST);
		
		outlayBD = outlayBD.setScale(ROUNDINGFACTOR, RoundingMode.CEILING);
		
		BigDecimal netIncomeBD = incomeBD.subtract(outlayBD);
		
		pane.setColor(Color.black);
		pane.fillRect(420, 45, 160, 100);
		pane.setColor(Color.red);
		pane.drawString("Rental Costs:", 425, 75);
		
		/*
		 * Here, I concatenate a "" onto the big decimal to cast it into
		 * a string.  Java doesn't seem to like casting it the normal way,
		 * but this works.  It happens a few more times down here.
		 */
		
		pane.drawString(outlayBD + "", 525, 75);
		pane.setColor(Color.green);
		pane.drawString(HOURSBOOTHWILLBEOPEN + " Hour Profits:", 425, 100);
		pane.drawString(incomeBD + "", 525, 100);
		
		/*
		 * This has a little simple check to know what color to print
		 * the Net Profits as.  Green for profits, red for losses, and
		 * yellow for 0.  It turns out that BigDecimal has a compareTo
		 * method, so we use that rather than the actual numbers, but
		 * the result is the same.
		 */
		
		int profitsComparison = incomeBD.compareTo(outlayBD);
		System.out.println(profitsComparison);
		if (profitsComparison > 0)
			pane.setColor(Color.green);
		else if (profitsComparison < 0)
			pane.setColor(Color.red);
		else pane.setColor(Color.yellow);
		
		pane.drawString("Net Profits:", 425, 125);
		pane.drawString(netIncomeBD + "", 525, 125);
			
	}
	
	public void drawAmps(Graphics pane)
	{
		/*
		 * This method draws the table that counts the number of amps
		 * currently used by the appliances plugged in.
		 * 
		 * As above, this also uses the BigDecimal solution I found
		 * on Stack Overflow.
		 */
		
		BigDecimal totalAmpsBD = new BigDecimal(myDataOrganizer.getTotalAmps());
		totalAmpsBD = totalAmpsBD.setScale(ROUNDINGFACTOR, RoundingMode.CEILING);
		
		pane.setColor(Color.black);
		pane.fillRect(420, 150, 160, 25);
		pane.setColor(Color.red);
		pane.drawString("Amps:", 425, 165);
		String ampString = Double.toString(MAXIMUMAMPS);
		pane.drawString(totalAmpsBD + " / " + ampString, 510, 165);
		
		/*
		 * We only want to display this little warning only when it's
		 * overloaded.
		 */
		
		if(overloaded)
		{
			pane.fillRect(420, 180, 150, 30);
			pane.setColor(Color.white);
			pane.drawString("Overloaded!", 470, 190);
			pane.drawString("Unplug something!", 450, 205);
		}
	}
	
	/*
	 * This is mouseListener.  I left the original documentation below.
	 */
	
	//
//	M o u s e L i s t e n e r
//	=========================
//
//	The implementation of the MouseListener requires the implementation of the
//		following five methods.  Each method deals with a particular event
//		associated with the mouse.
//
//	mouseClicked:	invoked when the mouse button is pressed and released at the
//					same location.
//	mousePressed:	invoked when the mouse button is pressed down
//	mouseReleased:	invoked when the mouse button is released
//	mouseEntered:	invoked when the mouse pointer moves into a component
//					(i.e., the Frame)
//	mouseExited:	invoked when the mouse pointer moves out of a component
//
//	Note that we are "listening" to mouse events that occur in conjunction with the
//		Frame that implements the methods associated with the mouse.  If your
//		application has several frames, each frame can deal with its own mouse
//		events in completely independent ways.
//
//	Only the click of the mouse is of interest to this demo  ... or is it?
//
	public void mouseClicked(MouseEvent event)
	{
		check();							//	Handle the mouse click
	}

	public void mousePressed(MouseEvent event)
	{
		lastX = event.getX();				//	Update the mouse location
		lastY = event.getY();

		flipWhenInside();					//	Flip any button hit by the mouse
	}

	public void mouseReleased(MouseEvent event)
	{
		flipWhenInside();
	}

	public void mouseEntered(MouseEvent event) {}
	public void mouseExited(MouseEvent event) {}

	
	
	private void flipWhenInside()
	{
		/*
		 * This makes the buttons 'press in' in a 3D way
		 * to look like a button is physically depressed.
		 * 
		 * The main change I made here was to make the add
		 * buttons not depress when things are overloaded.
		 * The Abutton class doesn't really let us change
		 * the color, so this hopefully makes it somewhat
		 * clear that those buttons are deactivated.
		 */
		
		if(!overloaded)
		{	
			if (blenderButton.isInside(lastX, lastY))
				blenderButton.flip();					
					
			else if (microButton.isInside(lastX, lastY))
				microButton.flip();
			
			else if (hotDogButton.isInside(lastX, lastY))
				hotDogButton.flip();
			
			else if(lavaLampButton.isInside(lastX,  lastY))
				lavaLampButton.flip();
			
			else if(popcornButton.isInside(lastX,  lastY))
				popcornButton.flip();
		}
		
		/*
		 * These two buttons are outside of the "if not overloaded" statment because
		 * we want to use them even if we are over the amperage limit.
		 */	
		
		if (removeAmpsButton.isInside(lastX, lastY))
			removeAmpsButton.flip();
		
		else if (removeIncomeButton.isInside(lastX, lastY))
			removeIncomeButton.flip();
		
		repaint();
		
		
	}
	
	private void checkAmpLevels()
	{
		/*
		 * This is used to check to make sure the total
		 * amount of amps plugged into the power strip is
		 * equal to or less than the maximum amperage of
		 * the circuit breaker.
		 * 
		 * It does this by comparing the total number to
		 * the final in the BoothOutletManager class.  It
		 * uses a boolean to track that state.
		 * 
		 * In order to make it clear, we change the
		 * background color, as though the lights went out
		 * when we plugged too much in.
		 * 
		 * One other thing to know about this method:
		 * Because every appliance I have added to this
		 * program uses more than 1.62 amps, I don't need
		 * to have a method that checks that there are not
		 * more than ten appliances already plugged in.
		 * Obviously, in a different implementation, you'd
		 * want to have something check that anyway, but
		 * at this time (7:06PM, 12/2/14), I probably will
		 * work on documentation rather than that. 
		 */
		
		if(myDataOrganizer.getTotalAmps() > MAXIMUMAMPS)
		{
			overloaded = true;
			Color bgColor = new Color(97,97,97);
			setBackground(bgColor);
		}
		else
		{
			overloaded = false;
			Color bgColor = new Color(0, 215, 215);
			setBackground(bgColor);
		}
	}
	
	private void check()
	{
		/*
		 * This is the method called when mouseclicked(), per the
		 * implementation of mouselistener.
		 */
		
				
		/*
		 * We don't want to be adding new appliances if we've already
		 * overloaded the circuit breaker.  So, these only work when
		 * it's not overloaded.
		 * 
		 * Each one of these does the following:
		 * 
		 * 		* Instantiates a new object of the given type
		 * 		* Gives that item the location it should be in
		 * 		* increment the location to be ready for the
		 * 			next one.
		 * 		* Add the new appliance to the data organizer.
		 * 		* Update the locations to make sure they are
		 * 			in the right place. (This step might make the
		 * 			step where we give the new item its location
		 * 			redundant, but it works.)
		 * 
		 * 
		 */
		
		if(!overloaded)
		{
			if (blenderButton.isInside(lastX, lastY))
			{
				if (myDataOrganizer != null)
				{
					Appliance blender = new Blender();
					blender.setLocation(applianceX, applianceY);
					applianceX = applianceX + 50;
					myDataOrganizer.plugIn(blender);
				}
			}
			
			else if (microButton.isInside(lastX, lastY))
			{
				if (myDataOrganizer != null)
				{
					Appliance microwave = new Microwave();
					microwave.setLocation(applianceX, applianceY);
					applianceX = applianceX + 50;
					myDataOrganizer.plugIn(microwave);
				}
			}
			
			else if (hotDogButton.isInside(lastX, lastY))
			{
				if (myDataOrganizer != null)
				{
					Appliance hotDogMachine = new HotDogMachine();
					hotDogMachine.setLocation(applianceX, applianceY);
					applianceX = applianceX + 50;
					myDataOrganizer.plugIn(hotDogMachine);
				}
			}
			
			else if(lavaLampButton.isInside(lastX,  lastY))
			{
				if (myDataOrganizer != null)
				{
					Appliance lavaLamp = new LavaLamp();
					lavaLamp.setLocation(applianceX, applianceY);
					applianceX = applianceX + 50;
					myDataOrganizer.plugIn(lavaLamp);
				}
			}
			
			else if (popcornButton.isInside(lastX,  lastY))
			{
				if (myDataOrganizer != null)
				{
					Appliance popcornPopper = new PopcornPopper();
					popcornPopper.setLocation(applianceX, applianceY);
					applianceX = applianceX + 50;
					myDataOrganizer.plugIn(popcornPopper);
				}
			}
		}
		
		/*
		 * These two buttons are outside of the "if not overloaded" statment because
		 * we want to use them even if we are over the amperage limit.
		 * 
		 * Each checks to make sure that the data organizer object is not null
		 * (which is should never be), and that the head is not null (and it 
		 * should only be null if the organizer is empty), which should prevent
		 * it from causing a null pointer.
		 * 
		 * A funny glitch to keep in mind: if you leave the 'else' before the if
		 * below, it will cause those two buttons to only work when the system is
		 * overloaded.
		 */
		
		
		if (removeAmpsButton.isInside(lastX, lastY))
		{
			if (myDataOrganizer != null)
			{
				if (myDataOrganizer.head != null)
				{
					myDataOrganizer.unplug(myDataOrganizer.findHighestAmps());
				}
			}
		}
		
		else if (removeIncomeButton.isInside(lastX, lastY))
		{
			if (myDataOrganizer != null)
			{
				if (myDataOrganizer.head != null)
				{
					myDataOrganizer.unplug(myDataOrganizer.findLowestIncome());
				}
			}
		}
		
		/*
		 * Now, since we've either added or removed an appliance with these
		 * clicks, we want to confirm where the levels are.  So we run
		 * checkAmpLevels().
		 */
		
		checkAmpLevels();
		
		/*
		 * Again, since we have changed things, we want to repaint.
		 */
		
		repaint();
	}
	
}
