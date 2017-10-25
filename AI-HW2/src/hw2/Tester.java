package hw2;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] goalBoard = {1,2,3,4,5,6,7,8,0};
		Board[] net;
		BeamSearch bs;
		
		Generator G = new Generator(11, goalBoard);
		
		net = G.Generate();
		

		
		System.out.println("Done");		
	}

}
