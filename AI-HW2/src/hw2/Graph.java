/*
 *  CS 461 - HW 2 
 *  Group Name: Minigma
 *  Group Members:  Irem Yuksel, Utku Uckun, Petek Ellialtioglu, Berk Izgi Danis
 * 
 */

package hw2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Graph extends JFrame{
	
	static int noOfInitBoards = 10;
	static Generator G;
	static int WIDTH = 2;
	HashMap<Integer,Integer> map;
	static int m;
	static Timer timer;
	
	public Graph(boolean run) {
		super();
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		
		
		int[] initialBoard = {1,2,3,4,5,6,7,8,0};
		G = new Generator(noOfInitBoards, initialBoard);
		Board[] boards = G.Generate();
		Tree tree = new Tree(boards);
		tree.beamSearch(WIDTH, boards.length-1, 0);
		ArrayList<Object> objects;
		
		map = tree.getSolution();
		System.out.println(map.size() + "irem" );
		
		JButton button = new JButton();
		button.addActionListener(new Listener());
		button.setText("Run");
		button.setBounds(650, 500, 30, 30);
		button.setVisible(true);
		
		
		try {
			int x = 30;
			int y = 30;
			int space = 80;
			String goal = "";
			int[] neighbours;
			objects = new ArrayList<Object>();
			
			for (int i = boards.length-1; i >= 0; i--) {
				if(boards[i].getDist() == 0) {
					goal = "\nGoal";
				} else goal = "";
				
				Object o = graph.insertVertex(parent, null,
						"S" + i + "\n" + boards[i].toString() + goal,
						x + ((i % 4) * space * 2), y + ((i / 4) * space * 2), 80, 80);
				objects.add(o);
			}
			
			if(!run) {
				for (int j = boards.length-1; j >= 0; j--) {
					neighbours = boards[j].getNeighbours();
					for (int k = 0; k < boards[j].getMaxNumOfNeigb(); k++) {
						graph.insertEdge(parent, null, "", objects.get(j),
								objects.get( neighbours[k] ), "dashed=1");
					}
				}
		
			} else {
				timer = new Timer();
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
							for ( int i : map.keySet() ) {
								int j = map.get(i);
								if(j != -1 && i != j && j != objects.size()-1)
									graph.insertEdge(parent, null, "",
										objects.get( j ),
										objects.get( i ) );
								
							}
							timer = null;
					}
				};
				timer.schedule(task,0,1000);
				button.setVisible(false);
			}
			this.add(button);
			
			graph.setCellsEditable(false);
			graph.setCellsBendable(false);
			graph.setCellsSelectable(false); //can be commented to see each edge individually by dragging
			
		} finally {
			graph.getModel().endUpdate();
		}
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}
	
	public static void main(String[] args) {
		Graph frame = new Graph(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(true);
	}
	
	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Graph frame = new Graph(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(700, 700);
			frame.setVisible(true);

		}

	}

}
