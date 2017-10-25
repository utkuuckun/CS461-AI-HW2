package hw2;

public class Board implements Comparable{
	//properties
		private int[] board;
		private int state_id;
		private boolean visited;
		public int[] neigbours;
		private int neigbourCount;
		private int maxNumOfNeigb;
		private int dist;

		public Board(int[] board, int id)
		{
			setBoard(board);
			dist =0;
			setState_id(id);
			visited = false; 
			neigbours = new int[4];
			neigbourCount = 0;
		}

		public int[] applyFunction()
		{
			int results[] = new int[4];
			int emptyPos = findPos(0);
			
			switch(emptyPos){
			case 0: results[0] = 1; results[1] = 3; results[2] = -1; results[3] = -1; maxNumOfNeigb = 2;break;
			case 1: results[0] = 0; results[1] = 4; results[2] = 2; results[3] = -1; maxNumOfNeigb = 3;break;
			case 2: results[0] = 1; results[1] = 5; results[2] = -1; results[3] = -1; maxNumOfNeigb = 2;break;
			case 3: results[0] = 0; results[1] = 4; results[2] = 6; results[3] = -1; maxNumOfNeigb = 3;break;
			case 4: results[0] = 1; results[1] = 3; results[2] = 5; results[3] = 7; maxNumOfNeigb = 4;break;
			case 5: results[0] = 2; results[1] = 4; results[2] = 8; results[3] = -1; maxNumOfNeigb = 3;break;
			case 6: results[0] = 3; results[1] = 7; results[2] = -1; results[3] = -1; maxNumOfNeigb = 2;break;
			case 7: results[0] = 6; results[1] = 4; results[2] = 8; results[3] = -1; maxNumOfNeigb = 3;break;
			case 8: results[0] = 7; results[1] = 5; results[2] = -1; results[3] = -1; maxNumOfNeigb = 2;break;
			}
			return results;
		}
		
		public int compareTo(Object arg0) {
			if(this.dist > ((Board)arg0).getDist())
				return 1;
			if(this.dist == ((Board)arg0).getDist())
				return 0;
			return -1;
		}
		
		public void calcDist(Board goalBoard)
		{
			int diff = 0;
			for(int i = 0; i < 9; i++)
			{
				int t = Math.abs(goalBoard.findPos(board[i]) - i);
				diff += (t/3) + (t % 3);
			}
			dist = diff/2;
		}
		
		public int[] exchange( int input)
		{
			int[] newBoard = new int[9];
			
			for(int i = 0; i < 9; i++)
				newBoard[i] = board[i];
			
			newBoard[findPos(0)] =  newBoard[input];
			newBoard[input] = 0;
			return newBoard;
		}
		
		public boolean compare(int[] board2)//If we don't care about a value it has -1 value
		{
			for(int i = 0; i < 9; i++)
			{
				if(board[i] != board2[i])
					return false;
			}
			return true;
		}
		
		public int findPos(int k)
		{
			for(int i = 0; i < 9; i++)
				if(board[i] == k)
					return i;
			return -1;
		}
		
		public int getNeigbourCount() {
			return neigbourCount;
		}
		
		
		public void setNeigbour(int stateId)
		{
			neigbours[neigbourCount++] = stateId;
		}
		
		public int[] getBoard() {
			return board;
		}


		private void setBoard(int[] board) {
			this.board = board;
		}



		public int getState_id() {
			return state_id;
		}

		public void setState_id(int state_id) {
			this.state_id = state_id;
		}
		public void setVisited(boolean val)
		{
			visited = val;
		}
		public boolean isVisited() {
			return visited;
		}

		public int getMaxNumOfNeigb() {
			return maxNumOfNeigb;
		}

		public void setMaxNumOfNeigb(int maxNumOfNeigb) {
			this.maxNumOfNeigb = maxNumOfNeigb;
		}

		public int getDist() {
			return dist;
		}

		public void setDist(int dist) {
			this.dist = dist;
		}
	}

