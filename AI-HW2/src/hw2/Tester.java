package hw2;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] initialBoard = {1,2,3,4,5,6,7,8,0};
		
		
		Generator G = new Generator(10, initialBoard);
		
		G.Generate();
		
		System.out.println("Done");		
	}

}
