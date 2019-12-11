package cs365project;

final class Node {
	/* Data Members */
	public double Price;
	public double S;
	public double fugit;

	
	/* Pointers to top and bottom child of node */
	public Node up = null;
	public Node down = null;
	
	public Node() {
	}
	
	public Node(double s) {
		S = s;
	}

	public Node(double fgt, double price) {
		fugit = fgt;
		Price = price;
	}
}
