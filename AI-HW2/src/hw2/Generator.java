/*
 *  CS 461 - HW 2 
 *  Group Name: Minigma
 *  Group Members:  Irem Yuksel, Utku Uckun, Petek Ellialtioglu, Berk Izgi Danis
 * 
 */

package hw2;


public class Generator {
	
	//properties
	int noOfStates;
	int stateCount;
	Board[] states;
	
	public Generator(int noOfStates, int[] startBoard)
	{
		stateCount = 0;
		this.noOfStates = noOfStates + 100;	//+ 10 is to make intial states more complex. Choose last 10 'noOfStates' boards from the end of the states array and use them as initial states.
		states = new Board[this.noOfStates];
		states[0] = new Board(startBoard, stateCount++);
		
	}
	
	public Board[] Generate()
	{
		for(int i = 0; i < stateCount ; i++)
		{
			
			int[] values = states[i].applyFunction();
			int[] newBoard = new int[9];
			for(int j = 0; j < states[i].getMaxNumOfNeigb(); j++)
			{
				boolean temp = true;	//for making sure if the state is a new state, we create it and add it to the net
				if(values[j] != -1)
					newBoard = states[i].exchange( values[j]);
				
				for(int k = 0; k < stateCount && temp; k++)
				{							
					if(states[k].compare(newBoard))
					{
						if(i != k)
							states[i].setNeigbour(k);
						temp = false;
					}
				}
			
				if(temp && stateCount < noOfStates) //The state is a new state/creating a new state. 
				{
					states[i].setNeigbour(stateCount);
					states[stateCount] = new Board(newBoard, stateCount);
					states[stateCount].calcDist(states[0]); //Manhattan distance to the initial state
					stateCount++;
				}
			}
		}
		return states;
	}
	
}
