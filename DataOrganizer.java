/*
 * John Hibbert
 * 12/2/14
 * Final Project
 */

import java.awt.Graphics;

public class DataOrganizer {

	Node head;
	//int x;
	//int y;
	
	public DataOrganizer()
	{
		head = null;
	}
	
	public void plugIn(Appliance givenAppliance)//aka Add
	{
		/*
		 * This is the add method.
		 * 
		 * For this particular implementation, we add things in a few ways:
		 * 
		 * If it has a small plug, you put it at the end no matter what.
		 * 
		 * If it has a big plug, you put past any other big plugs but behind any
		 * small plugs.
		 * 
		 */
		
		Node holder = new Node();
		holder.setItem(givenAppliance);
		Node current = head;
		
		/*
		 * If it's empty, we know it goes in the first location.
		 */
		
		if (head == null)
		{
			head = holder;
		}
		
		/*
		 * Here is the weird part: where we deal with adding big plugs.
		 */
		
		else
		{	
		if(holder.getItem().hasBigPlug())
			{
			
			/*
			 * If it has a big plug, we iterate through the list until we
			 * either find the first small plug or find the end (which is 
			 * only possible if there are no small plugs at all).
			 */
			
				while(current.hasNext() && current.getItem().hasBigPlug())
				{
					current = current.getNext();
				}
				
			/*
			 * At that point, we check to make sure current has a previous.
			 * That should only happen if the only one was the first item in
			 * the list.  In that case, we stick the new one behind it and make
			 * it the new head.
			 */	
				
				if(!current.hasPrev())
				{
					current.setPrev(holder);
					holder.setNext(current);
					head = holder;
				}
				
			/*
			 * If not, whether or not we stopped because of finding a small
			 * plug or the end, we simply take the next and prevs and stick 
			 * the new one between current and current's prev.
			 */	
				
				else
				{
					Node prevHolder = current.getPrev();
					Node nextHolder = current;
					holder.setPrev(prevHolder);
					holder.setNext(nextHolder);
					prevHolder.setNext(holder);
					nextHolder.setPrev(holder);
				}
			}
	
		/*
		 * I had built a version that always puts big plugs at the
		 * beginning.  It's simple and that let me work out the
		 * other problems.  In case at some point I need to put
		 * this back in here, I'm leaving it here.
		 * 
				else
				if(holder.getItem().hasBigPlug())
				{
					//put in front
					holder.setNext(head);
					head.setPrev(holder);
					head = holder;
					holder.setPrev(null);
				}
		 */
		
		/*
		 * If it's not a big plug, we're putting it at the end
		 * no matter what.
		 */
		
		else
		{
			while(current.hasNext())	
			{
				current = current.getNext();
			}
			
			current.setNext(holder);
			holder.setPrev(current);
		}
		}	
		updateLocations();	

	}
	
	public void updateLocations()
	{
		/*
		 * Every time we add or remove something, we want to call this to reset
		 * the positions of the appliances so they draw in the correct order.
		 * 
		 * This is kind of like hitting a fly with a sledgehammer, but it's an
		 * implementation that works.
		 */
		
		/*
		 * These variables need to be the same value as applianceX and applianceY
		 * in BoothOutletManager.  I could probably have them be the same, but
		 * I'm afraid to break it.
		 */
		
		Node current = head;
		int setX = 50;
		int setY = 250;
	
		/*
		 * We check if the current node is null, then we confirm it has an item
		 * (even though it always should by current implemenation).
		 */
		
		while(current != null)
		{
			if(current.getItem() != null)
			{
				current.getItem().setLocation(setX, setY);
				
			/*
			 * Now we check if it has a big plug, and whether it's followed by
			 * another big plug.  If both are true, we increment 100.  If not,
			 * we only increment by 50.	
			 */
				
				if(current.getItem().hasBigPlug())
				{
					if(current.hasNext())
					{
						if(!current.getNext().getItem().hasBigPlug())
						{
							setX = setX + 50;
						}
						else
						{
							setX = setX + 100;
						}
					}
				}
				else
				{
						setX = setX + 50;
				}
			}
			
			/*
			 * Then we iterate the whole way through.
			 */
				current = current.getNext();
		}

	}
	
	public void unplug(Node givenNode)//aka Remove
	{
		/*
		 * This method is the remove method.
		 * 
		 * I thought removing would be simple for a double-linked list,
		 * but it's more complicated than I would have thought.
		 * 
		 * Brandon helped me slay some of the bugs in this one.
		 */

		/*
		 * If we think about this, there are four states the given node
		 * can be in:
		 * 
		 * 		1: The node can have a next and a previous.
		 * 
		 * In this case, we put the next and previous in temporary storage,
		 * then set the previous's next to next and next's prev to prev.
		 */
		
		if(givenNode.hasNext() && givenNode.hasPrev())
		{
			Node prevHolder = givenNode.getPrev();
			Node nextHolder = givenNode.getNext();
			prevHolder.setNext(nextHolder);
			nextHolder.setPrev(prevHolder);
		}
		
		/*
		 * 		2: The node can have a next, but not a previous.
		 * 
		 * This can only happen at the very beginning of the list.  In that
		 * case we just set head to be the one after the given node, and make
		 * sure that node's previous is now null.
		 */
		
		else if (givenNode.hasNext() && !givenNode.hasPrev())
		{
			head = givenNode.getNext();
			head.setPrev(null);
		}
		
		/*
		 * 		3: The node has no next, but has a previous.
		 * 
		 * This can only happen at the end of the list.  So we take the prev
		 * of the given node and set its next to null.
		 */
		
		else if (!givenNode.hasNext() && givenNode.hasPrev())
		{
			Node prevHolder = givenNode.getPrev();
			prevHolder.setNext(null);
		} 
		
		/*
		 * 		4: The node has no next and no previous.
		 * 
		 * This can only happen if the node is the only element in the list.
		 * That means we can remove it simply by setting head to null.
		 */
		
		else if(!givenNode.hasNext() && !givenNode.hasPrev())
		{
			head = null;
		}
				
		/*
		 * Then we update the locations, as the Xs and Ys don't match
		 * their relative place in the list.
		 */
		
		updateLocations();
	}
	
	/*
	 * Next, we have two 'find' functions:
	 * 
	 * 		findHighestAmps()
	 * 		findLowestIncome()
	 *  
	 * They all have a very similar structure, and maybe they could have
	 * somehow been worked to each call some other function to reduce
	 * repetitive code, but I didn't think it was worth the brainpower.
	 * The functions are different enough in principle to warrant their own
	 * functions.
	 * 
	 * You may notice the loop is a little weird, what with the keepGoing
	 * boolean variable.  If I remember correctly, I borrowed this from
	 * a different program, and didn't bother changing it back.  It's
	 * probably not as efficient as it could be, but I'm afraid to break it,
	 * so I left it.
	 */
	
	public Node findHighestAmps()
	{
		Node result = head;
		Node current;
		
		if(head == null)
		{
			return head;
		}
		
		if(head.hasNext())
		{
				
		current = head.getNext();
		boolean keepGoing = true;
		if (current != null)
		{
		while (current != null && keepGoing)
			{
			
			/*
			 * Just as you might expect, if current has higher amps, we
			 * save current instead.  Then we iterate the whole way through.
			 */
			
			if (result.getItem().getAmps() < current.getItem().getAmps()) 
			{
				result = current;
			}
			
			if (current.hasNext())
			{
				current = current.getNext();
			}
			else keepGoing = false;
			}
		}
		}
		return result;
	}
	
	public Node findLowestIncome()
	{
		Node result = head;
		Node current;

		
		if(head == null)
		{
			return head;
		}
		
		if(head.hasNext())
		{
				
		current = head.getNext();
		boolean keepGoing = true;
		if (current != null)
		{
		while (current != null && keepGoing)
			{
			
			/*
			 * Just as you might expect, if current has higher amps, we
			 * save current instead.  Then we iterate the whole way through.
			 */
			
			if (result.getItem().getHourlyIncome() > current.getItem().getHourlyIncome()) 
			{
				result = current;
			}
			
			if (current.hasNext())
			{
				current = current.getNext();
			}
			else keepGoing = false;
			}
		}
		}
		return result;
	}
	
	/*
	 * Next, we have three 'get' functions:
	 * 
	 * 		getTotalAmps()
	 * 		getTotalRentalCost()
	 *		getTotalHourlyIncome()
	 *  
	 * Like above, these are all similar, and might be able to be reduced to a function
	 * that calls other functions, but I put that energy into other things.
	 * 
	 * Also, like above, these are copied from a weird loop and probably could be fixed up.
	 */
	
	public double getTotalAmps()
	{
		double result = 0;
		Node current;
	
		if(head != null)
		{
				
		current = head;
		boolean keepGoing = true;
		if (current != null)
		{
		while (current != null && keepGoing)
			{
			result = result + current.getItem().getAmps();
				
			if (current.hasNext())
			{
				current = current.getNext();
			}
			else keepGoing = false;
			}
		}
	}

		return result;
	}
	
	
	public double getTotalRentalCost()
	{
		double result = 0;
		Node current;
	
		if(head != null)
		{
				
		current = head;
		boolean keepGoing = true;
		if (current != null)
		{
		while (current != null && keepGoing)
			{
			result = result + current.getItem().getRentalCost();
				
			if (current.hasNext())
			{
				current = current.getNext();
			}
			else keepGoing = false;
			}
		}
	}

		return result;
	}
	
	public double getTotalHourlyIncome()
	{
		double result = 0;
		Node current;
	
		if(head != null)
		{
				
		current = head;
		boolean keepGoing = true;
		if (current != null)
		{
		while (current != null && keepGoing)
			{
			result = result + current.getItem().getHourlyIncome();
				
			if (current.hasNext())
			{
				current = current.getNext();
			}
			else keepGoing = false;
			}
		}
	}

		return result;
	}
	
	
	
	public void paint(Graphics pane)
	{

		Node current = head;
		boolean keepGoing = true;
		if (current != null)
		{
		while (current != null && keepGoing)
			{
			
			/*
			 * This is the second step in painting the actual appliances.
			 * It calls to the Node's paint function.
			 */
			
				current.paint(pane);
				if (current.hasNext())
				{
					current = current.getNext();
				}
				else keepGoing = false;
			}
		}
	}
}
