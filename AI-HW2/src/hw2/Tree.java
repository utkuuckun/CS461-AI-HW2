/*
 *  CS 461 - HW 2 
 *  Group Name: Minigma
 *  Group Members:  Irem Yuksel, Utku Uckun, Petek Ellialtioglu, Berk Izgi Danis
 * 
 */

package hw2;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class Tree {
	private int[][] neighbours;
	private LinkedList<Integer> queue;
	Board[] states;
	HashMap <Integer, Integer> map;
	int[] s;
	private static HashMap<Integer, Integer> closedList;
	private static HashMap<Integer, Integer> beam;
	private static HashMap<Integer, Integer> set;
	public Tree(Board[] x)
	{
		states = x;
		neighbours = new int[states.length][4];
		
		for (int i = states.length-1; i >= 0; i--) {
			System.out.println( "HHH" );
			 s = states[i].getNeighbours();
			
			 neighbours[states[i].getState_id()][0] = s[0];
			 System.out.println( s[0] );
			 neighbours[states[i].getState_id()][1] = s[1];
			 System.out.println( s[1] );
			 neighbours[states[i].getState_id()][2] = s[2];
			 neighbours[states[i].getState_id()][3] = s[3];
			 System.out.println( s[2] );
			 System.out.println( s[3] );
		}
			 
	}
	
	public HashMap<Integer, Integer> getSolution() {
		return beam;
	}
	public LinkedList<Integer> getQueue(){
		return queue;
	}
	
	public boolean beamSearch(int width, int start, int goal) { 
		
		closedList = new HashMap<Integer, Integer>();
		beam = new HashMap<Integer, Integer>();
	
		closedList.put(start, 0);
        beam.put(start, 0);
		int g =0;
		
        while (beam.size() != 0) {
            set = new HashMap<Integer, Integer>();
        	 	for (Integer node : beam.keySet() ) {
        	 		System.out.println(node + "hjklkjhn");
                 for (Integer neighbor : states[node].getNeighbours() ) {
                         if (neighbor == goal) {
                                return true; 
                         }
                         set.put(neighbor, node);
                 }
        	 	}
        	 	
        	 	beam = new HashMap<Integer, Integer>();
            g = g + 1;
             
             while ((set.size() != 0 ) && (width > beam.size())) {
            	 	HashMap<Integer, Integer> heuristicValue = new HashMap<Integer, Integer>();
                 for (Integer key : set.keySet()) {
                         heuristicValue.put(key, states[key].getDist());
                 }
                 Integer minIndex = compare_hashMap_min(heuristicValue);
             
                 Iterator<Integer> keys = set.keySet().iterator();
 				while(keys.hasNext()) {
 					Integer key = keys.next();
 					if (key == minIndex) keys.remove();	
 				}
 				
 				 if (!closedList.containsKey(minIndex)) {
                     closedList.put(minIndex, set.get(minIndex));
                     beam.put(minIndex, set.get(minIndex));
             }
             
             }
        }
		
		return false;
	}

	public HashMap<Integer, Integer> getClosedNotes() {
		return closedList;
	}
	public static Integer compare_hashMap_min(HashMap<Integer, Integer> heuristicValue) {
        Collection<Integer> c = heuristicValue.values();
        Integer minvalue = (Integer) Collections.min(c);
        Integer minIndex = 0;
        Set<Integer> scores_set = heuristicValue.keySet();
        Iterator<Integer> scores_it = scores_set.iterator();
        while(scores_it.hasNext()) {
                Integer id = scores_it.next();
                Integer value = heuristicValue.get(id);
                if (value == minvalue) {
                        minIndex = id;
                        break;
                }
        }
        return minIndex;
	}

}
