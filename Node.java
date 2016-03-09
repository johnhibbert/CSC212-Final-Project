/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Graphics;

public class Node {

	/*
	 * This is a pretty standard double-link list.  We have setters, getters, and 
	 * has-checks for everything, except the appliance, which is just set and get,
	 * since the node will only exist if it has an appliance.
	 * 
	 * One funny thing about this one was that I was getting odd null pointers and
	 * couldn't find why.  I brought it to Brandon and we couldn't figure out why for
	 * a while, until we noticed that hasPrev() read:
	 * 
	 * 		return next != null;
	 * 
	 * When I built this class originally, I didn't realize I would need a hasPrev(),
	 * so I copied the hasNext() method.  Unfortunately, I neglected to change it to
	 * check prev instead of next, so it was checking that it had a next, then calling
	 * a null prev.
	 * 
	 * Otherwise, there's not much to say.
	 */
	
	private Appliance thisAppliance;
	private Node next;
	private Node prev;
	
	public Node()
	{
		thisAppliance = null;
		next = null;
		prev = null;
	}
	
	public Node(Appliance givenAppliance)
	{
		thisAppliance = givenAppliance;
		next = null;
	}
		
	public Node getNext()
	{
		return next;
	}
	
	public boolean hasNext()
	{
		return next != null;
	}
	
	public void setNext(Node givenNode)
	{
		next = givenNode;
	}
	
	public Node getPrev()
	{
		return prev;
	}
	
	public boolean hasPrev()
	{
		return prev != null;
	}
		
	public void setPrev(Node givenNode)
	{
		prev = givenNode;
	}
	
	public Appliance getItem()
	{
		return thisAppliance;
	}
	
	public void setItem(Appliance givenAppliance)
	{
		thisAppliance = givenAppliance;
	}
	
	public void paint (Graphics pane)
	{
		/*
		 * This is the third step in painting the actual appliances.
		 * It calls to the appliance's paint function.
		 */
		
		if (thisAppliance != null)
		{
			thisAppliance.paint(pane);
		}
	}

	
}
