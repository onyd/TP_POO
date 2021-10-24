package automateCellulaire;

public class TestGrid {

	public static void main(String[] args) {
		Grid g = new Grid(10, 10);
		System.out.println(g);
		
		for(int i = 0; i < 10; i++) {
			g.iterate();
			System.out.println(g);
		}
	}

}
